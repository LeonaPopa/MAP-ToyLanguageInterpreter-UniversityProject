package model.exceptions;

public class MyException extends Exception{
    //call parent class constructor with super
    public MyException(){ super("error");}
    public MyException(String description){super(description);}
}
