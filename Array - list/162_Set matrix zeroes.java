// 把所有的 0 先投影到左、上边界, 再扫一遍边界
// 左上角特殊处理
public class Solution {
    /**
     * @param matrix: A lsit of lists of integers
     * @return: nothing
     */
    public void setZeroes(int[][] matrix) {
        // write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return;
        }
        int n = matrix.length, m = matrix[0].length;
        int sign = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (matrix[i][j] == 0){
                    if (i == 0 && j == 0){
                        sign = 2;
                    }
                    else if (i == 0){
                        matrix[0][0] = 0;
                    }
                    else if (j == 0){
                        sign = 1;
                        matrix[0][0] = 0;
                    }
                    else {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }
        }

        for (int i = 1; i < n; i++){
            if (matrix[i][0] == 0){
                for (int j = 0; j < m; j++){
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 1; j < m; j++){
            if (matrix[0][j] == 0){
                for (int i = 0; i < n; i++){
                    matrix[i][j] = 0;
                }
            }
        }

        if (matrix[0][0] == 0){
            if (sign == 0 || sign == 2){
                for (int j = 0; j < m; j++){
                    matrix[0][j] = 0;
                }
            }
            if (sign == 1 || sign == 2){
                for (int i = 0; i < n; i++){
                    matrix[i][0] = 0;
                }
            }
        }

        return;
    }
}
