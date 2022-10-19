package DebugExercise;
import static org.junit.Assert.*;

import org.apache.commons.math3.random.RandomGenerator;
import org.junit.Test;

import java.util.Random;

public class Debug2Test{
    @Test
    public void testArraySum(){
        int [] input = new int [] {1,2,3,4,5,6};
        int expected = 21;
        int result = DebugExercise2.arraySum(input);
        assertEquals(expected,result);
    }
    @Test
    public void testArrayMax(){
        int [] input1 = new int [] {1,2,3,4,5,6};
        int [] intpu2 = new int [] {-1,5,3,6,2,11};
        int [] expected = new int [] {1,5,3,6,5,11};
        int [] result = DebugExercise2.arrayMax(input1,intpu2);
        assertArrayEquals(expected,result);
    }
    @Test
    public void testAdd(){
        Random rand = new Random();
        for(int i = 1;i <= 1000;i++){
            int a = rand.nextInt(),b = rand.nextInt();
            assertEquals(DebugExercise2.add(a,b),a+b);
        }
    }
    @Test
    public void testMax(){
        Random rand = new Random();
        for(int i = 1;i <= 1000;i++){
            int a = rand.nextInt(),b = rand.nextInt();
            assertEquals(DebugExercise2.max(a,b),Math.max(a,b));
        }
    }
}