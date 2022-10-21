package deque;

import com.google.common.collect.BoundType;
import com.google.common.collect.TreeMultiset;
import jh61b.junit.In;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest {
    private int Rand(int n) {
        Random rand = new Random();
        return Math.abs(rand.nextInt()) % n;
    }
    @Test
    public void randomAddRemoveMaxTest(){
        TreeMultiset<Integer> set = TreeMultiset.create();
        MaxArrayDeque<Integer> test = new MaxArrayDeque<>(Comparator.<Integer>naturalOrder());
        int testCase = 100000;
        for (int i = 0; i < testCase; i++) {
            int opt = Rand(4);
            if(test.size() == 0)opt %= 2;
            if (opt == 0) {
                int v = Rand(1000);
                set.add(v);
                test.addLast(v);
                assertEquals("addLast | Deque's size not equal", set.size(), test.size());
            } else if (opt == 1) {
                int v = Rand(1000);
                set.add(v);
                test.addFirst(v);
                assertEquals("addFirst | Deque's size not equal", set.size(), test.size());
            } else if (opt == 2) {
                Integer v2 = test.removeFirst();
                Integer v1 = set.tailMultiset(v2, BoundType.CLOSED).firstEntry().getElement();
                set.remove(v1,1);
                int x = 1;
                assertEquals("removeFirst | values not equal", v1, v2);
            } else if (opt == 3) {
                Integer v2 = test.removeLast();
                Integer v1 = set.tailMultiset(v2, BoundType.CLOSED).firstEntry().getElement();
                set.remove(v1,1);
                int x = 1;
                assertEquals("removeLast | values not equal", v1, v2);
            }
            else if(opt == 4){
                Integer v1 = set.lastEntry().getElement();
                Integer v2 = test.max();
                assertEquals("max | valuse not equal",v1,v2);
            }
        }
    }
}
