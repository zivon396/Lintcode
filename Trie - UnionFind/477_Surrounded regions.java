// 需将边缘上的 0 连结到 n * m + 1
// 因此 union 的时候需要有顺序, 将小的连结到大的上, 这样所有和边缘的 0 相邻的 0 的 father 都是 n * m + 1
class UnionFind{
    
    int[] father;
    
    UnionFind(int n){
        father = new int[n];
        for (int i = 1; i < n; i++){
            father[i] = i;
        }
    }
    
    int find(int a){
        if (a == father[a]){
            return a;
        }
        return father[a] = find(father[a]);
    }
    
    void connect(int a, int b){
        int fa = find(a);
        int fb = find(b);
        if (fa != fb){
            father[Math.min(fa, fb)] = Math.max(fa, fb);
        }
    }
}

public class Solution {
    /*
     * @param board: board a 2D board containing 'X' and 'O'
     * @return: nothing
     */
    public void surroundedRegions(char[][] board) {
        // write your code here
        if (board == null || board.length == 0 || board[0].length == 0){
            return;
        }
        int n = board.length;
        int m = board[0].length;
        
        int total = n * m;
        UnionFind uf = new UnionFind(total + 1);
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                // check only edge 0
                if (board[i][j] == 'X'){
                    continue;
                }
                if (i == 0 || i == n - 1 || j == 0 || j == m - 1){
                    uf.connect(i * m + j, total);
                } else {
                    for (int k = 0; k < 4; k++){
                        int ni = i + dx[k];
                        int nj = j + dy[k];
                        if (board[ni][nj] == 'O'){
                            uf.connect(i * m + j, ni * m + nj);
                        }
                    }
                }
            }
        }
        for (int i = 1; i < n - 1; i++){
            for (int j = 1; j < m - 1; j++){
                if (board[i][j] == 'O' && uf.find(i * m + j) != total){
                    board[i][j] = 'X';
                }
            }
        }
    }
}


// BFS 版本
// 对每个边缘上的 0 进行 BFS, 将遍历过的 0 都置为 F
public class Solution {
    static final int[] directionX = { +1, -1, 0, 0 };
    static final int[] directionY = { 0, 0, +1, -1 };

    static final char FREE = 'F';

    public void surroundedRegions(char[][] board) {
        if (board.length == 0) {
            return;
        }

        int row = board.length;
        int col = board[0].length;

        for (int i = 0; i < row; i++) {
            bfs(board, i, 0);
            bfs(board, i, col - 1);
        }

        for (int j = 1; j < col - 1; j++) {
            bfs(board, 0, j);
            bfs(board, row - 1, j);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                switch (board[i][j]) {
                case 'O':
                    board[i][j] = 'X';
                    break;
                case 'F':
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void bfs(char[][] board, int i, int j) {
        if (board[i][j] != 'O') {
            return;
        }

        Queue<Node> queue = new LinkedList<Node>();
        board[i][j] = FREE;
        queue.offer(new Node(i, j));

        while (!queue.isEmpty()) {
            Node crt = queue.poll();

            for (Node node : expand(board, crt)) {
                queue.offer(node);
            }
        }
    }

    private List<Node> expand(char[][] board, Node node) {
        List<Node> expansion = new ArrayList<Node>();

        for (int i = 0; i < directionX.length; i++) {
            int x = node.x + directionX[i];
            int y = node.y + directionY[i];

            // check validity
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'O') {
                board[x][y] = FREE;
                expansion.add(new Node(x, y));
            }
        }

        return expansion;
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}


// DFS 版本
public class Solution {
    /**
     * @param board a 2D board containing 'X' and 'O'
     * @return void
     */
    public void surroundedRegions(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return ;
        }
        int n = board.length,m = board[0].length;
        //四周向中间搜
        for (int i = 0; i < n;i++) {
            for (int j = 0;j < m;j++) {
                if (!is_border(i,j,n,m)) {
                    continue;
                }
                if (board[i][j] == 'O') {
                    dfs(board , i , j);
                }
            }
        }
        
        //遍历图，更新结果
        for (int i = 0;i < n;i++) {
            for (int j = 0;j < m;j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                else if (board[i][j] == '*') {
                    board[i][j] = 'O'; 
                }
            }
        }
    }
    
    public void dfs(char[][] board , int x , int y){
        //保证当前点在图中
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) {
            return ;
        }
        if (board[x][y] != 'O') {
            return ;
        }
        board[x][y] = '*';
        dfs(board , x + 1, y);
        dfs(board , x - 1, y);
        dfs(board , x , y + 1);
        dfs(board , x , y - 1);
    }
    public boolean is_border(int x,int y,int n,int m) {//判断是否边界
        if (x == 0 || y == 0 || x == n - 1||y == m - 1) {
            return true;
        }
        return false;
    }
}
