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
