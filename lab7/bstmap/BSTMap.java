package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K, V> {

    private Node root = null;
    private int size = 0;
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    private class Node implements Comparable<Node>{
        K key;
        V value;
        Node left = null,right = null;
        Node(K k,V v){
            key = k;
            value = v;
        }

        @Override
        public int compareTo(Node o) {
            return key.compareTo(o.key);
        }
    }
    private void clear(Node u){
        if(u == null)return;
        clear(u.left);
        clear(u.right);
        u.left = null;
        u.right = null;
    }
    @Override
    public void clear() {
        clear(root);
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return get(root,key) != null;
    }
    private Node get(Node u,K key){
        if(u == null)return null;
        int comp = key.compareTo(u.key);
        if(comp > 0){
            return get(u.right,key);
        }
        else if(comp == 0){
            return u;
        }
        else {
            return get(u.left,key);
        }
    }
    @Override
    public V get(K key) {
        Node result = get(root,key);
        return (result == null?null:result.value);
    }

    @Override
    public int size() {
        return size;
    }
    private Node insert(Node u,K key,V value){
        if(u == null){
            size++;
            return new Node(key,value);
        }
        int comp = key.compareTo(u.key);
        if(comp > 0){
            return u.right = insert(u.right,key,value);
        }
        else if(comp == 0){
            return u;
        }
        else {
            return u.left = insert(u.left,key,value);
        }
    }
    @Override
    public void put(K key, V value) {
        if(root == null){
            size++;
            root = new Node(key,value);
        }
        else{
            insert(root,key,value);
        }
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }


}
