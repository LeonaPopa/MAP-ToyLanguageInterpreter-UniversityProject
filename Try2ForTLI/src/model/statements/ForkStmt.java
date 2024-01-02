package model.statements;

import model.ADT.exeStack.MyIStack;
import model.ADT.exeStack.MyStack;
import model.ADT.heap.MyIHeap;
import model.ADT.out.MyIList;
import model.ADT.symTable.MyIDictionary;
import model.exceptions.MyException;
import model.program.PrgState;
import model.types.Type;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;

public class ForkStmt implements IStmt{
    private IStmt stmt;
    public ForkStmt(IStmt stmt){
        this.stmt = stmt;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIList<Value> out = state.getOut();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        return new PrgState(new MyStack<>(),symTable.deepCopy(),heap, out,fileTable, stmt);

    }

    @Override
    public IStmt deepCopy() {
        return new ForkStmt(stmt.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        stmt.typeCheck(typeEnv.deepCopy());
        return typeEnv;
    }

    @Override
    public String toString(){
        return "fork("+stmt.toString()+")";
    }
}
