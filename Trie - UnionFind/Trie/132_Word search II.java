//version 1:
class TrieNode {
    String word;
    HashMap<Character, TrieNode> children;
    public TrieNode() {
        word = null;
        children = new HashMap<Character, TrieNode>();
    }
};


class TrieTree{
    TrieNode root;
    
    public TrieTree(TrieNode TrieNode) {
        root = TrieNode;
    }
    
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            if (!node.children.containsKey(word.charAt(i))) {
                node.children.put(word.charAt(i), new TrieNode());
            }
            node = node.children.get(word.charAt(i));
        }
        node.word = word;
    }
};

public class Solution {
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    public int[] dx = {1, 0, -1, 0};
    public int[] dy = {0, 1, 0, -1};
    
    public void search(char[][] board,
                       int x,
                       int y,
                       TrieNode root,
                       List<String> results) {
        if (!root.children.containsKey(board[x][y])) {
            return;
        }
        
        TrieNode child = root.children.get(board[x][y]);
        
        if (child.word != null) {
            if (!results.contains(child.word)) {
                results.add(child.word);
            }
        }
        
        char tmp = board[x][y];
        board[x][y] = 0;  // mark board[x][y] as used
        
        for (int i = 0; i < 4; i++) {
            if (!isValid(board, x + dx[i], y + dy[i])) {
                continue;
            }
            search(board, x + dx[i], y + dy[i], child, results);
        }
        
        board[x][y] = tmp;  // revert the mark
    }
    
    private boolean isValid(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }
        
        return board[x][y] != 0;
    }
    
    public List<String> wordSearchII(char[][] board, List<String> words) {
        List<String> results = new ArrayList<String>();
        
        TrieTree tree = new TrieTree(new TrieNode());
        for (String word : words){
            tree.insert(word);
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                search(board, i, j, tree.root, results);
            }
        }
        
        return results;
    }
}

//version 2:

class TrieNode {
    public TrieNode[] children;
    public String word;
    
    public TrieNode (){
        children = new TrieNode[26];
        word = null;
    }
    
    public void insert (String word, int index){
        if (index == word.length()){
            this.word = word;
            return;
        }
        
        int pos = word.charAt(index) - 'a';
        if (children[pos] == null){
            children[pos] = new TrieNode();
        }
        
        children[pos].insert(word, index + 1);
    }
    
    public TrieNode find (String word, int index){
        if (index == word.length()){
            return this;
        }
        
        int pos = word.charAt(index) - 'a';
        if (children[pos] == null){
            return null;
        }
        return children[pos].find(word, index + 1);
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
    
    public List<String> wordSearchII(char[][] board, List<String> words) {
        // write your code here
        List<String> results = new ArrayList<String>();
        
        TrieNode root = new TrieNode();
        for (String word : words){
            root.insert(word, 0);
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                search(board, i, j, root, results);
            }
        }
        
        return results;
    }
    
    private void search (char[][] board,
                         int x,
                         int y,
                         TrieNode root,
                         List<String> results){
        TrieNode child = root.children[board[x][y] - 'a'];
        if (child == null){
            return;
        }
        
        if (child.word != null) {
            if (!results.contains(child.word)) {
                results.add(child.word);
            }
        }
        
        char tmp = board[x][y];
        board[x][y] = 0;  // mark board[x][y] as used
        
        for (int i = 0; i < 4; i++) {
            if (!isValid(board, x + dx[i], y + dy[i])) {
                continue;
            }
            search(board, x + dx[i], y + dy[i], child, results);
        }
        
        board[x][y] = tmp;  // revert the mark
    }
    
    private boolean isValid(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }
        
        return board[x][y] != 0;
    }
}
