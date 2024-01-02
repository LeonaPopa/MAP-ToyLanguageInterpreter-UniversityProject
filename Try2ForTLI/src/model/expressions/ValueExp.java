package model.expressions;

import model.ADT.heap.MyIHeap;
import model.ADT.symTable.MyIDictionary;
import model.exceptions.MyException;
import model.types.Type;
import model.values.Value;

public class ValueExp implements Exp{
    private Value e;
    public ValueExp(Value e){
        this.e = e;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        return e;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return e.getType();
    }

    @Override
    public Exp deepCopy() {
        return new ValueExp(e.deepCopy());
    }
    @Override
    public String toString() {
        return e.toString();
    }
}
