package model.statements;

import model.ADT.heap.MyIHeap;
import model.ADT.symTable.MyIDictionary;
import model.exceptions.MyException;
import model.program.PrgState;
import model.types.IntType;
import model.types.StringType;
import model.types.Type;
import model.values.IntValue;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class readFileStmt implements IStmt{

    private model.expressions.Exp exp;
    private String varName;

    public readFileStmt(model.expressions.Exp exp, String varName){
        this.exp = exp;
        this.varName = varName;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        if (symTable.isDefined(varName)){
            Type varType = symTable.lookup(varName).getType();
            if(varType.equals(new IntType())) {
                Value v = exp.eval(symTable, heap);
                if (v.getType().equals(new StringType())) {
                    StringValue fileName = (StringValue) v;
                    if (fileTable.isDefined(fileName)) {
                        BufferedReader bufferedReader = fileTable.lookup(fileName);
                        try {
                            String line = bufferedReader.readLine();
                            int numberRead;
                            if (line == null || line.isEmpty()) numberRead = 0;
                            else numberRead = Integer.parseInt(line.strip());
                            symTable.update(varName, new IntValue(numberRead));
                        } catch (IOException io) {
                            throw new MyException(io.getMessage());
                        }
                    } else throw new MyException("File is not open");
                } else throw new MyException("Expression is not string");
            } else throw new MyException("Variable is not int");
        } else throw new MyException("Variable is not declared");
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new readFileStmt(exp.deepCopy(), varName);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookup(varName);
        Type typeExp = exp.typeCheck(typeEnv);
        if (typeVar.equals(new IntType())){
            if (typeExp.equals(new StringType())){
                return typeEnv;
            } else throw new MyException("Expression is not string");
        } else throw new MyException("Variable is not int");
    }

    @Override
    public String toString(){
        return "readFileStmt(" + exp.toString() + ", " + varName + ")";
    }
}
