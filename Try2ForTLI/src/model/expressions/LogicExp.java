package model.expressions;

import model.ADT.heap.MyIHeap;
import model.ADT.symTable.MyIDictionary;
import model.exceptions.MyException;
import model.types.BoolType;
import model.types.Type;
import model.values.BoolValue;
import model.values.Value;

public class LogicExp implements Exp{
    private Exp e1;
    private Exp e2;
    private String op;

    public LogicExp(String op, Exp e1, Exp e2){
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public String toString() {
        return e1.toString() +" "+op +" "+ e2.toString();
    }

    @Override
    public Exp deepCopy() {
        return new LogicExp(op, e1.deepCopy(), e2.deepCopy());
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException, MyException {
        Value v1, v2;
        v1 = e1.eval(tbl, heap);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(tbl, heap);
            if (v2.getType().equals(new BoolType())) {
                BoolValue i1 = (BoolValue) v1;
                BoolValue i2 = (BoolValue) v2;
                if (op.equals("&&")) return new BoolValue(i1.getValue() && i2.getValue());
                if (op.equals("||")) return new BoolValue(i1.getValue() || i2.getValue());
            } else
                throw new MyException("first operand is not an integer");
        } else
            throw new MyException("second operand is not an integer");
        return null;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1 = e1.typeCheck(typeEnv);
        type2 = e2.typeCheck(typeEnv);
        if (type1.equals(new BoolType())) {
            if (type2.equals(new BoolType())) {
                return new BoolType();
            } else
                throw new MyException("second operand is not an integer");
        } else
            throw new MyException("first operand is not an integer");
    }
}
