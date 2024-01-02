package model.ADT.heap;

import java.util.Map;

public interface MyIHeap<K,V> {
    void add(K key, V value);
    void update(K key, V value);
    V lookup(K key);
    boolean isDefined(K key);
    int nextFree();
    void remove(K key);
    String toString();
    Iterable<K> getAll();
    void setContent(Map<K,V> map);
    Map<K,V> getContent();

}
