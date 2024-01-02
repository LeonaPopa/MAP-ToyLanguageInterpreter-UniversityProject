package view;

import controller.Controller;
import model.ADT.exeStack.MyStack;
import model.ADT.heap.MyHeap;
import model.ADT.out.MyList;
import model.ADT.symTable.MyDictionary;
import model.exceptions.MyException;
import model.expressions.ValueExp;
import model.expressions.VarExp;
import model.expressions.rH;
import model.program.PrgState;
import model.statements.*;
import model.types.IntType;
import model.types.RefType;
import model.values.IntValue;
import repository.Repository;

public class Interpreter {
    public static void main(String[] args) throws MyException {
        //Please call the method typecheck for the input program before you create its associated
        //PrgState. The execution is done only if the program passes the typechecker

        IStmt example1 =  new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new
                        VarExp("v"))));
        example1.typeCheck(new MyDictionary<>());
        PrgState program1 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyHeap<>(),new MyList<>(),new MyDictionary<>(), example1);
        Repository repo1 = new Repository(program1, "log1.txt");
        Controller ctrl1 = new Controller(repo1);

        /*IStmt example2 =  new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ArithmeticExpression(ArithmeticOperand.ADD,new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression(ArithmeticOperand.ADD,new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignStatement("b",new ArithmeticExpression(ArithmeticOperand.ADD,new VariableExpression("a"), new ValueExpression(new
                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        ProgramState program2 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example2);
        Repository repo2 = new MemoryRepository(program2, "log2.txt");
        Controller ctrl2 = new Controller(repo2);

        IStmt example3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignStatement("v",new ValueExpression(new
                                        IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))))));
        ProgramState program3 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example3);
        Repository repo3 = new MemoryRepository(program3, "log3.txt");
        Controller ctrl3 = new Controller(repo3);

        IStmt example4 = new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
                new CompoundStatement(new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new OpenReadFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseFileStatement(new VariableExpression("varf"))))))))));
        ProgramState program4 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example4);
        Repository repo4 = new MemoryRepository(program4, "log4.txt");
        Controller ctrl4 = new Controller(repo4);

        IStmt example5 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new NewStatement("v",new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(30))),
                                        new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                                new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a")))))))));
        ProgramState program5 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example5);
        Repository repo5 = new MemoryRepository(program5, "log5.txt");
        Controller ctrl5 = new Controller(repo5);

        IStmt example6 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new NewStatement("v",new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new WriteHeapStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a")))))))));
        ProgramState program6 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example6);
        Repository repo6 = new MemoryRepository(program6, "log6.txt");
        Controller ctrl6 = new Controller(repo6);

        IStmt example7 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(new WhileStatement(new RelationalExpression(Relation.GREATER, new VariableExpression("v"), new ValueExpression(new IntValue(0))), new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v", new ArithmeticExpression(ArithmeticOperand.SUBTRACT, new VariableExpression("v"), new ValueExpression(new IntValue(1)))))),
                                new PrintStatement(new VariableExpression("v")))));
        ProgramState program7 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example7);
        Repository repo7 = new MemoryRepository(program7, "log7.txt");
        Controller ctrl7 = new Controller(repo7);
        */
        IStmt thread2 = new CompStmt(new writeHeapStmt("a", new ValueExp(new IntValue(30))),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                        new CompStmt(new PrintStmt(new VarExp("v")),
                                new PrintStmt(new rH(new VarExp("a"))))));
        //thread2.typeCheck(new MyDictionary<>());
        IStmt example8 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new NewStmt("a",new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(thread2),
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new rH(new VarExp("a")))))))));
        example8.typeCheck(new MyDictionary<>());
        PrgState program8 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyHeap<>(),new MyList<>(),new MyDictionary<>(), example8);
        Repository repo8 = new Repository(program8, "log8.txt");
        Controller ctrl8 = new Controller(repo8);
        /*
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "Exit"));
        menu.addCommand(new RunExampleCommand("1", example1.toString(), ctrl1));
        menu.addCommand(new RunExampleCommand("2", example2.toString(), ctrl2));
        menu.addCommand(new RunExampleCommand("3", example3.toString(), ctrl3));
        menu.addCommand(new RunExampleCommand("4", example4.toString(), ctrl4));
        menu.addCommand(new RunExampleCommand("5", example5.toString(), ctrl5));
        menu.addCommand(new RunExampleCommand("6", example6.toString(), ctrl6));
        menu.addCommand(new RunExampleCommand("7", example7.toString(), ctrl7));
        menu.addCommand(new RunExampleCommand("8", example8.toString(), ctrl8));
        menu.show();
        */

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "Exit"));
        menu.addCommand(new RunExampleCommand("1", example1.toString(), ctrl1));
        menu.addCommand(new RunExampleCommand("8", example8.toString(), ctrl8));
        menu.show();
    }
}
