package model.expressions;

import model.ADT.heap.MyIHeap;
import model.ADT.symTable.MyIDictionary;
import model.exceptions.MyException;
import model.types.IntType;
import model.types.Type;
import model.values.IntValue;
import model.values.Value;

public class ArithExp implements Exp{
    private final Exp e1;
    private final Exp e2;
    private final String op;
    public ArithExp(String op, Exp e1, Exp e2){
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl, heap);
            if (v2.getType().equals(new IntType())) {
                int i1 = ((IntValue) v1).getValue();
                int i2 = ((IntValue) v2).getValue();
                if (op.equals("+")) return new IntValue(i1 + i2);
                if (op.equals("-")) return new IntValue(i1 - i2);
                if (op.equals("*")) return new IntValue(i1 * i2);
                if (op.equals("/")){
                    if (i2 == 0)
                        throw new MyException("division by zero");
                    else
                        return new IntValue(i1 / i2);
                }
            } else throw new MyException("second operand is not an integer");
        } else throw new MyException("first operand is not an integer");
        return null;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1 = e1.typeCheck(typeEnv);
        type2 = e2.typeCheck(typeEnv);
        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new IntType();
            } else
                throw new MyException("second operand is not an integer");
        } else
            throw new MyException("first operand is not an integer");
    }

    @Override
    public Exp deepCopy() {
        return new ArithExp(op, e1.deepCopy(), e2.deepCopy());
    }
    @Override
    public String toString() {
        return e1.toString() +" "+op +" "+ e2.toString();
    }
}
