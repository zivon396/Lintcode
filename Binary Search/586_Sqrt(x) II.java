public class Solution {
    /**
     * @param x: a double
     * @return: the square root of x
     */
    public double sqrt(double x) {
        // write your code here
        double start = 0, end = Math.max(1, x); // note: end is not x
        double eps = 1e-10;
        while(start + eps < end) {
            double mid = (start + end) / 2;
            if(mid * mid > x) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return start;
    }
}