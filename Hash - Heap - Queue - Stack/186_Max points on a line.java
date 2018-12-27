/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

public class Solution {
    private int max = 1;
    public int maxPoints(Point[] points){
        if (points == null || points.length == 0){
            return 0;
        }

        for (int i = 0; i < points.length; i++){
            Map<Double, Integer> hash = new HashMap<>();
            int num_same = 0;
            double slope = 0;
            for (int j = i + 1; j < points.length; j++){
                if ((points[i].y - points[j].y == 0) && (points[i].x - points[j].x == 0)){
                    num_same++;
                } else {
                    if (points[i].x - points[j].x == 0){
                        slope = (double) Integer.MAX_VALUE;
                    } else {
                        slope = (double)(points[i].y - points[j].y) / 
                                (double)(points[i].x - points[j].x);
                    }
                    hash.put(slope, hash.getOrDefault(slope, 1) + 1);
                }
            }
            
            for (int count: hash.values()){
                max = Math.max(max, count + num_same);
            }
            max = Math.max(max, num_same + 1);
        }
        
        return max;
    }
}