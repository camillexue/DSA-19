import java.util.HashMap;

public class LargestSubArray {
    static int[] largestSubarray(int[] nums) {
        int[] largest = {0,0};
        int length = nums.length;

        for (int x = 0; x < length; x++) {
            nums[x] = (nums[x] * 2) - 1; // change 0s to -1s
        }

        // calculate sums
        int[] sums = new int[length+1];
        sums[0] = nums[0];

        for (int x = 1; x < sums.length; x++) {
            sums[x] = sums[x-1] + nums[x-1];
//            System.out.println(sums[x]);
        }

        HashMap<Integer, Integer> indexSums = new HashMap<>();
        for(int i = 0; i < sums.length; i++) {
            // if there is already an index with that sum, calculate the
            // range between them, update largest if it's a larger range
            if(indexSums.containsKey(sums[i])) {
                int start = indexSums.get(sums[i]);
                int range = i - 1 - start;

                if (range > largest[1] - largest[0]) {
                    largest = new int[]{start, i-1};

                }
            } else { indexSums.put(sums[i], i); } // if it's not already in hashmap, add it
        }
        return largest;
    }
}
