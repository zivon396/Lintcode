// dp[i][j] 现在还剩从第 i 到第 j 个硬币, 当前选取的玩家能赢得的最大分差
public class Solution {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // write your code here

        int n = values.length;
        if (n == 0) {
            return true;
        }

        int[][] dp = new int[n][n];
        int i, j, len;
        // len 1
        for (i = 0; i < n; ++i) {
            dp[i][i] = values[i];
        }

        for (len = 2; len <= n; ++len) {
            for (i = 0; i <= n - len; ++i) {
                j = i + len - 1;
                dp[i][j] = Math.max(values[i] - dp[i + 1][j], values[j] - dp[i][j - 1]);
            }
        }

        return dp[0][n - 1] >= 0;
    }
}
