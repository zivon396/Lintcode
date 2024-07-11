public class Solution {
    /**
     * @param n: the integer to be reversed
     * @return: the reversed integer
     */
    public int reverseInteger(int n) {
        // write your code here
        if (Math.abs(n) < 10){
            return n;
        }

        int zeros = 0;
        int m = n;
        while (m / 10 != 0){
            m = m / 10;
            zeros++;
        }

        long sum = 0;
        int rem = 0;
        while (n != 0){
            rem = n % 10;
            n = n / 10;
            sum += rem * Math.pow(10, zeros);
            zeros--;
        }

        return sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE ? 0 : (int) sum;
    }
}

// 九章答案
// 把 *10 和 /10 放在同一个 while 里
public class Solution {
    /**
     * @param n the integer to be reversed
     * @return the reversed integer
     */
    public int reverseInteger(int n) {
        int reversed_n = 0;
        
        while (n != 0) {
            int temp = reversed_n * 10 + n % 10;
            n = n / 10;
            if (temp / 10 != reversed_n) { // 判断是否越界
                reversed_n = 0;
                break;
            }
            reversed_n = temp;
        }
      
        return reversed_n;
    }
}
