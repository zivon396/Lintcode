public class Solution {
    /**
     * @param dividend: the dividend
     * @param divisor: the divisor
     * @return: the result
     */
    // 本质上和 Pow(x, n) 是一样的, 都是把 n 分解为 2的幂的和. 不同之处在于 Pow(x, n)是从低到高(次幂)来找, 而本题是从高到低来找.
    // Integer.MIN_VALUE 在取绝对值的时候会溢出，所以要用 long
    // 注意 dividend = Integer.MIN_VALUE, divisor = -1 的时候, 不断累加左移的 shift 最终会越界.
    // 时间复杂度: 最坏 (log(dividend/divisor))², 最好 log(dividend/divisor). 
    public int divide(int dividend, int divisor) {
        // write your code here
        if (divisor == 0){
            return dividend >= 0? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        if (dividend == 0){
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        
        boolean isNegative = (dividend < 0 && divisor > 0) || 
                             (dividend > 0 && divisor < 0);
        
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        int result = 0;
        
        while (a >= b){
            int shift = 0;
            while (a >= b << shift){
                shift++;
            }
            a -= b << (shift - 1);
            result += 1 << (shift - 1);
        }
        
        return isNegative? -result : result;
    }
}

// 下面方法也可以
public class Solution {
    /**
     * @param dividend: the dividend
     * @param divisor: the divisor
     * @return: the result
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 0){
            return dividend >= 0? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        if (dividend == 0){
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        
        boolean isNegative = (dividend < 0 && divisor > 0) || 
                             (dividend > 0 && divisor < 0);
        
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        if (a < b){
            return 0;
        }
        int result = 0;

        long shift = 1;
        while (b <= a){
            b <<= 1;
            shift <<= 1;
        }
        b >>= 1;
        shift >>= 1;

        while (a > 0 && b > 0){
            result += shift;
            a -= b;
            while (a < b){
                b >>= 1;
                shift >>= 1;
            }
        }

        // System.out.println(result);
        return isNegative? -result : result;
    }
}
