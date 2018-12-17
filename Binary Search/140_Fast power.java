public class Solution {
    /**
     * @param a: A 32bit integer
     * @param b: A 32bit integer
     * @param n: A 32bit integer
     * @return: An integer
     */
    public int fastPower(int a, int b, int n) {
        // write your code here
        if (b == 1){
            return 0;
        }
        
        long res = 1, tmp = a;
        while (n >= 1){
            if (n % 2 == 1){
                res = (res * tmp) % b;
            }
            tmp = (tmp * tmp) % b;
            n >>= 1;
        }
        
        return (int) res % b;
    }
}