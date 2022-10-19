package IntList;

import static org.junit.Assert.*;

import jh61b.junit.In;
import org.junit.Test;

import java.util.Random;
public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        {
            IntList lst = IntList.of(14, 15, 16, 17, 18);
            boolean changed = IntListExercises.squarePrimes(lst);
            assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
            assertTrue(changed);
        }
        {
            IntList lst = IntList.of(4,6,8);
            boolean changed = IntListExercises.squarePrimes(lst);
            assertEquals("4 -> 6 -> 8", lst.toString());
            assertFalse(changed);
        }
        {
            IntList lst = IntList.of(1, 15, 16, 16, 18);
            boolean changed = IntListExercises.squarePrimes(lst);
            assertEquals("1 -> 15 -> 16 -> 16 -> 18", lst.toString());
            assertFalse(changed);
        }
    }
    @Test
    public void testSquarePrimesRandom(){
        Random rand = new Random();
        final int maxn = 1005;
        boolean [] not_prime = new boolean[maxn];
        not_prime[1] = true;
        for(int i = 2;i < maxn;i++){
            if(!not_prime[i]){
                for(int j = i + i;j < maxn;j += i){
                    not_prime[j] = true;
                }
            }
        }
        for(int i = 1;i < maxn;i++){
            boolean result = Primes.isPrime(i);
            if(!not_prime[i] != result)System.out.println(i);
            assertEquals(!not_prime[i],result);
        }
        for(int loop = 1;loop <= 100;loop++){
            int len = 10;
            int [] input = new int[len];
            int [] expected = new int[len];
            for(int i = 0;i < len;i++)input[i] = Math.max(1,rand.nextInt(maxn - 1));
//            for(int i = 0;i < len;i++)input[i] = i + 1;
            for(int i = 0;i < len;i++){
                expected[i] = (not_prime[input[i]])?input[i]:input[i]*input[i];
            }
            boolean changed = false;
            for(int i = 0;i < len;i++){
                if (input[i] != expected[i]) {
                    changed = true;
                    break;
                }
            }
            IntList input_list = IntList.of(input);
            IntList expected_list = IntList.of(expected);
            boolean result_changed = IntListExercises.squarePrimes(input_list);
            assertEquals(expected_list.toString(),input_list.toString());
            assertEquals(result_changed,changed);
        }
    }
}
