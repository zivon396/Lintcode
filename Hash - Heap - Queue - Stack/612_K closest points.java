/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
// 此题要用大顶堆, 所以 Comparator 里要输出 -diff
public class Solution {
    /**
     * @param points: a list of points
     * @param origin: a point
     * @param k: An integer
     * @return: the k closest points
     */
    public Point[] kClosest(Point[] points, Point origin, int k) {
        // write your code here
        Comparator<Point> disCom = new Comparator<Point> (){
            public int compare (Point left, Point right){
                int diff = getDis(left, origin) - getDis(right, origin);
                if (diff == 0){
                    diff = left.x - right.x;
                }
                if (diff == 0){
                    diff = left.y - right.y;
                }
                return -diff;
            }
        };
        
        Queue<Point> heap = new PriorityQueue<Point>(k, disCom);
        for (int i = 0; i < points.length; i++){
            heap.add(points[i]);
            if (heap.size() > k){
                heap.poll();
            }
        }
        
        Point[] res = new Point[k];
        int index = k;
        for (int i = 0; i < k; i++){
            res[--index] = heap.poll();
        }
        
        return res;
    }
    
    private int getDis (Point left, Point right){
        return (left.x - right.x) * (left.x - right.x) + (left.y - right.y) * (left.y - right.y);
    }
}
