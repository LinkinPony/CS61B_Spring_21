package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    private T Max(T A, T B, Comparator<T> comp) {
        return comp.compare(A, B) >= 0 ? A : B;
    }

    private T Max(T A, T B) {
        return comparator.compare(A, B) >= 0 ? A : B;
    }

    public T max() {
        T result = null;
        for (T next : this) {
            if (result == null) result = next;
            else result = Max(result, next);
        }
        return result;
    }

    public T max(Comparator<T> c) {
        T result = null;
        for (T next : this) {
            if (result == null) result = next;
            else result = Max(result, next, c);
        }
        return result;
    }
}
