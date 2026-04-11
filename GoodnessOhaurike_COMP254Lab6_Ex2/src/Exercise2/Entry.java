package Exercise2;

/** Interface for a key-value pair entry. */
public interface Entry<K, V> {
    K getKey();
    V getValue();
    V setValue(V value); // Returns the old value
}