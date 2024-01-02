package model.statements;

import model.ADT.symTable.MyIDictionary;
import model.exceptions.MyException;
import model.program.PrgState;
import model.types.Type;

public interface IStmt {
    //execution method for a statement
    PrgState execute(PrgState state) throws MyException;
    IStmt deepCopy();
    MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException;
}
