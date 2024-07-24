// 注意 line 29 - 33 的出口比较巧妙
public class Solution {
    /**
     * @param board: A list of lists of character
     * @param word: A string
     * @return: A boolean
     */
    private static int[] dirX = new int[]{0, 1, 0, -1};
    private static int[] dirY = new int[]{1, 0, -1, 0};
    public boolean exist(char[][] board, String word) {
        // write your code here
        if (board == null || board.length == 0 || board[0].length == 0){
            return false;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (dfs(board, word, 0, i, j, visited)){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs (char[][] board, String word, int index, int x, int y, boolean[][] visited){
        if (board[x][y] != word.charAt(index)) {
            return false;
        } else if (index == word.length() - 1) {
            return true;
        }

        visited[x][y] = true;
        boolean res = false;
        for (int k = 0; k < 4; k++){
            int newX = x + dirX[k];
            int newY = y + dirY[k];
            if (!isValid(board, newX, newY, visited)){
                continue;
            }
            if (dfs(board, word, index + 1, newX, newY, visited)){
                res = true;
                break;
            }
        }
        visited[x][y] = false;

        return res;
    }

    private boolean isValid (char[][] board, int x, int y, boolean[][] visited){
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length && !visited[x][y];
    }
}
