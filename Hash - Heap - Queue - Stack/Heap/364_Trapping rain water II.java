// 将矩阵周边的格子都放到堆里, 这些格子上面是无法盛水的. 每次在堆里挑出一个高度最小的格子 cell, 把周围的格子加入到堆里. 这些格子被加入堆的时候, 计算他们上面的盛水量.
// 本质上和 363 的 two pointers 解法是一样的, 都是每逢降低就存水, 每逢升高就更新高度.
class Cell {
    public int x, y, height;
    public Cell() {};
    public Cell (int x, int y, int height){
        this.x = x;
        this.y = y;
        this.height = height;
    }
}

class cellCom implements Comparator<Cell> {
    public int compare (Cell left, Cell right){
        return left.height - right.height;
    }
}

public class Solution {
    /**
     * @param heights: a matrix of integers
     * @return: an integer
     */
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    
    public int trapRainWater(int[][] heights) {
        // write your code here
        if (heights == null || heights.length == 0) {
            return 0;
        }
    
        PriorityQueue<Cell> minheap =  new PriorityQueue<>(new cellCom());
        
        int n = heights.length;
        int m = heights[0].length;
        boolean[][] visited = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            minheap.offer(new Cell(i, 0, heights[i][0]));
            minheap.offer(new Cell(i, m - 1, heights[i][m - 1]));
            visited[i][0] = true;
            visited[i][m - 1] = true;
        }
        
        for (int i = 0; i < m; i++) {
            minheap.offer(new Cell(0, i, heights[0][i]));
            minheap.offer(new Cell(n - 1, i, heights[n - 1][i]));
            visited[0][i] = true;
            visited[n - 1][i] = true;
        }

        int water = 0;
        while (!minheap.isEmpty()) {
            Cell cell = minheap.poll();
            
            for (int i = 0; i < 4; i++) {
                int nx = cell.x + dx[i];
                int ny = cell.y + dy[i];
                
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                
                visited[nx][ny] = true;
                // 每次填入水之后, 相当于多出一个水柱
                minheap.offer(new Cell(nx, ny, Math.max(cell.height, heights[nx][ny])));
                water = water + Math.max(0, cell.height - heights[nx][ny]);
            }
        }
        
        return water;
    }
}


// 原创版本
class Point {
    public int x, y;
    public int height;
    public Point (int x, int y, int height){
        this.x = x;
        this.y = y;
        this.height = height;
    }
}

public class Solution {
    /**
     * @param heights: a matrix of integers
     * @return: an integer
     */
    private Comparator<Point> com = new Comparator<Point>(){
        public int compare (Point a, Point b){
            if (a.height == b.height){
                if (a.x == b.x){
                    return a.y - b.y;
                }

                return a.x - b.x;
            }

            return a.height - b.height;
        }
    };
    private int[] dirX = new int[]{1, 0, -1, 0};
    private int[] dirY = new int[]{0, 1, 0, -1};
    private boolean[][] visited;
    private int n, m;
    public int trapRainWater(int[][] heights) {
        // write your code here
        if (heights == null || heights.length == 0 || heights[0].length == 0){
            return 0;
        }
        n = heights.length;
        m = heights[0].length;
        Queue<Point> heap = new PriorityQueue<>(com);
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++){
            heap.offer(new Point(i, 0, heights[i][0]));
            visited[i][0] = true;
            heap.offer(new Point(i, m - 1, heights[i][m - 1]));
            visited[i][m - 1] = true;
        }
        for (int j = 0; j < m; j++){
            if (!visited[0][j]){
                heap.offer(new Point(0, j, heights[0][j]));
                visited[0][j] = true;
            }
            if (!visited[n - 1][j]){
                heap.offer(new Point(n - 1, j, heights[n - 1][j]));
                visited[n - 1][j] = true;
            }
        }

        int res = 0;
        while (!heap.isEmpty()){
            Point curt = heap.poll();
            for (int k = 0; k < 4; k++){
                int newX = curt.x + dirX[k];
                int newY = curt.y + dirY[k];
                if (isValid(newX, newY)){
                    if (heights[newX][newY] < curt.height){
                        res += curt.height - heights[newX][newY];
                    }

                    heap.offer(new Point(newX, newY, Math.max(curt.height, heights[newX][newY])));
                    visited[newX][newY] = true;
                }
            }
        }

        return res;
    }

    private boolean isValid (int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m && !visited[x][y];
    }
}
