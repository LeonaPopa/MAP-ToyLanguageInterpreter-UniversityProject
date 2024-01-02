package model.program;

import model.ADT.exeStack.MyIStack;
import model.ADT.heap.MyIHeap;
import model.ADT.out.MyIList;
import model.ADT.symTable.MyIDictionary;
import model.statements.IStmt;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;

public class PrgState {
    private int id;
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIHeap<Integer, Value> heap;
    private MyIList<Value> out;
    private MyIDictionary<StringValue, BufferedReader> fileTable;
    private IStmt originalProgram;
    private static int nextId = 1;

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIHeap<Integer, Value> heap, MyIList<Value> ot, MyIDictionary<StringValue, BufferedReader> fileTable, IStmt prg){
        exeStack = stk;
        symTable = symtbl;
        this.heap = heap;
        out = ot;
        this.fileTable = fileTable;
        originalProgram = prg.deepCopy();
        stk.push(prg);
        id = getNextId();
    }
    private static synchronized int getNextId(){
        nextId++;
        return nextId-1;
    }
    public int getId(){
        return id;
    }
    public MyIStack<IStmt> getStk(){
        return exeStack;
    }
    public MyIDictionary<String, Value> getSymTable(){
        return symTable;
    }
    public MyIHeap<Integer, Value> getHeap(){
        return heap;
    }
    public MyIList<Value> getOut(){
        return out;
    }
    public MyIDictionary<StringValue, BufferedReader> getFileTable(){
        return fileTable;
    }
    public IStmt getOriginalProgram(){
        return originalProgram;
    }
    public void setStk(MyIStack<IStmt> stk){
        exeStack = stk;
    }
    public void setSymTable(MyIDictionary<String, Value> symtbl){
        symTable = symtbl;
    }
    public void setHeap(MyIHeap<Integer, Value> heap){
        this.heap = heap;
    }
    public void setOut(MyIList<Value> ot){
        out = ot;
    }
    public void setFileTable(MyIDictionary<StringValue, BufferedReader> fileTable){
        this.fileTable = fileTable;
    }
    public void setOriginalProgram(IStmt prg){
        originalProgram = prg;
    }
    public boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }
    public PrgState executeOneStep() throws Exception{
        if(exeStack.isEmpty())
            throw new Exception("prgstate stack is empty");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    @Override
    public String toString(){
        return "Program state id: " + id + "\n" +
                "Execution stack: " + exeStack.toString() + "\n" +
                "Symbol table: " + symTable.toString() + "\n" +
                "Heap: " + heap.toString() + "\n" +
                "Output: " + out.toString() + "\n" +
                "File table: " + fileTable.toString() + "\n";

    }

}
