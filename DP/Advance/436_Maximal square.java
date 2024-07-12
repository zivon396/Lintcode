// 二维滚动数组
public class Solution {
    /**
     * @param matrix: a matrix of 0 and 1
     * @return: an integer
     */
    public int maxSquare(int[][] matrix) {
        // write your code here
        int ans = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return ans;
        }
        int n = matrix.length, m = matrix[0].length;
        
        int [][]res = new int [2][m];
        for (int i = 0; i < n; i++){
            res[i % 2][0] = matrix[i][0];
            ans = Math.max(res[i % 2][0], ans);
            for (int j = 1; j < m; j++){
                if (i > 0){
                    if (matrix[i][j] > 0){
                        res[i % 2][j] = Math.min(res[(i - 1) % 2][j], Math.min(res[i % 2][j - 1], 
                        res[(i - 1) % 2][j - 1])) + 1;
                    } else {
                        res[i % 2][j] = 0;
                    }
                }
                else {
                    res[i % 2][j] = matrix[i % 2][j];
                }
                ans = Math.max(res[i % 2][j], ans);
            }
        }

        return ans * ans;
    }
}

// version 2 (更好):
public class Solution {
    /**
     * @param matrix: a matrix of 0 and 1
     * @return: an integer
     */
    public int maxSquare(int[][] matrix) {
        // write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int n = matrix.length, m = matrix[0].length;
        int[][] dp = new int[2][m];
        int max = 0;
        for (int i = 0; i < m; i++){
            dp[0][i] = matrix[0][i];
            max = Math.max(max, dp[0][i]);
        }

        for (int i = 1; i < n; i++){
            for (int j = 0; j < m; j++){
                if (matrix[i][j] > 0){
                    if (j == 0){
                        dp[i % 2][j] = 1;
                    }
                    else {
                        dp[i % 2][j] = Math.min(Math.min(dp[(i - 1) % 2][j], dp[i % 2][j - 1]), dp[(i - 1) % 2][j - 1]) + 1;
                    }
                }
                else {
                    dp[i % 2][j] = 0;
                }

                max = Math.max(max, dp[i % 2][j]);
            }
        }

        return max * max;
    }
}
