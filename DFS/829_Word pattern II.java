// 不能使用 DP 或记忆化搜索, 因为 Map 的信息无法存储在 memo 或者 DP 里
// DFS
public class Solution {
    /**
     * @param pattern: a string,denote pattern string
     * @param str: a string, denote matching string
     * @return: a boolean
     */
    public boolean wordPatternMatch(String pattern, String str) {
        // write your code here
        Map<Character, String> dict = new HashMap<>();

        return dfs(str, 0, pattern, 0, dict);
    }

    private boolean dfs (String s, int sIndex, String p, int pIndex,
                         Map<Character, String> dict){
        if (pIndex >= p.length()){
            return sIndex >= s.length();
        }
        if (sIndex >= s.length()){
            return pIndex >= p.length();
        }

        char pChar = p.charAt(pIndex);
        boolean flag = dict.containsKey(pChar);
        for (int len = 1; sIndex + len <= s.length(); len++){
            String cur = s.substring(sIndex, sIndex + len);
            if (flag && !cur.equals(dict.get(pChar))){
                continue;
            }
            if (!flag && exists(dict, cur)){
                continue;
            }
            
            dict.put(pChar, cur);
            if (dfs(s, sIndex + len, p, pIndex + 1, dict)){
                return true;
            }
            if (flag){
                continue;
            }
            dict.remove(pChar);
        }

        return false;
    }

    private boolean exists (Map<Character, String> dict, String s){
        for (Character c: dict.keySet()){
            if (dict.get(c).equals(s)){
                return true;
            }
        }

        return false;
    }
}
