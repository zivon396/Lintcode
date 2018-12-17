public class Solution {
    /**
     * @param x: the base number
     * @param n: the power number
     * @return: the result
     */
    public double myPow(double x, int n) {
        // write your code here
        if (x == 0 || x == 1 || n == 1){
            return x;
        }
        
        long m = n;
        if (n < 0){
            m = -m;
            x = 1.0 / x;
        }
        
        int tail = 1;
        double res = 1;
        double temp = x;
        
        while (tail <= m){
            if ((m & 1) == 1){
                res *= temp;
            }
            temp *= temp;
            m >>= 1;
        }
        
        return res;
    }
}