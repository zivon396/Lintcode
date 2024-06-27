// version 1: r++
// 不能只用一个数组 + 用减到 0 的方式来判断 -> 会丢失信息
public class Solution {
    /**
     * @param source : A string
     * @param target: A string
     * @return: A string denote the minimum window, return "" if there is no such a string
     */
    public String minWindow(String source , String target) {
        // write your code here
        char[] s = source.toCharArray();
        char[] t = target.toCharArray();

        if (t.length == 0){
            return "";
        }

        int[] cntS = new int[256];
        int[] cntT = new int[256];
        int unique = 0;

        for (char c: t){
            cntT[c]++;
            if (cntT[c] == 1){
                unique++;
            }
        }

        int left = -1, right = -1;
        int curt = 0;
        for (int l = 0, r = 0; r < s.length; r++){
            cntS[s[r]]++;
            if (cntS[s[r]] == cntT[s[r]]){
                curt++;
            }

            while (curt == unique){
                if (left == -1 || r - l < right - left){
                    left = l;
                    right = r + 1;
                }
                
                cntS[s[l]]--;
                if (cntS[s[l]] < cntT[s[l]]){
                    curt--;
                }
                l++;
            }
        }

        if (left == -1){
            return "";
        } else {
            return source.substring(left, right);
        }
    }
}

// version 2: l++
public class Solution {
    /**
     * @param source : A string
     * @param target: A string
     * @return: A string denote the minimum window, return "" if there is no such a string
     */
    public String minWindow(String source , String target) {
        // write your code here
        char[] s = source.toCharArray();
        char[] t = target.toCharArray();
        
        if (t.length == 0){
            return "";
        }
        
        int[] cntS = new int[256];
        int[] cntT = new int[256];
        int unique = 0;
        
        for (char c: t){
            cntT[c]++;
            if (cntT[c] == 1){
                unique++;
            }
        }
        
        int left = -1, right = -1;
        int l, r = 0;
        int curt = 0;
        for (l = 0; l < source.length(); l++){
            while (r < source.length() && curt < unique){
                cntS[s[r]]++;
                if (cntS[s[r]] == cntT[s[r]]){
                    curt++;
                }
                
                r++;
            }
            
            if (curt == unique){
                if (left == -1 || r - l < right - left){
                    left = l;
                    right = r;
                }
            }
            
            cntS[s[l]]--;
            if (cntS[s[l]] == cntT[s[l]] - 1){
                curt--;
            }
        }
        
        if (left == -1){
            return "";
        } else {
            return source.substring(left, right);
        }
    }
}
