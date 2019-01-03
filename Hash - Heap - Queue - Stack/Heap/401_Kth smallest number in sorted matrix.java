class Pair {
    public int x, y;
    public int val;
    public Pair (int x, int y, int val){
        this.x = x;
        this.y = y;
        this.val = val;
    }
}

public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @param k: An integer
     * @return: the kth smallest number in the matrix
     */
    private Comparator<Pair> pairCom = new Comparator<Pair>(){
        public int compare (Pair a, Pair b){
            return a.val - b.val;
        }
    };
    private int n, m;
    
    public int kthSmallest(int[][] matrix, int k) {
        // write your code here
        n = matrix.length;
        m = matrix[0].length;
        int[] dx = {1, 0};
        int[] dy = {0, 1};
        boolean[][] hash = new boolean[n][m];
        
        Queue<Pair> heap = new PriorityQueue<Pair>(k, pairCom);
        heap.offer(new Pair(0, 0, matrix[0][0]));
        
        for (int i = 0; i < k - 1; i++){
            Pair curt = heap.poll();
            for (int j = 0; j < 2; j++){
                int nx = curt.x + dx[j];
                int ny = curt.y + dy[j];
                if (isValid(hash, nx, ny)){
                    hash[nx][ny] = true;
                    heap.add(new Pair(nx, ny, matrix[nx][ny]));
                }
            }
        }
        
        return heap.peek().val;
    }
    
    private boolean isValid (boolean[][] hash, int x, int y){
        return x < n && y < m && !hash[x][y];
    }
}