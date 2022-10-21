package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private final int initSize = 3;
    /**
     * expand array's size expandFactor times.
     */
    private final double expandFactor = 2;
    private final double shrinkFactor = 0.5;
    /**
     * shrink array's size when array.length/size is not greater than shrinkLimit.
     */
    private final double shrinkLimit = 0.25;
    private T[] array = (T[]) new Object[initSize];
    /**
     * the first and last elements' position in array.Alwasy ensure that front <= end.
     */
    private int front = 0, end = 0;
    /**
     * number of elements in ArrayDeque
     */
    private int size = 0;

    private class ArrayDequeIterator implements Iterator<T> {
        int L, R;

        ArrayDequeIterator(int front, int end) {
            L = front;
            R = end;
        }

        @Override
        public boolean hasNext() {
            return L != ((R + 1) % array.length);
        }

        @Override
        public T next() {
            int resL = L;
            L++;
            if (L == array.length) L = 0;
            return array[resL];
        }
    }

    /**
     * resize array to fit new capacity.
     */
    private void resize(int capactiy) {
        assert (capactiy >= size);
        T [] newArray = (T[]) new Object[capactiy];
        //reuse space
        if (front > end) {
//            System.arraycopy(array, front, newArray, 0, array.length - front);
//            System.arraycopy(array, 0, newArray, array.length - front + 1, end + 1);
            for(int i = front;i < array.length;i++)newArray[i - front] = array[i];
            for(int i = 0;i <= end;i++)newArray[array.length - front + i] = array[i];
        } else {
            for(int i = front;i <= end;i++)newArray[i - front] = array[i];
//            System.arraycopy(array, front, newArray, 0, size);
        }
        front = 0;
        end = size - 1;
        array = newArray;
    }

    /**
     * expand to size * 2 if array is full.
     */
    private void expand() {
        resize(Math.max(size, (int) (array.length * expandFactor)));
    }

    private void shrink() {
        resize(Math.max(size, (int) (array.length * shrinkFactor)));
    }

    @Override
    public void addFirst(T item) {
        if (size == array.length) {
            expand();
        }
        if(size > 0)front--;
        size++;
        if (front < 0) front = array.length - 1;
        array[front] = item;
    }

    @Override
    public void addLast(T item) {
        if (size == array.length) {
            expand();
        }
        if(size > 0)end++;
        size++;
        if (end == array.length) end = 0;
        array[end] = item;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int idx = front;
        while (idx != end) {
            System.out.print(array[idx]);
            System.out.print(" ");
            idx++;
            if(idx == array.length)idx = 0;
        }
        System.out.println(array[end]);
    }

    @Override
    public T removeFirst() {
        if(size == 0)return null;
        size--;
        T res = array[front];
        array[front] = null;
        if(size > 0)front++;
        if (front == array.length) front = 0;
        if (size > 0 && (float) array.length / size < shrinkLimit) shrink();
        return res;
    }

    @Override
    public T removeLast() {
        if(size == 0)return null;
        size--;
        T res = array[end];
        array[end] = null;
        if(size > 0)end--;
        if (end < 0) end = array.length - 1;
        if (size > 0 && (float) array.length / size < shrinkLimit) shrink();
        return res;
    }

    @Override
    public T get(int index) {
        return array[(index + front) % array.length];
    }


    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator(front, end);
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        else if (!(o instanceof Deque)) {
            return false;
        } else {
            boolean ok = true;
            Deque<?> tar = (Deque<?>) o;
            if (tar.size() != this.size()) ok = false;
            else {
                Iterator<T> itThis = this.iterator();
                Iterator<?> itTar = tar.iterator();
                while (itTar.hasNext() && itThis.hasNext()) {
                    if (!itTar.next().equals(itThis.next())) {
                        ok = false;
                        break;
                    }
                }
            }
            return ok;
        }
    }
}
