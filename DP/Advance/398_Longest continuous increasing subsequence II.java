// 记忆化搜索 + dp
public class Solution {
    /**
     * @param matrix: A 2D-array of integers
     * @return: an integer
     */
    private int[][] dp;
    private int m, n;
    
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};
    public int longestContinuousIncreasingSubsequence2(int[][] matrix) {
        // write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int res = 0;
        
        n = matrix.length;
        m = matrix[0].length;
        dp = new int[n][m];
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                res = Math.max(res, search(i, j, matrix));
            }
        }
        
        return res;
    }
    
    private int search (int x, int y, int[][] matrix){
        if (dp[x][y] > 0){
            return dp[x][y];
        }
        
        int res = 1;
        for (int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m){
                if (matrix[x][y] > matrix[nx][ny]){
                    res = Math.max(res, search(nx, ny, matrix) + 1);
                }
            }
        }
        
        dp[x][y] = res;
        
        return res;
    }
}
