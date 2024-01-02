package model.statements;

import model.ADT.symTable.MyIDictionary;
import model.exceptions.MyException;
import model.program.PrgState;
import model.types.Type;
import model.values.Value;

public class VarDeclStmt implements IStmt{
    private final String name;
    private final Type type;

    public VarDeclStmt(String name, model.types.Type type) {
        this.name = name;
        this.type = type;
    }



    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if(symTable.isDefined(name)) {
            throw new MyException("Variable " + name + " is already defined!");
        }else{
            symTable.add(name, type.defaultValue());
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.add(name, type);
        return typeEnv;
    }

    @Override
    public String toString() {
        return type.toString() + " " + name;
    }
}
