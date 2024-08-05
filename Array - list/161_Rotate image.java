// 先对角翻转
// 再水平翻转
public class Solution {
    /**
     * @param matrix: a lists of integers
     * @return: nothing
     */
    public void rotate(int[][] matrix) {
        // write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return;
        }
        int n = matrix.length;
        int m = matrix[0].length;

        for (int i = 0; i < n; i++){
            for (int j = i; j < m; j++){
                swap(matrix, i, j, j, i);
            }
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m /2 ; j++){
                swap(matrix, i, j, i, m - j - 1);
            }
        }

        return;
    }

    private void swap (int[][] matrix, int i1, int j1, int i2, int j2){
        int tmp = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = tmp;
    }
}
