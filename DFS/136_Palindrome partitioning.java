public class Solution {
    /*
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {
        // write your code here
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0){
            return res;
        }
        
        List<String> com = new ArrayList<>();
        helper(s, com, 0, res);
        
        return res;
    }
    
    private void helper (String s, List<String> com, int startIndex, List<List<String>>  res){
         if (startIndex == s.length()){
             res.add(new ArrayList(com));
             return;
         }
         
         for (int i = 0; startIndex + i < s.length(); i++){
             String str = s.substring(startIndex, startIndex + i + 1);
             if (isValid(str)){
                 com.add(str);
                 helper(s, com, startIndex + i + 1, res);
                 com.remove(com.size() - 1);
             }
         }
    }
    
    private boolean isValid (String s){
        if (s.length() <= 1){
            return true;
        }
        for (int i = 0; i < s.length() / 2; i++){
            if (s.charAt(i) != s.charAt(s.length() - i - 1)){
                return false;
            }
        }
        
        return true;
    }
}