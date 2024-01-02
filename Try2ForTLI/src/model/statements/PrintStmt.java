package model.statements;


import model.ADT.heap.MyIHeap;
import model.ADT.out.MyIList;
import model.ADT.symTable.MyIDictionary;
import model.exceptions.MyException;
import model.expressions.Exp;
import model.program.PrgState;
import model.types.Type;
import model.values.Value;

public class PrintStmt implements IStmt{
    private final Exp exp;
    public PrintStmt(Exp exp){this.exp=exp;}
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIList<Value> out = state.getOut();
        MyIHeap<Integer, Value> heap = state.getHeap();
        out.add(exp.eval(state.getSymTable(), heap));
        return null;
    }
    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString(){return "print("+exp.toString()+")";}
}
