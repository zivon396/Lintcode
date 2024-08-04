public class Solution {
    /**
     * @param a: a sparse matrix
     * @param b: a sparse matrix
     * @return: the result of A * B
     */
    public int[][] multiply(int[][] a, int[][] b) {
        // write your code here
        int[][] ab = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++){
            for (int j = 0; j < b[0].length; j++){
                for (int k = 0; k < b.length; k++){
                    ab[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return ab;
    }
}
