package Exercise1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnsortedTableMap<K, V> {
    // I named this 'storage' to keep it distinct from 'table' in the HashMap
    private ArrayList<MapEntry<K, V>> storage = new ArrayList<>();

    public UnsortedTableMap() { }

    // Helper method to find the index of a key
    private int findEntryIndex(K key) {
        int n = storage.size();
        for (int j = 0; j < n; j++) {
            if (storage.get(j).getKey().equals(key)) {
                return j;
            }
        }
        return -1; // Key not found
    }

    public int size() { return storage.size(); }

    public V get(K key) {
        int index = findEntryIndex(key);
        if (index == -1) return null;
        return storage.get(index).getValue();
    }

    public V put(K key, V value) {
        int index = findEntryIndex(key);
        if (index == -1) {
            storage.add(new MapEntry<>(key, value));
            return null;
        } else {
            return storage.get(index).setValue(value);
        }
    }

    public V remove(K key) {
        int index = findEntryIndex(key);
        int n = size();
        if (index == -1) return null;
        V removedValue = storage.get(index).getValue();
        if (index != n - 1) {
            // I struggled with efficiency here; instead of shifting everything, 
            // I move the last element into the hole left by the removed one.
            storage.set(index, storage.get(n - 1));
        }
        storage.remove(n - 1);
        return removedValue;
    }

    // Support for the entrySet() used in ChainHashMap
    private class EntryIterable implements Iterable<Entry<K, V>> {
        public Iterator<Entry<K, V>> iterator() { return new EntryIterator(); }
    }

    private class EntryIterator implements Iterator<Entry<K, V>> {
        private int j = 0;
        public boolean hasNext() { return j < storage.size(); }
        public Entry<K, V> next() {
            if (j == storage.size()) throw new NoSuchElementException();
            return storage.get(j++);
        }
        public void remove() { throw new UnsupportedOperationException(); }
    }

    public Iterable<Entry<K, V>> entrySet() { return new EntryIterable(); }
}