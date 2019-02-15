import java.util.*;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        int res = 0;

        Map<Integer, Integer> map = new HashMap<>();
        // for each possible boomerang vertex
        for(int i = 0; i < points.length; i++){

            // calculate distances with each other point
            for(int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                } else {
                    // calculate dist and increment val for that dist key
                    int dist = getDistance(points[i], points[j]);
                    map.put(dist, map.getOrDefault(dist, 0) + 1);
                }
            }
            for(int val : map.values()) {
                res += val * (val-1);   // sum up boomerangs for each vertex
                }
            map.clear();
        }
        return res;
    }

    public static int getDistance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];

        return dx*dx + dy*dy;
    }

}