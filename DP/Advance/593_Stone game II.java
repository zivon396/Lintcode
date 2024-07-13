// Double
// 由小到大
public class Solution {
    /**
     * @param A: An integer array
     * @return: An integer
     */
    public int stoneGame2(int[] A) {
        // Write your code here
        int n = A.length;
        if (n <= 1)
            return 0;

        int[][] dp = new int[2 * n][2 * n];

        int[] sum = new int[2 * n + 1];

        for (int i = 1; i <= 2 * n; ++i) {
            sum[i] = sum[i - 1] + A[(i - 1) % n];
        }

        for (int i = 0; i < 2 * n; ++i) {
            dp[i][i] = 0;
        }

        for(int len = 2; len <= n; ++len)
            for(int i = 0; i < 2 * n && i + len - 1 < 2 * n; ++i) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; ++k) {
                    if (dp[i][k] + dp[k + 1][j] + sum[j + 1] - sum[i] < dp[i][j])
                        dp[i][j] = dp[i][k] + dp[k + 1][j] + sum[j + 1] - sum[i];
                }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i)
            if (dp[i][i + n - 1] < ans)
                ans = dp[i][i + n - 1];
        
        return ans;
    }
}

// 记忆化搜索版本
public class Solution {
    /**
     * @param a: An integer array
     * @return: An integer
     */
    public int stoneGame2(int[] a) {
        // write your code here
        if (a == null || a.length == 0){
            return 0;
        }
        int n = a.length;
        int[] aa = new int[n * 2];
        int[][] dp = new int[n * 2][n * 2];
        int[][] visited = new int[n * 2][n * 2];
        int[][] sum = new int[n * 2][n * 2];

        for (int i = 0; i < n * 2; i++){
            aa[i] = a[i % n];
        }

        for (int i = 0; i < n * 2; i++){
            dp[i][i] = 0;
        }

        for (int i = 0; i < n * 2; i++){
            sum[i][i] = aa[i];
            for (int j = i + 1; j < n * 2; j++){
                sum[i][j] = sum[i][j - 1] + a[j % n];
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++){
            min = Math.min(min, memorySearch(dp, visited, sum, 0 + i, n + i - 1));
        }

        return min;
    }

    private int memorySearch (int[][] dp, int[][] visited, int[][] sum, int start, int end){
        if (visited[start][end] == 1){
            return dp[start][end];
        }
        if (start == end){
            visited[start][end] = 1;
            return dp[start][end];
        }

        visited[start][end] = 1;
        dp[start][end] = Integer.MAX_VALUE;
        for (int k = start; k < end; k++){
            dp[start][end] = Math.min(dp[start][end], memorySearch(dp, visited, sum, start, k) + memorySearch(dp, visited, sum, k + 1, end) + sum[start][end]);
        }

        return dp[start][end];
    }
}
