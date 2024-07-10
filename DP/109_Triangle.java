// 无优化版本
public class Solution {
    /**
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(int[][] triangle) {
        // write your code here
        if (triangle == null || triangle.length == 0 || triangle[0].length == 0){
            return 0;
        }
        int n = triangle.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j <= i; j++){
                if (i == 0){
                    dp[i][j] = triangle[i][j];
                    continue;
                }

                if (j == 0){
                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                    continue;
                }

                if (j == i){
                    dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                    continue;
                }

                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++){
            min = Math.min(min, dp[n - 1][i]);
        }

        return min;
    }
}

// 优化空间, 空间复杂度 O(n)
public class Solution {
    /**
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(int[][] triangle) {
        // write your code here
        if (triangle == null || triangle.length == 0 || triangle[0].length == 0){
            return 0;
        }
        int n = triangle.length;
        int[][] dp = new int[2][n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j <= i; j++){
                if (i == 0){
                    dp[i][j] = triangle[i][j];
                    continue;
                }

                if (j == 0){
                    dp[i % 2][j] = dp[(i - 1) % 2][j] + triangle[i][j];
                    continue;
                }

                if (j == i){
                    dp[i % 2][j] = dp[(i - 1) % 2][j - 1] + triangle[i][j];
                    continue;
                }

                dp[i % 2][j] = Math.min(dp[(i - 1) % 2][j - 1], dp[(i - 1) % 2][j]) + triangle[i][j];
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++){
            min = Math.min(min, dp[(n - 1) % 2][i]);
        }

        return min;
    }
}
