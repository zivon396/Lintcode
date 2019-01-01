class UnionFind {
    public int[] father = null;
    public int count;
    
    public UnionFind (int n){
        father = new int[n];
        for (int i = 0; i < n; i++){
            father[i] = i;
        }
    }
    
    public int find (int x){
        if (father[x] == x){
            return x;
        }
        
        return father[x] = find(father[x]);
    }
    
    public void connect (int a, int b){
        int root_a = find(a);
        int root_b = find(b);
        
        if (root_a != root_b){
            father[root_a] = root_b;
            count--;
        }
    }
    
    public int query (){
        return count;
    }
    
    public void set_count(int total) {
        count = total;
    }
}

public class Solution {
    /**
     * @param grid: a boolean 2D matrix
     * @return: an integer
     */
    private int n, m;
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};
    
    public int numIslands(boolean[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        
        n = grid.length;
        m = grid[0].length;
        
        UnionFind union_find = new UnionFind(n * m);
        
        int total = 0;
        for(int i = 0;i < grid.length; ++i) {
            for(int j = 0;j < grid[0].length; ++j) {
            if (grid[i][j])
                total ++;
            }
        }
        union_find.set_count(total);
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (grid[i][j]){
                    for (int k = 0; k < 4; k++){
                        int ni = i + dx[k];
                        int nj = j + dy[k];
                        if (isValid(grid, ni, nj)){
                            union_find.connect(i * m + j, ni * m + nj);
                        }
                    }
                }
            }
        }
        
        return union_find.query();
    }
    
    private boolean isValid (boolean[][] grid, int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m && grid[x][y];
    }
}