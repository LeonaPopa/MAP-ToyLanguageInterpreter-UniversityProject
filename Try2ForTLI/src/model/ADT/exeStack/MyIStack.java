package model.ADT.exeStack;

public interface MyIStack<T> {
    void push(T v);
    T pop();
    boolean isEmpty();
    String toString();
}
