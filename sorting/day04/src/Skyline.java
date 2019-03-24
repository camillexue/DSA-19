import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Skyline {

    static class Point {
        int x, y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Building {
        private int l, r, h;
        Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    public static List<Point> mergeSkylines(List<Point> A, List<Point> B) {
        int i = 0;
        int j = 0;

        List<Point> mergedSkyline = new ArrayList<>();
        int ALength = A.size();
        int BLength = B.size();

        while(i < ALength || j < BLength) {

            // x1 == x2, pick highest y
            if((i < ALength && j < BLength) && (A.get(i).x == B.get(j).x)) {
                int y = Math.max(A.get(i).y, B.get(j).y);
                mergedSkyline.add(new Point(A.get(i).x, y));
                i++;
                j++;
            }

            // x1 < x2 or there are no more points in B, choose x1
            else if ((j == BLength) || ((i < ALength) && A.get(i).x < B.get(j).x)) {
                if (j < 1) {
                    int y = A.get(i).y;
                    mergedSkyline.add(new Point(A.get(i).x, y));
                    i++;
                }
                else {
                    int y = Math.max(A.get(i).y, B.get(j-1).y);
                    mergedSkyline.add(new Point(A.get(i).x, y));
                    i++;
                }


            }

            // add point from B skyline
            else {
                int y = Math.max(B.get(j).y, A.get(i-1).y);
                mergedSkyline.add(new Point(B.get(j).x, y));
                j++;
            }
            // remove last point if point before it also has same y value
            int last = mergedSkyline.size() - 1;
            if(mergedSkyline.size() > 1 && (mergedSkyline.get(last).y == mergedSkyline.get(last -1).y)) {
                mergedSkyline.remove(last);
            }
        }
        return mergedSkyline;
    }
    public static List<Point> makeSkyline(Building[] B, int start, int end) {
        if(start == end) return new ArrayList(); // points are equal, empty skyline
        if(end - start == 1) return Arrays.asList(new Point(B[start].l, B[start].h), new Point(B[start].r, 0));
        int middle = (start + end) / 2;
        List<Point> leftSkyline = makeSkyline(B, start, middle);
        List<Point> rightSkyline = makeSkyline(B, middle, end);
        return mergeSkylines(leftSkyline, rightSkyline);


    }

    // Given an array of buildings, return a list of points representing the skyline
    public static List<Point> skyline(Building[] B) {
        int start = 0;
        int end = B.length;
        List<Point> result = makeSkyline(B, start, end);
        for(int i = 0; i < result.size(); i++) {
            System.out.print("("+ result.get(i).x + " " + result.get(i).y + ") ");
        }
        return result;
    }


}
