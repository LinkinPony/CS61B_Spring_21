package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Random;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        Random rand = new Random();
        AList<Integer> Ns = new AList<>();
        for(int i = 1000;i <= (1<<16);i *= 2)Ns.addLast(i);
        AList<Double> times = new AList<>();
        AList<Integer>ops = new AList<>();
        for(int i = 0;i < Ns.size();i++){
            int op = 1000;
            SLList<Integer>testSLList = new SLList<>();
            for(int j = 0;j < Ns.get(i);j++)testSLList.addLast(rand.nextInt());
            Stopwatch sw = new Stopwatch();
            int n = Ns.get(i);
            while(n-- > 0){
                testSLList.getLast();
            }
            times.addLast(sw.elapsedTime());
            ops.addLast(op);
        }
        printTimingTable(Ns,times,Ns);
    }

}
