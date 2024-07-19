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
