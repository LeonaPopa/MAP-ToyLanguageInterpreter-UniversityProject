package model.expressions;

import model.ADT.heap.MyIHeap;
import model.ADT.symTable.MyIDictionary;
import model.exceptions.MyException;
import model.types.RefType;
import model.types.Type;
import model.values.RefValue;
import model.values.Value;

public class rH implements Exp{
    private final Exp exp;
    public rH(Exp exp){
        this.exp = exp;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        Value v = exp.eval(tbl, heap);
        if(v.getType() instanceof RefType) {
            RefValue refValue = (RefValue) v;
            int address = refValue.getAddress();
            if (heap.isDefined(address)) {
                return heap.lookup(address);
            } else
                throw new MyException("Address not found in heap");
        }else{
                throw new MyException("Value is not a reference");
            }
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type = exp.typeCheck(typeEnv);
        if(type instanceof RefType){
            RefType refType = (RefType) type;
            return refType.getInner();
        }else{
            throw new MyException("Expression is not a reference");
        }
    }

    @Override
    public Exp deepCopy() {
        return new rH(exp.deepCopy());
    }
    @Override
    public String toString() {
        return "rH("+exp.toString()+")";
    }
}
