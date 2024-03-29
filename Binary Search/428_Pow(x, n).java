// 方法 1: m 右移 - 这里的 tail 从始至终就是 1, 所以其实可以不需要它.
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
        
        // while (m > 0)
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

// 方法 2: tail 左移 - 注意此时 tail 需要是 long, 不然会越界.
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
        
        long tail = 1;
        double res = 1;
        double temp = x;
        
        while (tail <= m){
            if ((tail & m) != 0){
                res *= temp;
            }
            temp *= temp;
            tail <<= 1;
        }
        
        return res;
    }
}
