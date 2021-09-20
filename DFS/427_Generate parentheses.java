// left 代表剩余还能被加的 "(", value 代表已经加过的 "(".
public class Solution {
    /**
     * @param n: n pairs
     * @return: All combinations of well-formed parentheses
     */
    public List<String> generateParenthesis(int n) {
        // write your code here
        List<String> res = new ArrayList<>();
        if (n < 1){
            return res;
        }
        
        String com = "";
        helper(n, com, n, 0, res);
        
        return res;
    }
    
    private void helper (int n, String com, int left, int value, List<String> res){
        if (com.length() == n * 2){
            res.add(new String(com));
        }
        
        if (left > 0){
            com += "(";
            helper(n, com, left - 1, value + 1, res);
            com = com.substring(0, com.length() - 1);
        }
        
        if (value > 0){
            com += ")";
            helper(n, com, left, value - 1, res);
            com = com.substring(0, com.length() - 1);
        }
    }
}

// 方法二: left 代表已经加过的 "(", value 代表剩余还需完成的 "()".
public class Solution {
    /**
     * @param n: n pairs
     * @return: All combinations of well-formed parentheses
     */
    public List<String> generateParenthesis(int n) {
        // write your code here
        List<String> res = new ArrayList<>();
        if (n == 0){
            return res;
        }

        String com = "";
        int left = 0, value = n;
        helper(n, com, left, value, res);

        return res;
    }

    private void helper (int n,
                         String com,
                         int left,
                         int value,
                         List<String> res){
        if (value == 0 && left == n){
            res.add(new String(com));
            return;
        }

        if (left < n){
            com += "(";
            helper(n, com, left + 1, value, res);
            com = com.substring(0, com.length() - 1);
        }

        if (value > 0 && left + value > n){
            com += ")";
            helper(n, com, left, value - 1, res);
            com = com.substring(0, com.length() - 1);
        }
    }
}
