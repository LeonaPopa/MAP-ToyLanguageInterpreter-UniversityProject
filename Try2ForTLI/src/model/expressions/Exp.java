package model.expressions;

import model.ADT.heap.MyIHeap;
import model.ADT.symTable.MyIDictionary;
import model.exceptions.MyException;
import model.types.Type;
import model.values.Value;

public interface Exp {
    Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException;
    Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException;
    Exp deepCopy();
}
