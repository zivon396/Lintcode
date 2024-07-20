// 记忆化搜索
public class Solution {
    /**
     * @param s: A string 
     * @param p: A string includes "?" and "*"
     * @return: is Match?
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        
        boolean[][] memo = new boolean[s.length()][p.length()];
        boolean[][] visited = new boolean[s.length()][p.length()];
        return isMatchHelper(s, 0, p, 0, memo, visited);
    }
    
    private boolean isMatchHelper(String s, int sIndex,
                                  String p, int pIndex,
                                  boolean[][] memo,
                                  boolean[][] visited) {
        // 如果 p 从pIdex开始是空字符串了，那么 s 也必须从 sIndex 是空才能匹配上
        if (pIndex == p.length()) {
            return sIndex == s.length();
        }
        
        // 如果 s 从 sIndex 是空，那么p 必须全是 * 
        if (sIndex == s.length()) {
            return allStar(p, pIndex);
        }
        
        if (visited[sIndex][pIndex]) {
            return memo[sIndex][pIndex];
        }
        
        char sChar = s.charAt(sIndex);
        char pChar = p.charAt(pIndex);
        boolean match;
        
        if (pChar == '*') {
            // 这里真的很妙!
            match = isMatchHelper(s, sIndex, p, pIndex + 1, memo, visited) ||
                isMatchHelper(s, sIndex + 1, p, pIndex, memo, visited);
        } else {
            match = charMatch(sChar, pChar) &&
                isMatchHelper(s, sIndex + 1, p, pIndex + 1, memo, visited);
        }
        
        visited[sIndex][pIndex] = true;
        memo[sIndex][pIndex] = match;
        return match;
    }
    
    private boolean charMatch(char sChar, char pChar) {
        return (sChar == pChar || pChar == '?');
    }
    
    private boolean allStar(String p, int pIndex) {
        for (int i = pIndex; i < p.length(); i++) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }
}


// 自己版本
public class Solution {
    /**
     * @param s: A string
     * @param p: A string includes "?" and "*"
     * @return: is Match?
     */
    public boolean isMatch(String s, String p) {
        // write your code here
        boolean[][] memo = new boolean[s.length()][p.length()];
        boolean[][] visited = new boolean[s.length()][p.length()];
        boolean res = dfs(s, p, 0, 0, memo, visited);

        return res;
    }

    private boolean dfs (String s, String p, int sIndex, int pIndex,
                         boolean[][] memo, boolean[][] visited){
        if (sIndex >= s.length() && pIndex >= p.length()){
            return true;
        }
        if (pIndex >= p.length()){
            return false;
        }
        if (sIndex >= s.length()){
            for (int i = pIndex; i < p.length(); i++){
                if (p.charAt(i) != '*'){
                    return false;
                }
            }

            return true;
        }
        if (visited[sIndex][pIndex]){
            return memo[sIndex][pIndex];
        }

        if (p.charAt(pIndex) != '*' && p.charAt(pIndex) != '?'){
            if (s.charAt(sIndex) != p.charAt(pIndex)){
                memo[sIndex][pIndex] = false;
                return false;
            }
            else {
                memo[sIndex][pIndex] = dfs(s, p, sIndex + 1, pIndex + 1, memo, visited);
            }
        }
        else if (p.charAt(pIndex) == '?'){
            memo[sIndex][pIndex] = dfs(s, p, sIndex + 1, pIndex + 1, memo, visited);
        }
        else {
            // 这里的 for 其实没必要
            for (int len = 0; sIndex + len <= s.length(); len++){
                boolean tmp = dfs(s, p, sIndex + len, pIndex + 1, memo, visited);
                if (tmp){
                    memo[sIndex][pIndex] = tmp;
                }
            }
        }

        visited[sIndex][pIndex] = true;
        
        return memo[sIndex][pIndex];
    }
}
