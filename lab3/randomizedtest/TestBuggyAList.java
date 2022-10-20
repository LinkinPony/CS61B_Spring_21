package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        int n = 3;
        Random rand = new Random();
        AListNoResizing<Integer> noResAList = new AListNoResizing<>();
        BuggyAList<Integer> bugAList = new BuggyAList<>();
        for(int i = 0;i < n;i++){
            int r = rand.nextInt();
            noResAList.addLast(r);
            bugAList.addLast(r);
        }
        for(int i = 0;i < n;i++){
            noResAList.removeLast();
            bugAList.removeLast();
            for(int j = 0;j < noResAList.size();j++){
                int x = noResAList.get(j),y = bugAList.get(j);
                assertEquals("Not euqal at " + j + ", noResAList = " + x + ", bugAList = " + y, x, y);
            }

        }
    }
    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer>BL = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0 || !(L.size() > 0 && BL.size() > 0)) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                BL.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int Bsize = BL.size();
                assertEquals("L's size: " + size + ", BL's size: " + Bsize,size,Bsize);
//                System.out.println("size: " + size);
            }
            else if (operationNumber == 2){
                int Llast = L.removeLast();
                int BLlast = BL.removeLast();
                assertEquals("L's last: " + Llast + ", BL's last: " + BLlast,Llast,BLlast);
//                System.out.println("Remove last: " + L.removeLast());
            }
        }
    }
}
