// 注意 insert 的时候每个途经的 node 都要 + sum
// old key 有新 value 的时候, insert (val - old)
class MapSum {

    public class TrieNode {
        public TrieNode[] son = new TrieNode[26];
        public int sum = 0;
    }

    public class Trie {
        public TrieNode root = new TrieNode();
        
        public void insert(String s, int value) {
            TrieNode cur = this.root;
            for(int i = 0; i < s.length(); i++) {
                int sid = s.charAt(i) - 'a';
                if(cur.son[sid] == null) {
                    cur.son[sid] = new TrieNode();
                }
                cur = cur.son[sid];
                cur.sum += value;
            }
        }
        
        public int find(String s) {
            TrieNode cur = root;
            for(int i = 0; i < s.length(); i++) {
                int sid = s.charAt(i) - 'a';
                if(cur.son[sid] == null) return 0;
                cur = cur.son[sid];
            }
            return cur.sum;
        }
        
    }
    
    public Trie trie;
    public HashMap<String, Integer> dict;
    
    /** Initialize your data structure here. */
    public MapSum() {
        this.trie = new Trie();
        dict = new HashMap<>();
    }
    
    public void insert(String key, int val) {
        int old = dict.get(key) == null ? 0 : dict.get(key);
        dict.put(key, val);
        this.trie.insert(key, val - old);
    }
    
    public int sum(String prefix) {
        return this.trie.find(prefix);
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */


// version 2
class TrieNode {
    private TrieNode[] children;
    public int value;
    
    public TrieNode (){
        children = new TrieNode[26];
        value = 0;
    }
    
    public void insert (String word, int value, int index){
        if (index == word.length()){
            this.value = value;
            return;
        }
        
        int pos = word.charAt(index) - 'a';
        if (children[pos] == null){
            children[pos] = new TrieNode();
        }
        children[pos].insert(word, value, index + 1);
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
    
    public int getSum (){
        int sum = 0;
        if (value > 0){
            sum += value;
        }
        for (int i = 0; i < 26; i++){
            if (children[i] != null){
                sum += children[i].getSum();
            }
        }
        
        return sum;
    }
}

class MapSum {
    private TrieNode root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieNode();
    }
    
    public void insert(String key, int val) {
        root.insert(key, val, 0);
    }
    
    public int sum(String prefix) {
        TrieNode node = root.find(prefix, 0);
        if (node != null){
            return node.getSum();
        }
        return 0;
    }
}
