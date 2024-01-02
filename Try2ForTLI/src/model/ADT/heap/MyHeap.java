package model.ADT.heap;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<K, V> implements MyIHeap<K, V> {
    private final Map<K, V> heap= new HashMap<>();
    private static int nextFree = 1;

    public MyHeap() {
        nextFree = 1;
    }
    @Override
    public void add(K key, V value) {
        if (isDefined(key)) {
            throw new RuntimeException("Key already exists!");
        }
        heap.put(key, value);
        nextFree++;
    }

    @Override
    public void update(K key, V value) {
        if (!isDefined(key)) {
            throw new RuntimeException("Key does not exist!");
        }
        heap.put(key, value);
    }

    @Override
    public V lookup(K key) {
        if (!isDefined(key)) {
            throw new RuntimeException("Key does not exist!");
        }
        return heap.get(key);
    }

    @Override
    public boolean isDefined(K key) {
        return heap.containsKey(key);
    }

    @Override
    public int nextFree() {
        return nextFree;
    }

    @Override
    public void remove(K key) {
        if (!isDefined(key)) {
            throw new RuntimeException("Key does not exist!");
        }
        heap.remove(key);
    }

    @Override
    public Iterable<K> getAll() {
        return heap.keySet();
    }

    @Override
    public void setContent(Map<K, V> map) {
        heap.clear();
        heap.putAll(map);
    }

    @Override
    public Map<K, V> getContent() {
        return heap;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (K key : heap.keySet()) {
            result.append(key).append("->").append(heap.get(key)).append("\n");
        }
        return result.toString();
    }
}
