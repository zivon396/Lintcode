class Pair {
    public int x, y;
    public Pair (int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    /**
     * @param grid: a boolean 2D matrix
     * @return: an integer
     */
    private int n, m;
    
    public int numIslands(boolean[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int count = 0;
        
        n = grid.length;
        m = grid[0].length;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (grid[i][j]){
                    count++;
                    bfs(grid, i, j);
                }
            }
        }
        
        return count;
    }
    
    private void bfs (boolean[][] grid, int i, int j){
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(i, j));
        grid[i][j] = false;
        int[] dirX = {1, 0, -1, 0};
        int[] dirY = {0, 1, 0, -1};
        
        while (!queue.isEmpty()){
            Pair curt = queue.poll();
            for (int k = 0; k < 4; k++){
                int newX = curt.x + dirX[k];
                int newY = curt.y + dirY[k];
                if (isValid(grid, newX, newY)){
                    queue.offer(new Pair(newX, newY));
                    grid[newX][newY] = false;
                }
            }
        }
    }
    
    private boolean isValid (boolean[][] grid, int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m && grid[x][y];
    }
}