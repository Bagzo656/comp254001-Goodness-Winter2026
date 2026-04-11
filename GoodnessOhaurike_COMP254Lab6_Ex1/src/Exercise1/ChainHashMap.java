package Exercise1;
import java.util.ArrayList;

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {
    // This array now recognizes 'UnsortedTableMap'
    private UnsortedTableMap<K, V>[] table; 

    public ChainHashMap(int cap, double loadFactor) { super(cap, loadFactor); }
    public ChainHashMap() { super(); } // Default constructor

    @Override
    @SuppressWarnings("unchecked") // I added this to suppress the generic array warning
    protected void createTable() {
        table = (UnsortedTableMap<K, V>[]) new UnsortedTableMap[capacity];
    }

    @Override
    protected V bucketGet(int h, K k) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null) return null;
        return bucket.get(k);
    }

    @Override
    protected V bucketPut(int h, K k, V v) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null) {
            bucket = table[h] = new UnsortedTableMap<>();
        }
        int oldSize = bucket.size();
        V answer = bucket.put(k, v);
        numEntries += (bucket.size() - oldSize);
        return answer;
    }

    @Override
    protected V bucketRemove(int h, K k) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null) return null;
        int oldSize = bucket.size();
        V answer = bucket.remove(k);
        numEntries -= (oldSize - bucket.size());
        return answer;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (int h = 0; h < capacity; h++) {
            if (table[h] != null) {
                for (Entry<K, V> entry : table[h].entrySet()) {
                    buffer.add(entry);
                }
            }
        }
        return buffer;
    }
}