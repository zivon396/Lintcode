public class Trie {
    private class Node{
        // isWord表示这个结点是否为一个单词的结尾
        // next[]表示这个结点的下一个26个字母结点
        public boolean isWord;  
        public Node[] next; 
        
        public Node() {
            this.isWord = false;
            this.next = new Node[26];
        }
    }
    
    private Node root;
    
    public Trie() {
        root = new Node(); 
    }

    /*
     * @param word: a word
     * @return: nothing
     */
    public void insert(String word) {
        Node now = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            // 依次寻找每个字符
            // 如果所有下一个结点中没有当前字符，则增加新结点到下一个next[pos]
            int pos = word.charAt(i) - 'a';
            if (now.next[pos] == null) {
                now.next[pos] = new Node();
            }
            now = now.next[pos];
        }
        now.isWord = true;
    }

    /*
     * @param word: A string
     * @return: if the word is in the trie.
     */
    public boolean search(String word) {
        // 查找单词
        int n = word.length();
        Node now = root;
        for (int i = 0; i < n; i++) {
            int ch = word.charAt(i) - 'a';
            // 如果有下一个对应字符的结点，那么继续查找下一个结点
            // 如果没有下一个对应字符的结点，那么说明查找单词失败
            if (now.next[ch] != null) {
                now = now.next[ch];
            }
            else {
                return false;
            }
        }
        // 如果每个字符都有且是完整单词，才说明查找单词成功
        return now.isWord;
    }

    /*
     * @param prefix: A string
     * @return: if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        // 查找前缀
        int n = prefix.length();
        Node now = root;
        for (int i = 0; i < n; i++) {
            int ch = prefix.charAt(i) - 'a';
            // 如果有下一个对应字符的结点，那么继续查找下一个结点
            // 如果没有下一个对应字符的结点，那么说明查找前缀失败
            if (now.next[ch] != null) {
                now = now.next[ch];
            }
            else {
                return false;
            }
        }
        return true;
    }
}
