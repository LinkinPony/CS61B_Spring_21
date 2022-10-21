package gh2;

// TODO: uncomment the following import once you're ready to start this portion
// import deque.Deque;
// TODO: maybe more imports

import deque.ArrayDeque;
import deque.Deque;
import deque.LinkedListDeque;
import edu.princeton.cs.algs4.StdAudio;

//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /**
     * Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday.
     */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor
    private static double last;//last dequeued double
    /* Buffer for storing sound data. */
    private Deque<Double> buffer;

    public GuitarString(double frequency) {
        buffer = new LinkedListDeque<Double>();
        int size = (int) (Math.round(SR / frequency));
        for (int i = 0; i < size; i++) buffer.addLast(0.0);
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        int n = buffer.size();
        for (int i = 0; i < n; i++) {
            buffer.removeLast();
            buffer.addFirst(Math.random() - 0.5);
        }
        last = buffer.get(0);
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        last = buffer.removeFirst();
        buffer.addLast((last + buffer.get(0)) / 2 * DECAY);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return last;
    }
}
