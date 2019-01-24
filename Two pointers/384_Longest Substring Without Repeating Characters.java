public class Solution {
    /**
     * @param s: a string
     * @return: an integer
     */
    public int lengthOfLongestSubstring(String s) {
        // write your code here
        if (s == null){
            return 0;
        }
        int[] map = new int[256];
        int j = 0;
        int ans = 0;
        for (int i = 0; i < s.length(); i++){
            while (j < s.length() && map[s.charAt(j)] == 0){
                map[s.charAt(j)] = 1;
                ans = Math.max(ans, j - i + 1);
                j++;
            }
            map[s.charAt(i)] = 0;
        }
        return ans;
    }
}

// version 2:
public class Solution {
    /**
     * @param s: a string
     * @return: an integer
     */
    public int lengthOfLongestSubstring(String s) {
        // write your code here
        if (s == null){
            return 0;
        }
        
        char[] sc = s.toCharArray();
        int[] cnt = new int[256];
        int ans = 0;
        int sum = 0;
        
        for (int l = 0, r = 0; r < sc.length; r++){
            cnt[sc[r]]++;
            while (cnt[sc[r]] > 1){
                cnt[sc[l++]]--;
            }
            
            ans = Math.max(ans, r - l + 1);
        }
        
        return ans;
    }
}
