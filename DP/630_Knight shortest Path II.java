// 相比于 611, 此题有方向性, 因此必须从左向右遍历
// 也可用 BFS, 只不过要自己设置 Point class 和起点终点
public class Solution {
    /**
     * @param grid a chessboard included 0 and 1
     * @return the shortest path
     */
    public int shortestPath2(boolean[][] grid) {
        // Write your code here
        int n = grid.length;
        if (n == 0)
            return -1;
        int m = grid[0].length;
        if (m == 0)
            return -1;

        int[][] f = new int[n][m];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                f[i][j] = Integer.MAX_VALUE;

        f[0][0] = 0;
        for (int j = 1; j < m; ++j)
          for (int i = 0; i < n; ++i)
            if (!grid[i][j]) {
                if (i >= 1 && j >= 2 && f[i - 1][j - 2] != Integer.MAX_VALUE)
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 2] + 1);
                if (i + 1 < n && j >= 2 && f[i + 1][j - 2] != Integer.MAX_VALUE)
                    f[i][j] = Math.min(f[i][j], f[i + 1][j - 2] + 1);
                if (i >= 2 && j >= 1 && f[i - 2][j - 1] != Integer.MAX_VALUE)
                    f[i][j] = Math.min(f[i][j], f[i - 2][j - 1] + 1);
                if (i + 2 < n && j >= 1 && f[i + 2][j - 1] != Integer.MAX_VALUE)
                    f[i][j] = Math.min(f[i][j], f[i + 2][j - 1] + 1);
            }

        if (f[n - 1][m - 1] == Integer.MAX_VALUE)
            return -1;

        return f[n - 1][m - 1];
    }
}

// 自创
public class Solution {
    /**
     * @param grid: a chessboard included 0 and 1
     * @return: the shortest path
     */
    private int[] dirX = new int[]{1, -1, 2, -2};
    private int[] dirY = new int[]{2, 2, 1, 1};
    public int shortestPath2(boolean[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0 ||grid[0].length == 0){
            return 0;
        }
        int n = grid.length, m = grid[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int j = 0; j < m; j++){
            for (int i = 0; i < n; i++){
                if (i == 0 && j == 0){
                    dp[i][j] = !grid[i][j] ? 0 : Integer.MAX_VALUE;
                    continue;
                }

                if (grid[i][j]){
                    continue;
                }

                for (int k = 0; k < 4; k++){
                    int newX = i - dirX[k];
                    int newY = j - dirY[k];
                    if (isValid(grid, n, m, newX, newY) && dp[newX][newY] != Integer.MAX_VALUE){
                        dp[i][j] = Math.min(dp[newX][newY] + 1, dp[i][j]);
                        continue;
                    }
                }
            }
        }

        return dp[n - 1][m - 1] == Integer.MAX_VALUE ? -1 : dp[n - 1][m - 1];
    }

    private boolean isValid (boolean[][] grid, int n, int m, int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m && !grid[x][y];
    }
}
