// 由于此题包含 ".", 因此 find 只能用 recursion
// version 1: Array + DFS
class TrieNode {

    public TrieNode[] children;
    public boolean hasWord;
    
    public TrieNode() {
        children = new TrieNode[26];
        for (int i = 0; i < 26; ++i)
            children[i] = null;
        hasWord = false;
    }
}

public class WordDictionary {
    private TrieNode root;
 
    public WordDictionary(){
        root = new TrieNode();
    }
 
    // Adds a word into the data structure.
    public void addWord(String word) {
        // Write your code here
        TrieNode now = root;
        for(int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (now.children[c - 'a'] == null) {
                now.children[c - 'a'] = new TrieNode();
            }
            now = now.children[c - 'a'];
        }
        now.hasWord = true;
    }
    
    boolean find(String word, int index, TrieNode now) {
        if(index == word.length()) {
            return now.hasWord;
        }
        
        Character c = word.charAt(index);
        if (c == '.') {
            for(int i = 0; i < 26; ++i) 
            if (now.children[i] != null) {
                if (find(word, index+1, now.children[i]))
                    return true;
            }
            return false;
        } else if (now.children[c - 'a'] != null) {
            return find(word, index+1, now.children[c - 'a']);  
        } else {
            return false;
        }
    }
    
    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        // Write your code here
        return find(word, 0, root);
    }
}

// version 2:
class TrieNode {
    public TrieNode[] children = null;
    public boolean hasWord;
    
    public TrieNode (){
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
    
    public TrieNode search (String word, int index){
        if (index == word.length()){
            return this;
        }
        
        if (word.charAt(index) == '.'){
            for (int i = 0; i < 26; i++){
                if (children[i] != null){
                    TrieNode res = children[i].search(word, index + 1);
                    if (res != null && res.hasWord){
                        return res;
                    }
                }
            }
            return null;
        } else {
            int pos = word.charAt(index) - 'a';
            if (children[pos] == null){
                return null;
            }
            return children[pos].search(word, index + 1);
        }
    }
}

public class WordDictionary {
    /*
     * @param word: Adds a word into the data structure.
     * @return: nothing
     */
    private TrieNode root;
    
    public WordDictionary (){
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        // write your code here
        root.insert(word, 0);
    }

    /*
     * @param word: A word could contain the dot character '.' to represent any one letter.
     * @return: if the word is in the data structure.
     */
    public boolean search(String word) {
        // write your code here
        TrieNode node = root.search(word, 0);
        if (node != null){
            return node.hasWord;
        }
        return false;
    }
}


// version 3: find 写在 Node 里, add 写在 Trie 里
class Node {
    public boolean isWord;
    public Node[] children;
    public Node (){
        this.isWord = false;
        this.children = new Node[26];
    }

    public boolean find (String word, int index){
        if (index == word.length()){
            return this.isWord;
        }

        if (word.charAt(index) == '.'){
            for (Node node: this.children){
                if (node != null && node.find(word, index + 1)){
                    return true;
                }
            }

            return false;
        }
        else {
            int pos = word.charAt(index) - 'a';
            if (this.children[pos] == null){
                return false;
            }

            return this.children[pos].find(word, index + 1);
        }
    }
}

class Trie {
    private Node root;
    public Trie (){
        this.root = new Node();
    }

    public void add (String word){
        Node now = root;
        int n = word.length();
        for (int i = 0; i < n; i++){
            int pos = word.charAt(i) - 'a';
            if (now.children[pos] == null){
                now.children[pos] = new Node();
            }
            now = now.children[pos];
        }

        now.isWord = true;
    }

    public boolean find (String word){
        return this.root.find(word, 0);
    }
}

public class WordDictionary {
    /*
     * @param word: Adds a word into the data structure.
     * @return: nothing
     */
    public Trie trie = new Trie();

    public void addWord(String word) {
        // write your code here
        trie.add(word);
    }

    /*
     * @param word: A word could contain the dot character '.' to represent any one letter.
     * @return: if the word is in the data structure.
     */
    public boolean search(String word) {
        // write your code here
        return trie.find(word);
    }
}
