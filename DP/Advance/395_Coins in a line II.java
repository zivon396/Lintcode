// dp[i] => 现在还剩 i 个硬币, 当前取硬币的人最后最多取到的价值
// sum[i] => 最后 i 个硬币的总和
// dp[i] = max((sum[i] - dp[i - 1]), (sum[i] - dp[i - 2]))
public class Solution {
    /**
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // write your code here
        if (values == null || values.length == 0){
            return false;
        }
        if (values.length < 3){
            return true;
        }

        int n = values.length;
        int[] dp = new int[n + 1];
        int[] sum = new int[n + 1];
        dp[0] = 0;
        dp[1] = values[n - 1];
        dp[2] = values[n - 1] + values[n - 2];
        sum[0] = 0;
        for (int i = n - 1; i >= 0; i--){
            sum[n - i] = sum[n - i - 1] + values[i];
        }

        for (int i = 3; i <= n; i++){
            dp[i] = Math.max((sum[i] - dp[i - 1]), (sum[i] - dp[i - 2]));
        }

        return dp[n] > (sum[n] / 2.0);
    }
}

// dp[i] => 现在还剩 i 个硬币, 当前取硬币的人可以赢得的最大分差
public class Solution {
    /**
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // write your code here
        if (values == null || values.length == 0){
            return false;
        }
        if (values.length < 3){
            return true;
        }

        int n = values.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = values[n - 1];
        dp[2] = values[n - 1] + values[n - 2];

        for (int i = 3; i <= n; i++){
            dp[i] = Math.max(values[n - i] - dp[i - 1], values[n - i] + values[n - i + 1] - dp[i - 2]);
        }

        return dp[n] >= 0;
    }
}

// dp[i] => 现在还剩 i 个硬币, 当前先手取硬币的人最后最多取到的价值
// dp[i] = max(min(dp[i-2], dp[i-3]) + coin[n-i], min(dp[i-3], dp[i-4]) + coin[n-i] + coin[n-i+1])
public class Solution {
    /**
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // write your code here
        if (values == null || values.length == 0){
            return false;
        }
        if (values.length < 3){
            return true;
        }
        if (values.length == 3){
            return values[2] < values[0] + values[1];
        }

        int n = values.length;
        int[] dp = new int[n + 1];
        int[] sum = new int[n + 1];
        dp[0] = 0;
        dp[1] = values[n - 1];
        dp[2] = values[n - 1] + values[n - 2];
        dp[3] = values[n - 2] + values[n - 3];
        sum[0] = 0;
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i - 1] + values[i - 1];
        }

        for (int i = 4; i <= n; i++){
            dp[i] = Math.max(Math.min(dp[i - 2], dp[i - 3]) + values[n - i], Math.min(dp[i - 3], dp[i - 4]) + values[n - i] + values[n - i + 1]);
        }

        return dp[n] > (sum[n] / 2.0);
    }
}
