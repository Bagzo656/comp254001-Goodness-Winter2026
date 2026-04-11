package Exercise1;

//AbstractHashMap.java
import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractHashMap<K, V> {
 protected int numEntries = 0; 
 protected int capacity;
 private int prime;
 private long scale, shift;
 
 // NEW: User-defined load factor
 protected double capacityThreshold; 

 public AbstractHashMap(int cap, int p, double loadFactor) {
     prime = p;
     capacity = cap;
     capacityThreshold = loadFactor;
     Random rand = new Random();
     scale = rand.nextInt(prime - 1) + 1;
     shift = rand.nextInt(prime);
     createTable();
 }

 public AbstractHashMap(int cap, double loadFactor) { this(cap, 109345121, loadFactor); }
 public AbstractHashMap() { this(17, 0.5); } // Default load factor

 public int size() { return numEntries; }
 public V get(K key) { return bucketGet(hashValue(key), key); }
 public V remove(K key) { return bucketRemove(hashValue(key), key); }

 public V put(K key, V value) {
     V answer = bucketPut(hashValue(key), key, value);
     // I used capacityThreshold here instead of the hardcoded 0.5
     if (numEntries > capacity * capacityThreshold) {
         resize(2 * capacity - 1);
     }
     return answer;
 }

 private int hashValue(K key) {
     return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
 }

 private void resize(int newCap) {
     ArrayList<Entry<K, V>> buffer = new ArrayList<>(numEntries);
     for (Entry<K, V> e : entrySet()) buffer.add(e);
     capacity = newCap;
     createTable();
     numEntries = 0;
     for (Entry<K, V> e : buffer) put(e.getKey(), e.getValue());
 }

 // Abstract methods to be implemented by subclasses
 protected abstract void createTable();
 protected abstract V bucketGet(int h, K k);
 protected abstract V bucketPut(int h, K k, V v);
 protected abstract V bucketRemove(int h, K k);
 public abstract Iterable<Entry<K, V>> entrySet();
}
