package model.statements;

import model.ADT.symTable.MyIDictionary;
import model.exceptions.MyException;
import model.program.PrgState;
import model.types.Type;

public class NopStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString(){
        return "(No Operation)";
    }
}
