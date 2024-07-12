public class Solution {
    /**
     * @param matrix: a matrix of 0 an 1
     * @return: an integer
     */
    public int maxSquare2(int[][] matrix) {
        // write your code here
        int n = matrix.length;
        if (n == 0)
            return 0;

        int m = matrix[0].length;
        if (m == 0)
            return 0;

        int[][] f = new int[n][m];
        int[][] u = new int[n][m];
        int[][] l = new int[n][m];

        int length = 0;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j) {
                if (matrix[i][j] == 0) {
                    f[i][j] = 0;
                    u[i][j] = l[i][j] = 1;
                    if (i > 0)
                        u[i][j] = u[i - 1][j] + 1;
                    if (j > 0)
                        l[i][j] = l[i][j - 1] + 1;
                } else {
                    u[i][j] = l[i][j] = 0;
                    if (i > 0 && j > 0)
                        f[i][j] = Math.min(f[i - 1][j - 1], Math.min(u[i - 1][j], l[i][j - 1])) + 1;
                    else 
                        f[i][j] = 1;
                }
                
                length = Math.max(length, f[i][j]);
            }
        return length * length;
    }
}

// 2D 滚动数组
public class Solution {
    /**
     * @param matrix: a matrix of 0 an 1
     * @return: an integer
     */
    public int maxSquare2(int[][] matrix) {
        // write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int n = matrix.length, m = matrix[0].length;
        int[][] diag = new int[2][m];
        int[][] h = new int[2][m];
        int[][] v = new int[2][m];
        if (matrix[0][0] == 1){
            diag[0][0] = 1;
        }
        else {
            v[0][0] = 1;
            h[0][0] = 1;
        }

        int max = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (matrix[i][j] == 1){
                    h[i % 2][j] = 0;
                    v[i % 2][j] = 0;
                    if (i == 0 || j == 0){
                        diag[i % 2][j] = 1;
                    }
                    else {
                        diag[i % 2][j] = Math.min(Math.min(h[i % 2][j - 1], v[(i - 1) % 2][j]), diag[(i - 1) % 2][j - 1]) + 1;
                    }
                }
                else {
                    diag[i % 2][j] = 0;
                    h[i % 2][j] = 1;
                    v[i % 2][j] = 1;
                    if (j > 0){
                        h[i % 2][j] = h[i % 2][j - 1] + 1;
                    }
                    if (i > 0){
                        v[i % 2][j] = v[(i - 1) % 2][j] + 1;
                    }
                }

                max = Math.max(max, diag[i % 2][j]);
            }
        }

        return max * max;
    }
}
