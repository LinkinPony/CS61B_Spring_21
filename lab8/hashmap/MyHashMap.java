package hashmap;

import org.w3c.dom.Node;

import java.util.*;

/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 * <p>
 * Assumes null keys will never be inserted, and does not resize down upon remove().
 *
 * @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private HashSet<K> keys = new HashSet<>();
    private static int INIT_SIZE = 7;
    private int size = INIT_SIZE;
    private int elementCount = 0;
    private double factor = 0.75;

    /**
     * Constructors
     */
    public MyHashMap() {
        buckets = createTable(size);
    }

    public MyHashMap(int initialSize) {
        size = initialSize;
        buckets = createTable(size);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad     maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        size = initialSize;
        factor = maxLoad;
        buckets = createTable(size);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * 1. Insert items (`add` method)
     * 2. Remove items (`remove` method)
     * 3. Iterate through items (`iterator` method)
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<Node>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }

    private int hash(K key) {
        int v = key.hashCode();
        return Math.floorMod(v,size);
    }

    private int hash(K key, int mod) {
        int v = key.hashCode();
        return Math.floorMod(v,mod);
    }

    private void expand() {
        int newSize = size * 2;
        Collection<Node>[] newBuckets = createTable(newSize);
        for (int i = 0; i < size; i++) {
            for (Node u : buckets[i]) {
                int h = hash(u.key, newSize);
                newBuckets[h].add(u);
            }
        }
        size = newSize;
        buckets = newBuckets;
    }
    // Your code won't compile until you do so!

    @Override
    public void clear() {
        size = INIT_SIZE;
        elementCount = 0;
        keys.clear();
        buckets = createTable(size);
    }

    @Override
    public boolean containsKey(K key) {
        int h = hash(key);
        for (Node it : buckets[h]) {
            if (it.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int h = hash(key);
        for (Node it : buckets[h]) {
            if (it.key.equals(key)) {
                return it.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return elementCount;
    }

    @Override
    public void put(K key, V value) {
        if(key == null)return;
        int h = hash(key);
        boolean find = false;
        Iterator<Node> it = buckets[h].iterator();
        while (it.hasNext()) {
            Node u = it.next();
            if (u.key.equals(key)) {
                u.value = value;
                find = true;
                break;
            }
        }
        if(!find){
            Node u = createNode(key, value);
            buckets[h].add(u);
            keys.add(u.key);
            elementCount++;
            if(elementCount > size * factor){
                expand();
            }
        }
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public V remove(K key) {
        int h = hash(key);
        V value = null;
        Iterator<Node> it = buckets[h].iterator();
        while (it.hasNext()) {
            Node u = it.next();
            K curKey = u.key;
            if (curKey.equals(key)) {
                value = u.value;
                keys.remove(key);
                it.remove();
                elementCount--;
                break;
            }
        }
        return value;
    }

    @Override
    public V remove(K key, V value) {
        int h = hash(key);
        V returnValue = null;
        Iterator<Node> it = buckets[h].iterator();
        while (it.hasNext()) {
            K curKey = it.next().key;
            V curValue = it.next().value;
            if (curKey.equals(key) && curValue.equals(value)) {
                returnValue = it.next().value;
                keys.remove(key);
                it.remove();
                elementCount--;
                break;
            }
        }
        return returnValue;
    }

    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }
}
