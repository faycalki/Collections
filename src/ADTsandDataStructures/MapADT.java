package ADTsandDataStructures;
import java.util.Collection;
import java.util.Set;
import java.util.Iterator;

/**
 * An interface representing a map that associates keys with values.
 * A map provides (K = key, V = value) pairs, mapping the key onto the value. Keys are distinct and can't be null.
 * All methods should throw IllegalArgumentException if passed a null key argument.
 * No value can be null, hence, any returns by put, get, or remove does not necessarily mean that an entry did not exist.
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public interface MapADT<K, V> extends Iterable<MapADT.MapEntryADT<K, V>>{

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    int size();

    /**
     * Returns true if this map contains no key-value mappings.
     *
     * @return true if this map contains no key-value mappings
     */
    boolean isEmpty();

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key the key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */
    boolean contains(K key);

    /**
     * Returns the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped,
     *         or null if this map contains no mapping for the key
     */
    V get(K key);

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the previous value associated with the key, or null if there was no mapping for the key
     */
    V put(K key, V value);

    /**
     * Removes the mapping for a key from this map if it is present.
     *
     * @param key the key whose mapping is to be removed from the map
     * @return the previous value associated with the key, or null if there was no mapping for the key
     */
    V remove(K key);

    /**
     * Returns a Set view of the keys contained in this map. This method is an additional resource for learning.
     *
     * @return a Set view of the keys contained in this map
     */
    Set<K> keySet();

    /**
     * Returns a Collection view of the values contained in this map. This method is an additional resource for learning.
     *
     * @return a Collection view of the values contained in this map
     */
    Collection<V> values();

    /**
     * Returns a Set view of the key-value mappings contained in this map.
     *
     * @return a Set view of the key-value mappings contained in this map
     */
    Set<MapADT.MapEntryADT<K, V>> entrySet();

    /**
     * An interface representing an entry in a map.
     *
     * @param <K> the type of keys in the entry
     * @param <V> the type of values in the entry
     */
    interface MapEntryADT<K, V> {

        /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry
         */
        K getKey();

        /**
         * Returns the value corresponding to this entry.
         *
         * @return the value corresponding to this entry
         */
        V getValue();

        /**
         * Sets the value corresponding to the entry
         *
         * @param v the value to set it to
         */
        void setValue(V v);

        /**
         * Returns a string representing this Entry
         * @return the entry as a textual representation
         */
        String toString();
    }

    /**
     * Returns true if this map is considered "full".
     *
     * @return true if this map is considered "full"
     */
    boolean isFull();
}
