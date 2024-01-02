package model.statements;

import model.ADT.heap.MyIHeap;
import model.ADT.symTable.MyIDictionary;
import model.exceptions.MyException;
import model.expressions.Exp;
import model.program.PrgState;
import model.types.StringType;
import model.types.Type;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class openRFileStmt implements IStmt{
    private Exp fileName;
    public openRFileStmt(Exp fileName){
        this.fileName = fileName;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        Value v = fileName.eval(symTable, heap);
        if (v.getType().equals(new StringType())){
            StringValue filePath = (StringValue) v;
            if (fileTable.isDefined(filePath)){
                throw new MyException("File is already opened");
            } else{
                try{
                    BufferedReader file = new BufferedReader(new FileReader(filePath.getValue()));
                    fileTable.add(filePath, file);
                } catch (FileNotFoundException e) {
                    throw new MyException("File not found");
                }
            }
        } else throw new MyException("The File Name Expression is not string");
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new openRFileStmt(fileName.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = fileName.typeCheck(typeEnv);
        if (typeExp.equals(new StringType())){
            return typeEnv;
        } else throw new MyException("The File Name Expression is not string");
    }

    @Override
    public String toString(){
        return "openRFile(" + fileName.toString() + ")";
    }
}
