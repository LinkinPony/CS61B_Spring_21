package deque;

import jh61b.junit.In;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ArrayDequeTest {
    @Test
    public void smallRemoveFirstTest() {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        int[] input = new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        int[] expect = new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        for (int x : input) dq.addLast(x);
        int[] result = new int[5];
        for (int i = 0; i <= 4; i++) result[i] = dq.removeFirst();
        for (int i = 0; i <= 4; i++) {
            assertEquals(result[i], expect[i]);
        }
    }

    /**
     * return random int in range [0,n)
     */
    private int Rand(int n) {
        Random rand = new Random();
        return Math.abs(rand.nextInt()) % n;
    }

    @Test
    public void randomAddRemoveTest() {
        java.util.ArrayDeque<Integer> standard = new java.util.ArrayDeque<>();
        ArrayDeque<Integer> test = new ArrayDeque<>();
        int testCase = 1000000;
        for (int i = 0; i < testCase; i++) {
            int opt = Rand(4);
            if(standard.size() == 0 || test.size() == 0)opt %= 2;
            if (opt == 0) {
                int v = Rand(1000);
                standard.addLast(v);
                test.addLast(v);
                assertEquals("addLast | Deque's size not equal", standard.size(), test.size());
            } else if (opt == 1) {
                int v = Rand(1000);
                standard.addFirst(v);
                test.addFirst(v);
                assertEquals("addFirst | Deque's size not equal", standard.size(), test.size());
            } else if (opt == 2) {
                Integer v1 = standard.removeFirst();
                Integer v2 = test.removeFirst();
                int x = 1;
                assertEquals("removeFirst | values not equal", v1, v2);
            } else if (opt == 3) {
                Integer v1 = standard.removeLast();
                Integer v2 = test.removeLast();
                int x = 1;
                assertEquals("removeLast | values not equal", v1, v2);
            }
        }
    }

    @Test
    public void smallRemoveLastTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 10; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 5; i++) {
            double lst = lld1.removeFirst();

            assertEquals("Should have the same value", i, (double) lst, 0.0);
        }

        for (double i = 9; i > 5; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }

    }

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String> lld1 = new ArrayDeque<String>();
        ArrayDeque<Double> lld2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertNull("Should return null when removeFirst is called on an empty Deque,", lld1.removeFirst());
        assertNull("Should return null when removeLast is called on an empty Deque,", lld1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }

    }

    @Test
    //TODO: finish and simplify this test
    public void equalsTest() {
        {
            int[] inputa = {1, 2, 3, 4, 5};
            int[] inputb = {5, 4, 3, 2, 1};

            ArrayDeque<Integer> LLDa = new ArrayDeque<>();
            ArrayDeque<Integer> LLDb = new ArrayDeque<>();
            for (int j : inputa) LLDa.addLast(j);
            for (int j : inputb) LLDb.addLast(j);
            assertNotEquals("euqals error", LLDa, LLDb);
        }
        {
            int[] inputa = {1, 2, 3, 4, 5};
            int[] inputb = {1, 2, 3, 4, 5};

            ArrayDeque<Integer> LLDa = new ArrayDeque<>();
            ArrayDeque<Integer> LLDb = new ArrayDeque<>();
            for (int j : inputa) LLDa.addLast(j);
            for (int j : inputb) LLDb.addLast(j);
            assertEquals("euqals error", LLDa, LLDb);
        }
        {
            char[] inputa = {1, 2, 3, 4, 5};
            int[] inputb = {1, 2, 3, 4, 5};

            ArrayDeque<Character> LLDa = new ArrayDeque<>();
            ArrayDeque<Integer> LLDb = new ArrayDeque<>();
            for (char j : inputa) LLDa.addLast(j);
            for (int j : inputb) LLDb.addLast(j);
            assertNotEquals("euqals error", LLDa, LLDb);
        }
        {
            //Use A.equals(B) instead of ==
            char[] inputa = {'a', 'b', 'c'};
            char[] inputb = {'a', 'b', 'c'};
            ArrayDeque<String> LLDa = new ArrayDeque<>();
            ArrayDeque<String> LLDb = new ArrayDeque<>();
            for (char j : inputa) LLDa.addLast(String.valueOf(j));
            for (char j : inputb) LLDb.addLast(String.valueOf(j));
            assertEquals("String euqals error", LLDa, LLDb);
        }
    }
}
