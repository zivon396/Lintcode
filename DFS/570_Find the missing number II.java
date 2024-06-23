// 需要一个 int[] 来判断是否 visited
public class Solution {
    /**
     * @param n: An integer
     * @param str: a string with number from 1-n in random order and miss one number
     * @return: An integer
     */
    private int miss = 0;
    
    public int findMissing2(int n, String str) {
        // write your code here
        if (str == null || str.length() == 0){
            return 0;
        }
        
        int[] dict = new int[n + 1];
        helper(str, dict, 0, n);
        
        return miss;
    }
    
    private void helper (String str,
                         int[] dict,
                         int startIndex,
                         int n){
        if (startIndex >= str.length()){
            List<Integer> count = new ArrayList<>();
            for (int i = 1; i <= n; i++){
                if (dict[i] == 0){
                    count.add(i);
                }
            }
            if (count.size() == 1){
                miss = count.get(0);
            }
            return;
        }
        
        for (int i = 0; i < 2 && startIndex + i < str.length(); i++){
            String s = str.substring(startIndex, startIndex + i + 1);
            int num = Integer.parseInt(s);
            if (num >= 1 && num <= n && dict[num] == 0){
                dict[num] = 1;
                helper(str, dict, startIndex + i + 1, n);
                dict[num] = 0;
            }
        }
    }
}

// 感觉应该判断一下 "01" 的情况?
public class Solution {
    /**
     * @param n: An integer
     * @param str: a string with number from 1-n in random order and miss one number
     * @return: An integer
     */
    private int miss = 0;
    
    public int findMissing2(int n, String str) {
        // write your code here
        if (str == null || str.length() == 0){
            return 0;
        }
        int[] visited = new int[n + 1];
        helper(n, str, 0, visited);
        
        return miss;
    }
    
    private void helper (int n,
                         String str,
                         int startIndex,
                         int[] visited){
        if (startIndex >= str.length()){
            int count = 0;
            int missFortune = 0;
            for (int i = 1; i <= n; i++){
                if (visited[i] == 0){
                    count++;
                    missFortune = i;
                }
            }

            if (count == 1){
                miss = missFortune;
            }

            return;
        }

        for (int i = 0; i < 2 && startIndex + i < str.length(); i++){
            String sub = str.substring(startIndex, startIndex + i + 1);
            if (sub.length() > 1 && sub.substring(0, 2) == "0"){
                continue;
            }
            int num = Integer.parseInt(sub);
            if (num > 0 && num <= n){
                if (visited[num] == 0){
                    visited[num] = 1;
                    helper(n, str, startIndex + i + 1, visited);
                    visited[num] = 0;
                }
            }
        }
    }
}
