class TrieNode {
    private TrieNode[] children;
    public boolean hasWord;
    
    public TrieNode() {
        children = new TrieNode[26];
        hasWord = false;
    }
    
    public void insert (String word, int index){
        if (index == word.length()){
            hasWord = true;
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

public class Trie {
    private TrieNode root;
    
    public Trie() {
        // do intialization if necessary
        root = new TrieNode();
    }

    /*
     * @param word: a word
     * @return: nothing
     */
    public void insert(String word) {
        // write your code here
        root.insert(word, 0);
    }

    /*
     * @param word: A string
     * @return: if the word is in the trie.
     */
    public boolean search(String word) {
        // write your code here
        TrieNode node = root.find(word, 0);
        return (node != null && node.hasWord);
    }

    /*
     * @param prefix: A string
     * @return: if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        // write your code here
        TrieNode node = root.find(prefix, 0);
        return node != null;
    }
}