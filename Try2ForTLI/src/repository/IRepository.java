package repository;

import model.exceptions.MyException;
import model.program.PrgState;

import java.util.List;

public interface IRepository {
    List<PrgState> getProgramList();

    void setProgramList(List<PrgState> newProgramList);

    void logProgramStateExecution(PrgState programState) throws MyException;

    void addProgram(PrgState programState);

    void logBeginningOfNewExecution() throws MyException;
}
