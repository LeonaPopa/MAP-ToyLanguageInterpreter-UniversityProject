package model.statements;

import model.ADT.exeStack.MyIStack;
import model.ADT.heap.MyIHeap;
import model.ADT.symTable.MyIDictionary;
import model.exceptions.MyException;
import model.expressions.Exp;
import model.program.PrgState;
import model.types.BoolType;
import model.types.Type;
import model.values.BoolValue;
import model.values.Value;

public class IfStmt implements IStmt{
    private final Exp expr;
    private final IStmt thenS;
    private final IStmt elseS;


    public IfStmt(Exp expr, IStmt thenS, IStmt elseS) {
        this.thenS = thenS;
        this.elseS = elseS;
        this.expr = expr;
    }
    @Override
    public String toString(){
        return "IF("+expr.toString()+") THEN(" +thenS.toString()+")ELSE("+elseS.toString()+")";
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        Value cond = expr.eval(symTbl, heap);
        if(cond.getType().equals(new BoolType())){
            BoolValue condBool = (BoolValue) cond;
            if(condBool.getValue()){
                stk.push(thenS);
            }
            else{
                stk.push(elseS);
            }
        }
        else{
            throw new MyException("Condition is not a boolean!");
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(expr.deepCopy(), thenS.deepCopy(), elseS.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp=expr.typeCheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenS.typeCheck(typeEnv.deepCopy());
            elseS.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else
            throw new MyException("The condition of IF has not the type bool");
    }
}
