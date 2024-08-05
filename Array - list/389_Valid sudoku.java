public class Solution {
    /**
     * @param board: the board
     * @return: whether the Sudoku is valid
     */
    public boolean isValidSudoku(char[][] board) {
        // write your code here
        if (board == null || board.length == 0 || board[0].length == 0){
            return true;
        }
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < board.length; i++){
            set.clear();
            for (int j = 0; j < board[0].length; j++){
                if (!checkValid(board[i][j], set)){
                    return false;
                }
            }
        }

        for (int j = 0; j < board[0].length; j++){
            set.clear();
            for (int i = 0; i < board.length; i++){
                if (!checkValid(board[i][j], set)){
                    return false;
                }
            }
        }

        for (int i = 0; i < board.length; i += 3){
            for (int j = 0; j < board[0].length; j += 3){
                set.clear();
                for (int k = i; k < i + 3; k++){
                    for (int l = j; l < j + 3; l++){
                        if (!checkValid(board[k][l], set)){
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    boolean checkValid(char c, Set<Character> set) {
        if (c == '.') {
            return true;
        }
        if (set.contains(c)) {
            return false;
        }

        set.add(c);
        return true;
    }
}
