// Maximum -> Math.max() 放 while 外面
public class Solution {
    /**
     * @param s: A string
     * @param k: An integer
     * @return: An integer
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        int[] cnt = new int[256];
        char[] sc = s.toCharArray();

        int ans = 0;
        int sum = 0;
        for (int l = 0, r = 0; r < s.length(); r++) {
            cnt[sc[r]]++;
            if (cnt[sc[r]] == 1) {
                sum++;
            }
            while (sum > k) {
                cnt[sc[l]]--;
                if (cnt[sc[l]] == 0) {
                    sum--;
                }
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}

// 基本一样
public class Solution {
    /**
     * @param s: A string
     * @param k: An integer
     * @return: An integer
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        int res = 0;
        int[] cnt = new int[256];
        if (s == null || s.length() == 0){
            return res;
        }

        int disdinct = 0;
        for (int l = 0, r = 0; r < s.length(); r++){
            if (cnt[s.charAt(r)] == 0){
                disdinct++;
            }
            cnt[s.charAt(r)]++;

            while (disdinct > k){
                cnt[s.charAt(l)]--;
                if (cnt[s.charAt(l++)] == 0){
                    disdinct--;
                }
            }

            res = Math.max(res, r - l + 1);
        }

        return res;
    }
}
