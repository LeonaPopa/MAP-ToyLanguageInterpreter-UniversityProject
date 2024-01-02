package model.expressions;

import model.ADT.heap.MyIHeap;
import model.ADT.symTable.MyIDictionary;
import model.exceptions.MyException;
import model.types.Type;
import model.values.Value;

public class VarExp implements Exp{
    private final String id;
    public VarExp(String id){
        this.id = id;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        return tbl.lookup(id);
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.lookup(id);
    }

    @Override
    public Exp deepCopy() {
        return new VarExp(id);
    }
    @Override
    public String toString() {
        return id;
    }
}
