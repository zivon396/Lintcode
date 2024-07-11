// 滚动数组
public class Solution {
    /**
     * @param A: An array of non-negative integers
     * @return: The maximum amount of money you can rob tonight
     */
    public long houseRobber(int[] A) {
        // write your code here
        int n = A.length;
        if (n == 0) {
            return 0;
        }

        long[] res = new long[2];
        
        
        res[0] = 0;
        res[1] = A[0];

        for(int i = 2; i <= n; i++) {
            res[i % 2] = Math.max(res[(i-1) % 2], res[(i-2) % 2] + A[i-1]);
        }

        return res[n % 2];
    }
}

// version 2 (原创)
public class Solution {
    /**
     * @param a: An array of non-negative integers
     * @return: The maximum amount of money you can rob tonight
     */
    public long houseRobber(int[] a) {
        // write your code here
        if (a == null || a.length == 0){
            return 0;
        }
        if (a.length < 2){
            return a[0];
        }
        int n = a.length;
        long[] dp = new long[2];
        dp[0] = a[0];
        dp[1] = Math.max(a[0], a[1]);

        for (int i = 2; i < n; i++){
            dp[i % 2] = Math.max(dp[(i - 1) % 2], dp[(i - 2) % 2] + a[i]);
        }

        return Math.max(dp[0], dp[1]);
    }
}
