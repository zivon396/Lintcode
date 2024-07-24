// DP
// dp[i] => 前 i 个字符能否拆分成若干个单词
public class Solution {
    /*
     * @param s: A string
     * @param dict: A dictionary of words dict
     * @return: A boolean
     */
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null) {
            return true;
        }
        
        int maxLength = 0;
        for (String word : dict) {
            maxLength = Math.max(maxLength, word.length());
        }
      
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        
        for (int i = 1; i <= n; i++) {
            for (int l = 1; l <= maxLength; l++) {
                if (i < l) {
                    break;
                }
                if (!dp[i - l]) {
                    continue;
                }
                String word = s.substring(i - l, i);
                if (dict.contains(word)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n];
    }
}

// 记忆化搜索 stack overflow
public class Solution {
    /**
     * @param s: A string
     * @param wordSet: A dictionary of words dict
     * @return: A boolean
     */
    public boolean wordBreak(String s, Set<String> wordSet) {
        // write your code here
        if (s == null || s.length() == 0){
            return true;
        }
        boolean[] dp = new boolean[s.length()];
        boolean[] visited = new boolean[s.length()];

        return dfs(s, 0, wordSet, dp, visited);
    }

    private boolean dfs (String s, int startIndex, Set<String> wordSet, boolean[] dp, boolean[] visited){
        if (startIndex == s.length()){
            return true;
        }
        if (visited[startIndex]){
            return dp[startIndex];
        }

        boolean res = false;
        for (int len = 1; startIndex + len <= s.length(); len++){
            boolean tmp = wordSet.contains(s.substring(startIndex, startIndex + len)) &&
                        dfs(s, startIndex + len, wordSet, dp, visited);
            if (tmp){
                res = true;
                break;
            }
        }

        visited[startIndex] = true;
        dp[startIndex] = res;

        return res;
    }
}
