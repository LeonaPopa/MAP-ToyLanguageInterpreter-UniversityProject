package model.statements;

import model.ADT.heap.MyIHeap;
import model.ADT.symTable.MyIDictionary;
import model.exceptions.MyException;
import model.expressions.Exp;
import model.program.PrgState;
import model.types.RefType;
import model.types.Type;
import model.values.Value;

public class NewStmt implements IStmt{
    private final String var_name;
    private final model.expressions.Exp exp;

    public NewStmt(String varName, Exp exp) {
        var_name = varName;
        this.exp = exp;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        if(symTable.isDefined(var_name)){
            Type type = symTable.lookup(var_name).getType();
            if(type instanceof RefType){
               Type locationType = ((RefType) type).getInner();
               Value value = exp.eval(symTable, heap);
               if(locationType.equals(value.getType())){
                   int address = heap.nextFree();
                     heap.add(address, value);
                   symTable.update(var_name, new model.values.RefValue(address, locationType));
               }else throw new MyException("Types don't match");
            }
            else throw new MyException("Types don't match");
        }
        else throw new MyException("Variable is not defined");
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NewStmt(var_name, exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookup(var_name);
        Type typeExp = exp.typeCheck(typeEnv);
        if(typeVar.equals(new RefType(typeExp))){
            return typeEnv;
        }
        else{
            throw new MyException("New: right hand side and left hand side have different types!");
        }
    }

    @Override
    public String toString(){
        return "new("+var_name+","+exp.toString()+")";
    }
}
