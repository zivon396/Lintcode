// 本质: 在 string 中选取最少的 char 组成 combination, 使得 string valid => startIndex & (i + 1)
// 和 427 还不太一样:
// count[0] => 剩下的还可以被抵消的 "("  (也就是 value)
// count[1] => 在 count[0] 为 0 情况下出现的 ")"
// 由遍历的顺序可以保证 => 一定是所有多出来的 ")" 最先按顺序被去掉. 而一旦它们全部被去掉之后, 就会保证所有完整的 "()" 都不再被去掉
// 时间复杂度 O(n * 2^n) ???
public class Solution {
 
    public List<String> removeInvalidParentheses(String s) {
        
        List<String> results = new ArrayList<String>();       
        int[] result = getLeftRightCount(s);
        dfs(s, 0, result[0], result[1], results);
        
        return results;
    }
    
    private void dfs(String s, int startIndex, int leftCount, int rightCount, List<String> results) {
        // 这里的 leftCount 和 rightCount 和把新的 s 重新算出来的结果代表的意义不同
        // )((())))))()(((l((((  7 4  7 4
        //  ((())))))()(((l((((  7 3  7 3
        //  ((()))   ()(((l((((  7 0  7 0
        //  ((()))    )(((l((((  6 0  7 1
        // 用 leftCount & rightCount 来判断, 到第三行就不能继续去掉左括号了, 因为要保证结果是最长的
        if(leftCount == 0 && rightCount == 0 && isStringValid(s)) {
            results.add(s);
            return;
        }
        
        for(int i = startIndex; i < s.length(); i++) {
            if(i > startIndex && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }
            
            if(leftCount > 0 && s.charAt(i) == '(') {
                dfs(s.substring(0, i) + s.substring(i + 1), i, leftCount - 1, rightCount, results);
            }
            
            if(rightCount > 0 && s.charAt(i) == ')') {
                dfs(s.substring(0, i) + s.substring(i + 1), i, leftCount, rightCount - 1, results);
            }
        }
    }
    
    private boolean isStringValid(String s) {
        int[] result = getLeftRightCount(s);
        return result[0] == 0 && result[1] == 0;
    }
    
    private int[] getLeftRightCount(String s) {
        int[] count = new int[]{0, 0};
        for(char c : s.toCharArray()) {
            if(c == '(') {
                count[0]++;
            }    
            if(c == ')') {
                if(count[0] > 0) {
                    count[0]--;
                } else {
                    count[1]++;
                }
            }
        }
        return count;
    }
}


// 自创的 不知道为什么过不了
public class Solution {
    /**
     * @param s: The input string
     * @return: Return all possible results
     *          we will sort your return value in output
     */
    private int maxLen = 0;
    public List<String> removeInvalidParentheses(String s) {
        // Write your code here
        List<String> res = new ArrayList<>();
        int[] count = getLeftRightCounts(s);
        dfs("", s, count[0], count[1], 0, res);

        if (res.size() == 0){
            res.add("");
        }

        return res;
    }

    private void dfs (String pre,
                 String s,
                 int leftCount,
                 int rightCount,
                 int value,
                 List<String> res){
        if (value < 0){
            return;
        }
        if (leftCount == rightCount && isValid(s, value)){
            String result = pre + s;
            if (result.length() < maxLen){
                return;
            }
            maxLen = Math.max(maxLen, result.length());
            res.add(pre + s);
            return;
        }
        if (s.length() == 0){
            return;
        }

        String now = s.substring(0, 1);
        if (now.equals("(")){
            dfs(pre + now, s.substring(1), leftCount, rightCount, value + 1, res);
            if (pre.length() > 0 && !pre.substring(pre.length() - 1).equals("(")){
                dfs(pre, s.substring(1), leftCount - 1, rightCount, value, res);
            }
        }
        else if (now.equals(")")){
            dfs(pre + now, s.substring(1), leftCount, rightCount, value - 1, res);
            if (pre.length() > 0 && !pre.substring(pre.length() - 1).equals(")")){
                dfs(pre, s.substring(1), leftCount, rightCount - 1, value, res);
            }
        }
        else {
            dfs(pre + now, s.substring(1), leftCount, rightCount, value, res);
        }
    }

    private int[] getLeftRightCounts (String s){
        int[] res = new int[2];
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '('){
                res[0]++;
            }
            if (s.charAt(i) == ')'){
                res[1]++;
            }
        }

        return res;
    }

    private boolean isValid (String s, int value){
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '('){
                value++;
            }
            if (s.charAt(i) == ')'){
                value--;
            }

            if (value < 0){
                return false;
            }
        }

        return true;
    }
}
