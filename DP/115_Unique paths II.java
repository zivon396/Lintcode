public class Solution {
    /**
     * @param obstacleGrid: A list of lists of integers
     * @return: An integer
     */
    public int uniquePathsWithObstacles(int[][] grid) {
        // write your code here
        int n = grid.length, m = grid[0].length;
        if (n == 0 || m == 0){
            return 0;
        }

        int[][] dp = new int[n][m];
        if (grid[0][0] == 0){
            dp[0][0] = 1;
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (i == 0 && j == 0){
                    continue;
                }

                if (grid[i][j] == 1){
                    continue;
                }

                if (i == 0){
                    dp[i][j] = dp[i][j - 1];
                    continue;
                }

                if (j == 0){
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }

                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[n - 1][m - 1];
    }
}
