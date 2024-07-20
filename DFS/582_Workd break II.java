// 记忆化搜索
public class Solution {
    /**
     * @param s: A string
     * @param wordDict: A set of words.
     * @return: All possible sentences.
     *          we will sort your return value in output
     */
    public List<String> wordBreak(String s, Set<String> wordDict) {
        // write your code here
        List<String> res = new ArrayList<>();
        Map<String, List<String>> memo = new HashMap<>();
        res = dfs(s, wordDict, memo);

        return res;
    }

    private List<String> dfs (String s,
                              Set<String> wordDict,
                              Map<String, List<String>> memo){
        if (memo.containsKey(s)){
            return memo.get(s);
        }

        List<String> res = new ArrayList<>();

        if (s.length() == 0){
            return res;
        }

        if (wordDict.contains(s)){
            res.add(s);
        }

        for (int len = 1; len < s.length(); len++){
            String next = s.substring(0, len);
            if (!wordDict.contains(next)){
                continue;
            }

            String suffix = s.substring(len);
            List<String> seg = dfs(suffix, wordDict, memo);

            for (int i = 0; i < seg.size(); i++){
                String result = next + " " + seg.get(i);
                res.add(result);
            }
        }

        memo.put(s, res);

        return res;
    }
}


// 纯 DFS 会超时
public class Solution {
    /**
     * @param s: A string
     * @param wordDict: A set of words.
     * @return: All possible sentences.
     *          we will sort your return value in output
     */
    public List<String> wordBreak(String s, Set<String> wordDict) {
        // write your code here
        List<String> res = new ArrayList<>();
        List<String> com = new ArrayList<>();
        dfs(s, wordDict, com, 0, res);

        return res;
    }

    private void dfs (String s,
                      Set<String> wordDict,
                      List<String> com,
                      int startIndex,
                      List<String> res){
        if (startIndex >= s.length()){
            String result = "";
            for (String str: com){
                result += str;
                result += " ";
            }
            result = result.substring(0, result.length() - 1);
            res.add(result);
            return;
        }

        for (int i = startIndex; i < s.length(); i++){
            String next = s.substring(startIndex, i + 1);
            if (!wordDict.contains(next)){
                continue;
            }
            com.add(next);
            dfs(s, wordDict, com, i + 1, res);
            com.remove(com.size() - 1);
        }
    }
}
