// Trie + DFS
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

// 纯 DFS 超时
public class Solution {
    /**
     * @param words: a set of words without duplicates
     * @return: all word squares
     *          we will sort your return value in output
     */
    public List<List<String>> wordSquares(String[] words) {
        // write your code here
        List<List<String>> res = new ArrayList<>();
        int size = words[0].length();

        List<String> per = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        dfs(words, size, per, visited, res);

        return res;
    }

    private void dfs (String[] words,
                      int size,
                      List<String> per,
                      Set<String> visited,
                      List<List<String>> res){
        if (per.size() == size){
            if (isSquare(per)){
                res.add(new ArrayList<>(per));
            }
            return;
        }

        for (int i = 0; i < words.length; i++){
            per.add(words[i]);
            visited.add(words[i]);
            dfs(words, size, per, visited, res);
            visited.remove(words[i]);
            per.remove(per.size() - 1);
        }
    }

    private boolean isSquare (List<String> per){
        for (int i = 0; i < per.size(); i++){
            for (int j = i; j < per.size(); j++){
                if (per.get(i).charAt(j) != per.get(j).charAt(i)){
                    return false;
                }
            }
        }

        return true;
    }
}
