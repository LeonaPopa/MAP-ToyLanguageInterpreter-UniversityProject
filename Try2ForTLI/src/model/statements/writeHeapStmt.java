package model.statements;

import model.ADT.heap.MyIHeap;
import model.ADT.symTable.MyIDictionary;
import model.exceptions.MyException;
import model.program.PrgState;
import model.types.RefType;
import model.types.Type;
import model.values.RefValue;
import model.values.Value;

public class writeHeapStmt implements IStmt{
    private String varName;
    private model.expressions.Exp exp;

    public writeHeapStmt(String varName, model.expressions.Exp exp){
        this.varName = varName;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        if (symTable.isDefined(varName)){
            Value oldValue = symTable.lookup(varName);
            if (oldValue.getType() instanceof RefType){
                int address = ((RefValue)oldValue).getAddress();
                if (heap.isDefined(address)){
                    Type locationType = ((RefType) oldValue.getType()).getInner();
                    Value newValue = exp.eval(symTable, heap);
                    if (newValue.getType().equals(locationType)){
                        heap.update(address, newValue);
                    } else throw new MyException("Expression evaluates to a type different from type of" + varName);
                } else throw new MyException("The address is not allocated");
            } else throw new MyException("Declared type of variable" + varName + "is not Ref");
        } else throw new MyException("The used variable" + varName + "was not declared before");
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new writeHeapStmt(varName, exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookup(varName);
        Type typeExp = exp.typeCheck(typeEnv);

        if (typeVar instanceof RefType) {
            RefType refTypeVar = (RefType) typeVar;

            if (refTypeVar.getInner().equals(typeExp)) {
                return typeEnv;
            } else {
                throw new MyException("writeHeap: right-hand side and left-hand side have different types!");
            }
        } else {
            throw new MyException("writeHeap: variable " + varName + " is not of reference type!");
        }}

    @Override
    public String toString() {
        return "writeHeap(" + varName + ", " + exp.toString() + ")";
    }
}
