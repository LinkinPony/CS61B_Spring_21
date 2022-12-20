package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root = null;
    private int size = 0;

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    private class Node implements Comparable<Node> {
        K key;
        V value;
        Node left = null, right = null;

        Node(K k, V v) {
            key = k;
            value = v;
        }

        @Override
        public int compareTo(Node o) {
            return key.compareTo(o.key);
        }
    }

    private void clear(Node u) {
        if (u == null) return;
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
        return get(root, key) != null;
    }

    private Node get(Node u, K key) {
        if (u == null) return null;
        int comp = key.compareTo(u.key);
        if (comp > 0) {
            return get(u.right, key);
        } else if (comp == 0) {
            return u;
        } else {
            return get(u.left, key);
        }
    }
    private Node get(Node u,K key,V value){
        if (u == null) return null;
        int comp = key.compareTo(u.key);
        if (comp > 0) {
            return get(u.right, key);
        } else if (comp == 0 && u.value == value) {
            return u;
        } else {
            return get(u.left, key);
        }
    }
    @Override
    public V get(K key) {
        Node result = get(root, key);
        return (result == null ? null : result.value);
    }

    @Override
    public int size() {
        return size;
    }

    private Node insert(Node u, K key, V value) {
        if (u == null) {
            size++;
            return new Node(key, value);
        }
        int comp = key.compareTo(u.key);
        if (comp > 0) {
            u.right = insert(u.right, key, value);
        } else if (comp == 0) {
            u.value = value;
        } else {
            u.left = insert(u.left, key, value);
        }
        return u;
    }

    @Override
    public void put(K key, V value) {
        if (root == null) {
            size++;
            root = new Node(key, value);
        } else {
            insert(root, key, value);
        }
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }
    private Node getBiggest(Node u){
        if(u == null)return null;
        Node result = getBiggest(u.right);
        return (result == null?u:result);
    }
    private void remove(Node u){
        if(u == null)return;
        Node nextu = null;
        int leafCnt = 0;
        if (u.left != null) {
            leafCnt++;
        }
        if (u.right != null) {
            leafCnt++;
        }
        if(leafCnt == 0){
            if(u == root){
                root = null;
            }
            u = null;
        }
        else if(leafCnt == 1){
            if(u.right != null){
                if(u == root){
                    root = u.right;
                }
                u = u.right;
            }
            else {
                if(u == root){
                    root = u.left;
                }
                u = u.left;
            }
        }
        else{
            Node l = u.left,r = u.right;
            Node nxt = getBiggest(u.left);
            if(u == root){
                root = nxt;
            }
            u = nxt;
            if(l != nxt)u.left = l;
            u.right = r;
        }
        size--;
    }
    @Override
    public V remove(K key) {
        Node u = get(root, key);
        V value = u.value;
        remove(u);
        return value;
    }
    @Override
    public V remove(K key, V value) {
        Node u = get(root, key, value);
        V resValue = u.value;
        remove(u);
        return resValue;
    }

    private void printInOrder(Node u) {
        if (u == null) return;
        printInOrder(u.left);
        System.out.println("{" + u.key + "," + u.value + "}");
        printInOrder(u.right);
    }

    public void printInOrder() {
        printInOrder(root);
    }

}
