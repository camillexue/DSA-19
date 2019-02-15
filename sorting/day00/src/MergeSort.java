import java.util.*;

public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;
    private InsertionSort insertionSort = new InsertionSort();

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime: O(1)
     * Worst-case runtime: O(NlogN)
     * Average-case runtime: O(NlogN)
     *
     * Space-complexity: O(N)
     */
    @Override
    public int[] sort(int[] array) {
        if(array.length < INSERTION_THRESHOLD) {
            return insertionSort.sort(array);
        }
        int half = array.length/2;
        int[] lefthalf = new int[half];
        int[] righthalf = new int[array.length-half];

        System.arraycopy(array, 0, lefthalf, 0, half);
        System.arraycopy(array, half, righthalf, 0, array.length - half);

        int[] sortedLeft = sort(lefthalf);
        int[] sortedRight = sort(righthalf);

        array = merge(sortedLeft, sortedRight);

        return array;
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        int[] array = new int[a.length + b.length];
        int i = 0;
        int j = 0;

        for(int k = 0; k < array.length; k++) {
            if(i == a.length){
                array[k] = b[j];
                j++;
            }
            else if (j == b.length) {
                array[k] = a[i];
                i++;
            }
            else if(a[i] < b[j]) {
                array[k] = a[i];
                i++;
            }
            else {
                array[k] = b[j];
                j++;
            }
        }
        return array;
    }

}
