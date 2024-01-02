package model.ADT.symTable;

import model.exceptions.MyException;

import java.util.Dictionary;

public interface MyIDictionary<K, V> {
    void add(K key, V value);
    void update(K key, V value);
    V lookup(K key);

    String toString();

    boolean isDefined(K key);

    void delete(K key) throws MyException;

    Iterable<K> getAll();
    void setContent(Dictionary<K,V> dict);
    Dictionary<K,V> getContent();
    MyIDictionary<K,V> deepCopy();
}
