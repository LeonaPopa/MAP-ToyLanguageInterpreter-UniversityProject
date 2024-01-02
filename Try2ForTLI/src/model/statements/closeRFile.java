package model.statements;

import model.ADT.heap.MyIHeap;
import model.ADT.symTable.MyIDictionary;
import model.exceptions.MyException;
import model.expressions.Exp;
import model.program.PrgState;
import model.types.StringType;
import model.types.Type;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFile implements IStmt{
    private Exp exp;

    public closeRFile(Exp exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        Value v = exp.eval(symTable, heap);
        if (v.getType().equals(new StringType())){
            StringValue fileName = (StringValue) v;
            if (fileTable.isDefined(fileName)){
                try {
                    fileTable.lookup(fileName).close();
                    fileTable.delete(fileName);
                } catch (IOException e) {
                    throw new MyException(e.getMessage());
                }
            } else throw new MyException("File is not open");
        } else throw new MyException("Expression is not string");

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new closeRFile(exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = exp.typeCheck(typeEnv);
        if (typeExp.equals(new StringType())){
            return typeEnv;
        } else throw new MyException("Expression is not string");
    }

    @Override
    public String toString(){
        return "closeRFile("+exp.toString()+")";
    }
}
