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

public class WhileStmt implements IStmt{
    private Exp condition;
    private IStmt statement;
    public WhileStmt(Exp condition, IStmt statement){
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> exeStack = state.getStk();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        Value cond = condition.eval(symTable, heap);
        if (cond.getType().equals(new BoolType())){
            if(((BoolValue)cond).getValue()){
                exeStack.push(this);
                exeStack.push(statement);
            } else {
                exeStack.push(new NopStmt());
            }
        } else throw new MyException("Conditional Expression is not bool");
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new WhileStmt(condition.deepCopy(), statement.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp=condition.typeCheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            statement.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else
            throw new MyException("The condition of WHILE has not the type bool");
    }

    @Override
    public String toString() {
        return "while (" + condition.toString() + ") " + statement.toString();
    }
}
