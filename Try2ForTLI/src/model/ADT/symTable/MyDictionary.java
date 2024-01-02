package model.ADT.symTable;

import model.exceptions.MyException;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class MyDictionary<K, V> implements MyIDictionary<K, V>{
    private Dictionary<K, V> dict ;
    public MyDictionary() {
        dict = new Hashtable<>();
    }
    private MyDictionary(Dictionary<K, V> dict) {
        this.dict = dict;
    }
    @Override
    public void add(K key, V value) {
        if (isDefined(key)) {
            throw new RuntimeException("Key already exists!");
        }
        dict.put(key, value);
    }

    @Override
    public void update(K key, V value) {
        if (!isDefined(key)) {
            throw new RuntimeException("Key does not exist!");
        }
        dict.put(key, value);
    }

    @Override
    public V lookup(K key) {
        if (!isDefined(key)) {
            throw new RuntimeException("Key does not exist!");
        }
        return dict.get(key);
    }
    @Override
    public boolean isDefined(K key) {
        Enumeration<K> enu = dict.keys();
        while (enu.hasMoreElements()) {
            if (enu.nextElement() == key)
                return true;
        }
        return false;
    }
    @Override
    public void delete(K key) throws MyException {
        if(!isDefined(key)){
            throw new MyException("The key does not exist");
        }
        dict.remove(key);
    }
    @Override
    public Iterable<K> getAll() {
        return (Iterable<K>) dict.keys();
    }

    @Override
    public void setContent(Dictionary<K, V> dict) {
        this.dict = dict;
    }

    @Override
    public Dictionary<K, V> getContent() {
        return dict;
    }

    @Override
    public MyIDictionary<K, V> deepCopy() {
        Dictionary<K, V> newDict = new Hashtable<>();
        Enumeration<K> enu = dict.keys();
        while (enu.hasMoreElements()) {
            K key = enu.nextElement();
            newDict.put(key, dict.get(key));
        }
        return new MyDictionary<>(newDict);
    }
    public String toString() {
        StringBuilder str = new StringBuilder();
        Enumeration<K> enu = dict.keys();
        while (enu.hasMoreElements()) {
            K key = enu.nextElement();
            str.append(key.toString()).append(" -> ").append(dict.get(key).toString()).append("\n");
        }
        return str.toString();
    }

}
