// version 1
// TrieNode 不自调
public class Solution {
     class TrieNode {
        List<String> startWith;
        TrieNode[] children;

        TrieNode() {
            startWith = new ArrayList<>();
            children = new TrieNode[26];
        }
    }

    class Trie {
        TrieNode root;

        Trie(String[] words) {
            root = new TrieNode();
            
            for (String w : words) {
                TrieNode cur = root;
                for (char ch : w.toCharArray()) {
                    int idx = ch - 'a';
                    if (cur.children[idx] == null)
                        cur.children[idx] = new TrieNode();
                    cur.children[idx].startWith.add(w);
                    cur = cur.children[idx];
                }
            }
        }

        List<String> findByPrefix(String prefix) {
            List<String> ans = new ArrayList<>();
            TrieNode cur = root;
            for (char ch : prefix.toCharArray()) {
                int idx = ch - 'a';
                if (cur.children[idx] == null)
                    return ans;

                cur = cur.children[idx];
            }
            
            ans.addAll(cur.startWith);
            return ans;
        }
    }

    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> ans = new ArrayList<>();
        if (words == null || words.length == 0)
            return ans;
        int len = words[0].length();
        Trie trie = new Trie(words);
        
        List<String> ansBuilder = new ArrayList<>();
        for (String w : words) {
            ansBuilder.add(w);
            search(len, trie, ans, ansBuilder);
            ansBuilder.remove(ansBuilder.size() - 1);
        }

        return ans;
    }

    private void search(int len, Trie tr, List<List<String>> ans,
            List<String> ansBuilder) {
        if (ansBuilder.size() == len) {
            ans.add(new ArrayList<>(ansBuilder));
            return;
        }

        int idx = ansBuilder.size();
        StringBuilder prefixBuilder = new StringBuilder();
        for (String s : ansBuilder)
            prefixBuilder.append(s.charAt(idx));
        
        List<String> startWith = tr.findByPrefix(prefixBuilder.toString());
        for (String sw : startWith) {
            ansBuilder.add(sw);
            search(len, tr, ans, ansBuilder);
            ansBuilder.remove(ansBuilder.size() - 1);
        }
    }
}

// version 2
// TrieNode 自调
class TrieNode {
    public TrieNode[] children;
    public List<String> startWith;
    
    public TrieNode (){
        children = new TrieNode[26];
        startWith = new ArrayList<>();
    }
    
    public void insert (String word, int index){
        if (index > 0){
            startWith.add(word);
        }
        if (index == word.length()){
            return;
        }
        
        int pos = word.charAt(index) - 'a';
        if (children[pos] == null){
            children[pos] = new TrieNode();
        }
        
        children[pos].insert(word, index + 1);
    }
    
    public List<String> findByPrefix (String prefix, int index){
        List<String> res = new ArrayList<>();
        if (index == prefix.length()){
            res.addAll(startWith);
            return res;
        }
        
        int pos = prefix.charAt(index) - 'a';
        if (children[pos] == null){
            return res;
        }
        
        res.addAll(children[pos].findByPrefix(prefix, index + 1));
        
        return res;
    }
}

public class Solution {
    /*
     * @param words: a set of words without duplicates
     * @return: all word squares
     */
    public List<List<String>> wordSquares(String[] words) {
        // write your code here
        List<List<String>> ans = new ArrayList<>();
        if (words == null || words.length == 0)
            return ans;
            
        int len = words[0].length();
        TrieNode root = new TrieNode();
        for (String word: words){
            root.insert(word, 0);
        }
        
        List<String> ansBuilder = new ArrayList<>();
        for (String w : words) {
            ansBuilder.add(w);
            search(len, root, ans, ansBuilder);
            ansBuilder.remove(ansBuilder.size() - 1);
        }

        return ans;
    }
    
    private void search(int len, TrieNode root, List<List<String>> ans,
            List<String> ansBuilder) {
        if (ansBuilder.size() == len) {
            ans.add(new ArrayList<>(ansBuilder));
            return;
        }

        int idx = ansBuilder.size();
        StringBuilder prefixBuilder = new StringBuilder();
        for (String s : ansBuilder)
            prefixBuilder.append(s.charAt(idx));
        
        List<String> startWith = root.findByPrefix(prefixBuilder.toString(), 0);
        for (String sw : startWith) {
            ansBuilder.add(sw);
            search(len, root, ans, ansBuilder);
            ansBuilder.remove(ansBuilder.size() - 1);
        }
    }
}
