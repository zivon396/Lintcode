class Pair {
    public int x, y;
    public Pair (int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    /**
     * @param grid: a 2D integer grid
     * @return: an integer
     */
    private int n, m;
    private int[] dirX = {1, 0, -1, 0};
    private int[] dirY = {0, 1, 0, -1};
    
    public int zombie(int[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0){
            return -1;
        }
        
        n = grid.length;
        m = grid[0].length;
        
        Queue<Pair> queue = new LinkedList<>();
        
        int people = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (grid[i][j] == 1){
                    queue.offer(new Pair(i, j));
                } else if (grid[i][j] == 0){
                    people++;
                }
            }
        }
        
        int count = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++){
                Pair curt = queue.poll();
                for (int j = 0; j < 4; j++){
                    int newX = curt.x + dirX[j];
                    int newY = curt.y + dirY[j];
                    if (isValid(grid, newX, newY)){
                        grid[newX][newY] = 1;
                        queue.offer(new Pair(newX, newY));
                        people--;
                        if (people == 0) {
                            return count;
                        }
                    }
                }
            }
        }
        
        
        return -1;
    }
    
    private boolean isValid (int[][] grid, int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m && grid[x][y] == 0;
    }
}