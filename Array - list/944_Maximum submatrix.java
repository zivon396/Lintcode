// 利用 sum[n + 1][m + 1] 来计算 maximum subarray
public class Solution {
    /**
     * @param matrix: the given matrix
     * @return: the largest possible sum
     */
    private int n, m;
    public int maxSubmatrix(int[][] matrix) {
        // write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        n = matrix.length;
        m = matrix[0].length;
        int[][] sum = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                sum[i + 1][j + 1] = matrix[i][j] + sum[i][j + 1] + sum[i + 1][j] - sum[i][j];
            }
        }

        int max = Integer.MIN_VALUE;
        for (int l = 0; l < n; l++){
            for (int h = l + 1; h <= n; h++){
                max = Math.max(max, maximumSubarray(sum, l, h));
            }
        }

        return max;
    }

    private int maximumSubarray(int[][] prefixSum, int l, int h) {
        int min = 0, max = Integer.MIN_VALUE, sum = 0;
        
        for (int j = 1; j <= m; j++) {
            sum = prefixSum[h][j] - prefixSum[l][j];
            max = Math.max(max, sum - min);
            min = Math.min(min, sum);
        }
        
        return max;
    }
}

// Compression + maximum subarray
public class Solution {
    /**
    * @param matrix: the given matrix
    * @return: the largest possible sum
    */
    private int n, m;
    public int maxSubmatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        
        n = matrix.length;
        m = matrix[0].length;
        
        int[][] prefixColSum = getPrefixColSum(matrix);
        
        int max = Integer.MIN_VALUE;
        for (int up = 0; up < n; up++) {
            for (int down = up; down < n; down++) {
                int[] arr = compression(matrix, up, down, prefixColSum);
                max = Math.max(max, maximumSubarray(arr));
            }
        }
        
        return max;
    };
  
    private int maximumSubarray(int[] arr) {
        int min = 0, max = Integer.MIN_VALUE, sum = 0;
        
        for (int i = 0; i < m; i++) {
            sum += arr[i];
            max = Math.max(max, sum - min);
            min = Math.min(min, sum);
        }
        
        return max;
    }
  
    private int[] compression(int[][] matrix, int up, int down, int[][] prefixColSum) {
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = prefixColSum[down + 1][i] - prefixColSum[up][i];
        }
        
        return arr;
    }
  
    private int[][] getPrefixColSum(int[][] matrix) {
        int[][] sum = new int[n + 1][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum[i + 1][j] = sum[i][j] + matrix[i][j];
            }
        }
        
        return sum;
    }
}
