/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    /**
     * @param grid: a chessboard included 0 (false) and 1 (true)
     * @param source: a point
     * @param destination: a point
     * @return: the shortest path 
     */
    private int n, m;
    
    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        
        n = grid.length;
        m = grid[0].length;
        int[] dirX = {1, 1, 2, 2, -1, -1, -2, -2};
        int[] dirY = {2, -2, 1, -1, 2, -2, 1, -1};
        
        Queue<Point> queue = new LinkedList<>();
        grid[source.x][source.y] = true;
        queue.offer(source);
        
        int count = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++){
                Point curt = queue.poll();
                for (int j = 0; j < 8; j++){
                    int newX = curt.x + dirX[j];
                    int newY = curt.y + dirY[j];
                    if (newX == destination.x && newY == destination.y){
                        return count;
                    }
                    if (isValid(grid, newX, newY)){
                        queue.offer(new Point(newX, newY));
                        grid[newX][newY] = true;
                    }
                }
            }
        }
        
        return -1;
    }
    
    private boolean isValid (boolean[][] grid, int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m && !grid[x][y];
    }
}