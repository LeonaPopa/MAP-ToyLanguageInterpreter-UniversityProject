package model.statements;

import model.ADT.exeStack.MyIStack;
import model.ADT.symTable.MyIDictionary;
import model.exceptions.MyException;
import model.program.PrgState;
import model.types.Type;

public class CompStmt implements IStmt{
    private final IStmt first;
    private final IStmt snd;
    public CompStmt(IStmt first, IStmt snd){
        this.first = first;
        this.snd = snd;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getStk();
        stk.push(snd);
        stk.push(first);
        return null;
   }
   @Override
   public IStmt deepCopy(){return new CompStmt(first.deepCopy(), snd.deepCopy());}

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return snd.typeCheck(first.typeCheck(typeEnv));
    }

    @Override
   public String toString(){
        return "("+first.toString()+";"+snd.toString()+")";
    }
}
