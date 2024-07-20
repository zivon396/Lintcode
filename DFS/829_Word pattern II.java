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
        // 这个判断用不到
        // if (sIndex >= s.length()){
        //     return pIndex >= p.length();
        // }

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


// 九章答案
// 与 209、685 一样, 用 set 来判断 map 里含不含当前 String
// map 里含当前 char 时, 可以简洁地跳过
public class Solution {
    /**
     * @param pattern: a string,denote pattern string
     * @param str: a string, denote matching string
     * @return: a boolean
     */
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        return match(pattern, str, map, set);
    }
    
    private boolean match(String pattern,
                          String str,
                          Map<Character, String> map,
                          Set<String> set) {
        if (pattern.length() == 0) {
            return str.length() == 0;
        }
        
        Character c = pattern.charAt(0);
        if (map.containsKey(c)) {
            if (!str.startsWith(map.get(c))) {
                return false;
            }
            
            return match(
                pattern.substring(1),
                str.substring(map.get(c).length()),
                map,
                set
            );
        }
        
        for (int i = 0; i < str.length(); i++) {
            String word = str.substring(0, i + 1);
            if (set.contains(word)) {
                continue;
            }
            map.put(c, word);
            set.add(word);
            if (match(pattern.substring(1),
                      str.substring(i + 1),
                      map,
                      set)) {
                return true;              
            }
            set.remove(word);
            map.remove(c);
        }
        
        return false;
    }
}
