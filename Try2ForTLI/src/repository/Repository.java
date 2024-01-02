package repository;

import model.exceptions.MyException;
import model.program.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{

    private List<PrgState> programList;
    private final String logFilePath;

    public Repository(PrgState prgState, String logFilePath){
        programList = new ArrayList<>();
        programList.add(prgState);
        this.logFilePath = logFilePath;
    }
    @Override
    public List<PrgState> getProgramList() {
        return programList;
    }

    @Override
    public void setProgramList(List<PrgState> newProgramList) {
        programList = newProgramList;
    }

    @Override
    public void logProgramStateExecution(PrgState programState) throws MyException {
        try(PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)))) {
            logFile.write(programState.toString() + "\n\n");
        } catch (IOException ioException){
            throw new MyException(ioException.getMessage());
        }
    }

    @Override
    public void addProgram(PrgState programState) {
            programList.add(0,programState);
    }

    @Override
    public void logBeginningOfNewExecution() throws MyException {
        try(PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)))) {
            logFile.write("---NEW EXECUTION---\n");
        } catch (IOException ioException){
            throw new MyException(ioException.getMessage());
        }
    }
}
