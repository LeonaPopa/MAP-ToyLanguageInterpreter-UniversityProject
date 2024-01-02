package model.statements;

import model.ADT.heap.MyIHeap;
import model.ADT.symTable.MyIDictionary;
import model.exceptions.MyException;
import model.expressions.Exp;
import model.program.PrgState;
import model.types.Type;
import model.values.Value;

public class AssignStmt implements IStmt{
    private final String id;
    private final Exp exp;

    public AssignStmt(String id, Exp exp){
        this.id = id;
        this.exp = exp;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        if(symTbl.isDefined(id)){
            Value val = exp.eval(symTbl, heap);
            Type type = symTbl.lookup(id).getType();
            if(val.getType().equals(type)){
                symTbl.update(id, val);
            }
            else{
                throw new MyException("Declared type of variable " + id + " and type of the assigned expression do not match!");
            }
        }
        else{
            throw new MyException("The used variable " + id + " was not declared before!");
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new AssignStmt(id, exp.deepCopy());

    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookup(id);
        Type typeExp = exp.typeCheck(typeEnv);
        if(typeVar.equals(typeExp)){
            return typeEnv;
        }
        else{
            throw new MyException("Assignment: right hand side and left hand side have different types!");
        }
    }

    @Override
    public String toString(){
        return id + "=" + exp.toString();
    }
}
