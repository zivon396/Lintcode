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
    
    public int getSum (String prefix){
        int sum = 0;
        if (value > 0){
            sum += value;
        }
        for (int i = 0; i < 26; i++){
            if (children[i] != null){
                sum += children[i].getSum(prefix + (char)('a' + i));
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
            return node.getSum(prefix);
        }
        return 0;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */