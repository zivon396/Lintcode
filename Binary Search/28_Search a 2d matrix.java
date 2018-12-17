public class Solution {
    /**
     * @param matrix: matrix, a list of lists of integers
     * @param target: An integer
     * @return: a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int i = n - 1, j = 0;
        
        while (i >= 0 && i < n && j >= 0 && j < m){
            if (matrix[i][j] == target){
                return true;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                i--;
            }
        }
        
        return false;
        
    }
}