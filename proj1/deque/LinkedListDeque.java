package deque;

import java.lang.reflect.ParameterizedType;
import java.util.*;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        T value;
        Node prev, next;

        Node(T val, Node pre, Node nxt) {
            value = val;
            prev = pre;
            next = nxt;
        }
    }

    private class LLDequeIterator implements Iterator<T> {
        Node cur;

        LLDequeIterator(Node u) {
            cur = u;
        }

        @Override
        public boolean hasNext() {
            return cur != sentinel;
        }

        @Override
        public T next() {
            T value = cur.value;
            cur = cur.next;
            return value;
        }
    }

    /**
     * Circular Sentinel.<br>
     * sentinel.next -> first node.<br>
     * sentinel.prev -> last node.<br>
     * In any time, sentinel must not be null. And if deque is empty, sentinel is both the first and the last node.
     */
    private final Node sentinel;
    private int size;


    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel.prev = sentinel;
    }

    @Override
    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node newNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node cur = sentinel.next;
        boolean space = false;
        while (cur != sentinel) {
            if (space) System.out.print(" ");
            space = true;
            System.out.print(cur.value);
            cur = cur.next;
        }
        System.out.println();
    }

    @Override
    /* TODO: ensure there is no memory leak */
    public T removeFirst() {
        if (size > 0) size--;
        else return null;
        T value = sentinel.next.value;
        Node delNode = sentinel.next;
        sentinel.next = delNode.next;
        delNode.next.prev = sentinel;
        return value;
    }

    @Override
    public T removeLast() {
        if (size > 0) size--;
        else return null;
        T value = sentinel.prev.value;
        Node delNode = sentinel.prev;
        sentinel.prev = delNode.prev;
        delNode.prev.next = sentinel;
        return value;
    }

    @Override
    public T get(int index) {
        if (index < 0) return null;
        int count = 0;
        Node cur = sentinel.next;
        while (count < index && cur != sentinel) {
            count++;
            cur = cur.next;
        }
        if (count != index || cur == sentinel) return null;
        else return cur.value;
    }

    private T getRecursive(int index, Node cur) {
        if (index == 0) return cur.value;
        return getRecursive(index - 1, cur.next);
    }

    public T getRecursive(int index) {
        if (index < 0) return null;
        return getRecursive(index, sentinel.next);
    }

    @Override
    public Iterator<T> iterator() {
        return new LLDequeIterator(sentinel.next);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        else if (!(o instanceof Deque)) {
            return false;
        } else {
            boolean ok = true;
            Deque tar = (Deque) o;
            if (tar.size() != this.size) ok = false;
            else {
                for (int i = 0; i < tar.size(); i++) {
                    if (!tar.get(i).equals(this.get(i))) return false;
                }
            }
            //TODO: Seems this can't pass autograder (or -Xlint?). But method above is extremely slow.
            //      Find a good way to solve it.
//            if (tar.size() != this.size()) ok = false;
//            else {
//                Iterator<T> itThis = this.iterator();
//                Iterator itTar = tar.iterator();
//                while (itTar.hasNext() && itThis.hasNext()) {
//                    if (!itTar.next().equals(itThis.next())) {
//                        ok = false;
//                        break;
//                    }
//                }
//            }
            return ok;
        }
    }
}
