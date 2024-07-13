// 记忆化搜索
// dp[i][j] = min(dp[i][k] + dp[k + 1][j] + sum[i][j])
public class Solution {
    /**
     * @param a: An integer array
     * @return: An integer
     */
    public int stoneGame(int[] a) {
        // write your code here
        if (a == null || a.length == 0){
            return 0;
        }
        int n = a.length;
        int[][] dp = new int[n][n];
        int[][] visited = new int[n][n];
        int[][] sum = new int[n][n];

        for (int i = 0; i < n; i++){
            dp[i][i] = 0;
        }

        for (int i = 0; i < n; i++){
            sum[i][i] = a[i];
            for (int j = i + 1; j < n; j++){
                sum[i][j] = sum[i][j - 1] + a[j];
            }
        }

        int min = memorySearch(dp, visited, sum, 0, n - 1);

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
