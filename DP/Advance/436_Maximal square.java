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
