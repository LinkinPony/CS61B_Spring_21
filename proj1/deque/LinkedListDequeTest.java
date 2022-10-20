package deque;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Performs some basic linked list tests.
 */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

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

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
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

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
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
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double> lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertNull("Should return null when removeFirst is called on an empty Deque,", lld1.removeFirst());
        assertNull("Should return null when removeLast is called on an empty Deque,", lld1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
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

            LinkedListDeque<Integer> LLDa = new LinkedListDeque<>();
            LinkedListDeque<Integer> LLDb = new LinkedListDeque<>();
            for (int j : inputa) LLDa.addLast(j);
            for (int j : inputb) LLDb.addLast(j);
            assertNotEquals("euqals error", LLDa, LLDb);
        }
        {
            int[] inputa = {1, 2, 3, 4, 5};
            int[] inputb = {1, 2, 3, 4, 5};

            LinkedListDeque<Integer> LLDa = new LinkedListDeque<>();
            LinkedListDeque<Integer> LLDb = new LinkedListDeque<>();
            for (int j : inputa) LLDa.addLast(j);
            for (int j : inputb) LLDb.addLast(j);
            assertEquals("euqals error", LLDa, LLDb);
        }
        {
            char[] inputa = {1, 2, 3, 4, 5};
            int[] inputb = {1, 2, 3, 4, 5};

            LinkedListDeque<Character> LLDa = new LinkedListDeque<>();
            LinkedListDeque<Integer> LLDb = new LinkedListDeque<>();
            for (char j : inputa) LLDa.addLast(j);
            for (int j : inputb) LLDb.addLast(j);
            assertNotEquals("euqals error", LLDa, LLDb);
        }
        {
            //Use A.equals(B) instead of ==
            char [] inputa = {'a','b','c'};
            char [] inputb = {'a','b','c'};
            LinkedListDeque<String> LLDa = new LinkedListDeque<>();
            LinkedListDeque<String> LLDb = new LinkedListDeque<>();
            for (char j : inputa) LLDa.addLast(String.valueOf(j));
            for (char j : inputb) LLDb.addLast(String.valueOf(j));
            assertEquals("String euqals error", LLDa, LLDb);
        }
    }
}
