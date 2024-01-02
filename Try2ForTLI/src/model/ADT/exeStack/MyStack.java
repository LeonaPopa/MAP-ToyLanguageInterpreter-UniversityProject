package model.ADT.exeStack;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {

    private final Stack<T> stack = new Stack<>();

    @Override
    public void push(T v) {
        stack.push(v);
    }
    @Override
    public T pop() {
        if (stack.empty())
            throw new EmptyStackException();
           return stack.pop();
    }
    @Override
    public boolean isEmpty() {
          return stack.isEmpty();
    }
    @Override
    public String toString() {
        List<T> list = new ArrayList<>(stack);
        StringBuilder stackString = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--){
            String currentElement = list.get(i).toString();
            stackString.append(currentElement).append("\n");
        }
        return stackString.toString();
    }
}
