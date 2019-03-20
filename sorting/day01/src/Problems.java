import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
//        for(double a : out) { System.out.println(a); }
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        int inputLength = inputStream.length;
        double[] runningMedian = new double[inputLength];
        PriorityQueue<Integer> before = maxPQ();
        PriorityQueue<Integer> after = minPQ();

        for (int i = 0; i < inputLength; i++) {
            int value = inputStream[i];
            if(after.isEmpty() || value <= after.peek()) {
                before.offer(value);
            }
            else {
                after.offer(value);
            }
            if(before.size() > after.size() + 1) {after.offer(before.poll());}
            if(before.size() < after.size()) {before.offer(after.poll());}

            if(before.size() > after.size()) {runningMedian[i] = before.peek(); }

            else {
                runningMedian[i] = (before.peek() + after.peek()) / 2.0;
            }

        }
//        for(double a : runningMedian) { System.out.println(a); }

        return runningMedian;
    }

}
