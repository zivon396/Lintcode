public class Solution {
    /*
     * @param : a string to be split
     * @return: all possible split string array
     */
    public List<List<String>> splitString(String s) {
        // write your code here
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0){
            res.add(new ArrayList<>());
            return res;
        }
        
        List<String> com = new ArrayList<>();
        helper(s, com, 0, res);
        
        return res;
    }
    
    private void helper (String s, List<String> com, int startIndex, List<List<String>> res){
        if (startIndex == s.length()){
            res.add(new ArrayList(com));
            return;
        }
        
        for (int i = 0; i < 2 && startIndex + i < s.length(); i++){
            String str = s.substring(startIndex, startIndex + i + 1);
            com.add(str);
            helper(s, com, startIndex + i + 1, res);
            com.remove(com.size() - 1);
        }
    }
}