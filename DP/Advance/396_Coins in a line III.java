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

// dp[i][j] 现在还剩从第 i 到第 j 个硬币, 当前选取的玩家能最后取得的最大价值
public class Solution {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // write your code here

        int n = values.length;
        int[] sum = new int[n + 1];
        sum[0] = 0;
        for (int i = 1; i <= n; ++i)
            sum[i] = sum[i - 1] + values[i - 1];

        // s[i][j] = sum[j + 1] - sum[i];

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i)
            dp[i][i] = values[i];

        for (int len = 2; len <= n; ++len) {
            for (int i = 0; i <= n - len; ++i) {
                int j = i + len - 1;
                int s = sum[j + 1] - sum[i];
                
                dp[i][j] = Math.max(s - dp[i + 1][j], s - dp[i][j - 1]);
            }
        }

        return dp[0][n - 1] > sum[n] / 2;
    }
}

// 记忆化搜索
// version 1
public class Solution {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // write your code here
        int n = values.length;
        int[][] dp = new int[n + 1][n + 1];
        boolean[][] flag = new boolean[n + 1][n + 1];

        int sum = 0;
        for (int now : values)
            sum += now;

        return sum < 2 * MemorySearch(0, values.length - 1, dp, flag, values);
    }

    int MemorySearch(int left, int right, int[][] dp, boolean[][] flag, int[] values) {

        if (flag[left][right])
            return dp[left][right];
        flag[left][right] = true;
        
        if (left > right) {
            dp[left][right] = 0;
        } else if (left == right) {
            dp[left][right] = values[left];
        } else if (left + 1 == right) {
            dp[left][right] = Math.max(values[left], values[right]);
        } else {
            int pick_left = Math.min(MemorySearch(left + 2, right, dp, flag, values),
                    MemorySearch(left + 1, right - 1, dp, flag, values)) + values[left];
            int pick_right = Math.min(MemorySearch(left, right - 2, dp, flag, values),
                    MemorySearch(left + 1, right - 1, dp, flag, values)) + values[right];
            dp[left][right] = Math.max(pick_left, pick_right);
        }
        
        return dp[left][right];
    }
}

// version 2
public class Solution {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        int n = values.length;
        int[][] dp = new int[n + 1][n + 1];
        boolean[][] flag = new boolean[n + 1][n + 1];
        int[][] sum = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                sum[i][j] = i == j ? values[j] : sum[i][j - 1] + values[j];
            }
        }
        
        int allsum = 0;
        for (int now : values)
            allsum += now;

        return allsum < 2 * MemorySearch(0, values.length - 1, dp, flag, values, sum);
    }

    int MemorySearch(int left, int right, int[][] dp, boolean[][] flag, int[] values, int[][] sum) {
        if (flag[left][right])
            return dp[left][right];
        flag[left][right] = true;
        
        if (left > right) {
            dp[left][right] = 0;
        } else if (left == right) {
            dp[left][right] = values[left];
        } else if (left + 1 == right) {
            dp[left][right] = Math.max(values[left], values[right]);
        } else {
            int cur = Math.min(MemorySearch(left + 1, right, dp, flag, values, sum),
                    MemorySearch(left, right - 1, dp, flag, values, sum));
            dp[left][right] = sum[left][right] - cur;
        }
        
        return dp[left][right];
    }
}
