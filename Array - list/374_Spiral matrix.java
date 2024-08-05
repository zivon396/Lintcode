public class Solution {
    /**
     * @param matrix: a matrix of m x n elements
     * @return: an integer list
     */
    private int n, m;
    private boolean[][] visited;
    private int[] dirX = new int[]{0, 1, 0, -1};
    private int[] dirY = new int[]{1, 0, -1, 0};
    public List<Integer> spiralOrder(int[][] matrix) {
        // write your code here
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        n = matrix.length;
        m = matrix[0].length;
        visited = new boolean[n][m];

        int i = 0, j = 0;
        int k = 0;
        int count = 0;
        while (true){
            visited[i][j] = true;
            res.add(matrix[i][j]);
            if (++count == n * m){
                break;
            }

            while (!isValid(i + dirX[k], j + dirY[k])){
                k = (k + 1) % 4;
            }
            i = i + dirX[k];
            j = j + dirY[k];
        }

        return res;
    }

    private boolean isValid (int i, int j){
        return i >= 0 && i < n && j >= 0 && j < m && !visited[i][j];
    }
}
