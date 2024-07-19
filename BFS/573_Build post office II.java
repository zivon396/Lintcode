// 注意本题不能穿过房屋, 而 574 可以
// BFS
class Point {
    public int x, y;
    public Point (int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    /**
     * @param grid: a 2D grid
     * @return: An integer
     */
    private static final int[] dirX = new int[]{1, 0, -1, 0};
    private static final int[] dirY = new int[]{0, 1, 0, -1};
    private static final int INF = Integer.MAX_VALUE;
    private static int n, m;
    public int shortestDistance(int[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        n = grid.length;
        m = grid[0].length;
        int cnt = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (grid[i][j] == 1){
                    cnt++;
                }
            }
        }
        int minDis = INF;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (grid[i][j] == 0){
                    minDis = Math.min(minDis, bfs(grid, i, j, cnt));
                }
            }
        }

        return minDis == INF ? -1 : minDis;
    }

    private int bfs (int[][] grid, int x, int y, int cnt){
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        queue.offer(new Point(x, y));
        visited[x][y] = true;

        int dist = 0;
        int step = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++){
                Point curt = queue.poll();
                for (int k = 0; k < 4; k++){
                    int newX = curt.x + dirX[k];
                    int newY = curt.y + dirY[k];
                    if (!inBound(grid, newX, newY)){
                        continue;
                    }
                    if (visited[newX][newY]){
                        continue;
                    }
                    if (grid[newX][newY] == 2){
                        visited[newX][newY] = true;
                        continue;
                    }
                    if (grid[newX][newY] == 1){
                        cnt--;
                        dist += step;
                        if (cnt == 0){
                            return dist;
                        }
                        visited[newX][newY] = true;
                        continue;
                    }
                    queue.offer(new Point(newX, newY));
                    visited[newX][newY] = true;
                }
            }
        }

        return INF;
    }

    private boolean inBound (int[][] grid, int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
