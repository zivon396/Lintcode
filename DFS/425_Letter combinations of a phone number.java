// 直接用 per.length() 来作判定, 可以省一层循环.
public class Solution {
    /**
     * @param digits: A digital string
     * @return: all posible letter combinations
     */
    public List<String> letterCombinations(String digits) {
        // write your code here
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0){
            return res;
        }
        Map<Character, char[]> dict = new HashMap<>();
        dict.put('0', new char[] {});
        dict.put('1', new char[] {});
        dict.put('2', new char[] { 'a', 'b', 'c' });
        dict.put('3', new char[] { 'd', 'e', 'f' });
        dict.put('4', new char[] { 'g', 'h', 'i' });
        dict.put('5', new char[] { 'j', 'k', 'l' });
        dict.put('6', new char[] { 'm', 'n', 'o' });
        dict.put('7', new char[] { 'p', 'q', 'r', 's' });
        dict.put('8', new char[] { 't', 'u', 'v'});
        dict.put('9', new char[] { 'w', 'x', 'y', 'z' });
        
        String per = "";
        helper(digits, per, dict, res);
        
        return res;
    }
    
    private void helper (String digits, String per, Map<Character, char[]> dict, List<String> res){
        if (per.length() == digits.length()){
            res.add(new String(per));
            return;
        }
        
        for (Character c: dict.get(digits.charAt(per.length())) ){
            per += Character.toString(c);
            helper(digits, per, dict, res);
            per = per.substring(0, per.length() - 1);
        }
    }
}
