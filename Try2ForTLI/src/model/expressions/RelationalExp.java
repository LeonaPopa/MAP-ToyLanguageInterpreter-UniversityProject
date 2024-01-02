package model.expressions;

import model.ADT.heap.MyIHeap;
import model.ADT.symTable.MyIDictionary;
import model.exceptions.MyException;
import model.types.BoolType;
import model.types.IntType;
import model.types.Type;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.Value;

import javax.management.relation.Relation;

public class RelationalExp implements Exp{
    private Exp e1;
    private Exp e2;
    private Relation relation;
    public RelationalExp(Exp e1, Exp e2, Relation relation){
        this.e1 = e1;
        this.e2 = e2;
        this.relation = relation;
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
                if (relation.equals("<")) return new BoolValue(i1 < i2);
                if (relation.equals("<=")) return new BoolValue(i1 <= i2);
                if (relation.equals("==")) return new BoolValue(i1 == i2);
                if (relation.equals("!=")) return new BoolValue(i1 != i2);
                if (relation.equals(">")) return new BoolValue(i1 > i2);
                if (relation.equals(">=")) return new BoolValue(i1 >= i2);
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
                return new BoolType();
            } else
                throw new MyException("second operand is not an integer");
        } else
            throw new MyException("first operand is not an integer");
    }

    @Override
    public Exp deepCopy() {
        return new RelationalExp(e1.deepCopy(), e2.deepCopy(), relation);
    }
    @Override
    public String toString() {
        return e1.toString() +" "+relation.toString() +" "+ e2.toString();
    }
}
