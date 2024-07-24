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
