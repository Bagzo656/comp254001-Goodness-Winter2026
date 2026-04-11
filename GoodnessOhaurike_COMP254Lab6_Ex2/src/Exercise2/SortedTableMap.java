package Exercise2;

import java.util.ArrayList;

/**
 * COMP-254 Lab #6 - Exercise 2
 * Submitted by: Goodness Ohaurike
 * Student Number: 301468840
 */
public class SortedTableMap<K extends Comparable<K>, V> {

    // Internal storage for the map entries
    private ArrayList<MapEntry<K, V>> table = new ArrayList<>();

    public SortedTableMap() { }

    /**
     * Helper method to find the index of a key using Binary Search.
     * Returns the index of the key if found, or the index where 
     * the key should be inserted (to keep the list sorted).
     */
    private int findIndex(K key) {
        int low = 0;
        int high = table.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comp = table.get(mid).getKey().compareTo(key);
            
            if (comp == 0) return mid; // Key found
            else if (comp < 0) low = mid + 1; // Search right
            else high = mid - 1; // Search left
        }
        return low; // Key not found, return insertion point
    }

    public int size() { return table.size(); }
    public boolean isEmpty() { return size() == 0; }

    /**
     * Returns the value associated with the key, or null if not found.
     * FIX: Added this method to resolve the 'undefined' error in MapTester.
     */
    public V get(K key) {
        int indexFound = findIndex(key);
        // I checked index validity to avoid IndexOutOfBoundsException
        if (indexFound < table.size() && table.get(indexFound).getKey().equals(key)) {
            return table.get(indexFound).getValue();
        }
        return null; // Ambiguous: could mean key missing OR key exists with null value
    }

    /**
     * NEW: Implementation of containsKey as required by Lab Exercise 2.
     * This resolves the null-value ambiguity.
     */
    public boolean containsKey(K key) {
        int position = findIndex(key);
        
        /* 
         * I originally forgot to check if position == table.size(), 
         * which caused a crash when searching for a key larger than any in the map.
         * This check ensures the search is safe.
         */
        if (position >= table.size()) {
            return false;
        }
        
        // If the key at the found position matches exactly, the key exists.
        return table.get(position).getKey().equals(key);
    }

    /**
     * Associates the given value with the given key.
     */
    public V put(K key, V value) {
        int i = findIndex(key);
        if (i < table.size() && table.get(i).getKey().equals(key)) {
            return table.get(i).setValue(value); // Replace existing
        }
        // I used the insertion point from findIndex to keep the list sorted
        table.add(i, new MapEntry<>(key, value));
        return null;
    }

    // --- Inner Class to handle Entries ---
    private static class MapEntry<K, V> {
        private K k;
        private V v;

        public MapEntry(K key, V value) {
            k = key;
            v = value;
        }

        public K getKey() { return k; }
        public V getValue() { return v; }
        public V setValue(V value) {
            V old = v;
            v = value;
            return old;
        }
    }
}