package controller;

import model.exceptions.MyException;
import model.program.PrgState;
import model.values.RefValue;
import model.values.Value;
import repository.IRepository;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private final IRepository repository;

    private ExecutorService executor;

    public Controller(IRepository repository) {
        this.repository = repository;
    }
    List<Integer> getAddressFromHeap(Collection<Value> heapValues){
        return heapValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(value -> {
                    RefValue value1 = (RefValue) value;
                    return value1.getAddress();
                })
                .collect(Collectors.toList());
    }
    List<Integer> getAddressFromSymTable(Enumeration<Value> symTableValues){
        Collection<Value> valueCollection = new ArrayList<>();
        while (symTableValues.hasMoreElements()){
            valueCollection.add(symTableValues.nextElement());
        }
        return valueCollection.stream()
                .filter(v -> v instanceof RefValue)
                .map(v ->{
                    RefValue v1 = (RefValue) v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
    }
    Map<Integer, Value> garbageCollector(List<Integer> symTableAddresses, Map<Integer, Value> heap){
        List<Integer> heapAddresses = getAddressFromHeap(heap.values());
        return heap.entrySet().stream()
                .filter(e -> symTableAddresses.contains(e.getKey()) || heapAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }
    List<PrgState> removeCompletedPrograms(List<PrgState> inProgramList){
        return inProgramList.stream().filter(PrgState::isNotCompleted).collect(Collectors.toList());

    }

    public void executeOneStepForAllPrograms(List<PrgState> programStateList) throws MyException{
        for (PrgState programState : programStateList) {
            repository.logProgramStateExecution(programState);
        }

        List<Callable<PrgState>> callList = programStateList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(p::executeOneStep))
                .collect(Collectors.toList());
        try {
            StringBuilder errors = new StringBuilder();
            List<PrgState> newProgramList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (ExecutionException | InterruptedException e) {
                            errors.append(e.getMessage());
                        }
                        return null;
                    }).filter(Objects::nonNull).collect(Collectors.toList());

            if (!errors.toString().equals("")){
                throw new MyException(errors.toString());
            }
            programStateList.addAll(newProgramList);
        } catch (InterruptedException e) {
            throw new MyException(e.getMessage());
        }

        for (PrgState programState : programStateList) {
            repository.logProgramStateExecution(programState);
        }

        repository.setProgramList(programStateList);
    }
    public void executeAllSteps() throws MyException {
        repository.logBeginningOfNewExecution();
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> programList = removeCompletedPrograms(repository.getProgramList());
        while (!programList.isEmpty()){

            List<Integer> symTablesAddresses = new ArrayList<>();
            for (PrgState program : programList){
                symTablesAddresses.addAll(getAddressFromSymTable(program.getSymTable().getContent().elements()));
            }
            programList.get(0).getHeap().setContent(garbageCollector(symTablesAddresses, programList.get(0).getHeap().getContent()));

            executeOneStepForAllPrograms(programList);
            programList = removeCompletedPrograms(repository.getProgramList());
        }
        executor.shutdownNow();
        repository.setProgramList(programList);
    }
}
