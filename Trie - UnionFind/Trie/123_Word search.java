// 其实本题用 Trie 算多此一举
// 参照 DFS 目录下答案, 用 word + index 即可
class TrieNode {
    public Map<Character, TrieNode> children;
    public String word;
    
    public TrieNode (){
        children = new HashMap<>();
        word = null;
    }
    
    public void insert (String word, int index){
        if (index == word.length()){
            this.word = word;
            return;
        }
        
        char pos = word.charAt(index);
        if (!children.containsKey(pos)){
            children.put(pos, new TrieNode());
        }
        
        children.get(pos).insert(word, index + 1);
    }
}
public class Solution {
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    public int[] dx = {1, 0, -1, 0};
    public int[] dy = {0, 1, 0, -1};
    
    public boolean exist(char[][] board, String word) {
        // write your code here
        TrieNode root = new TrieNode();
        root.insert(word, 0);
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (search(board, i, j, root)) {
                    return true;
                };
            }
        }
        
        return false;
    }
    
    private boolean search (char[][] board,
                         int x,
                         int y,
                         TrieNode root){
        TrieNode child = root.children.get(board[x][y]);
        if (child == null){
            return false;
        }
        
        if (child.word != null) {
            return true;
        }
        
        char tmp = board[x][y];
        board[x][y] = 0;  // mark board[x][y] as used
        
        for (int i = 0; i < 4; i++) {
            if (!isValid(board, x + dx[i], y + dy[i])) {
                continue;
            }
            if (search(board, x + dx[i], y + dy[i], child)){
                return true;
            }
        }
        
        board[x][y] = tmp;  // revert the mark
        return false;
    }
    
    private boolean isValid(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }
        
        return board[x][y] != 0;
    }
}
