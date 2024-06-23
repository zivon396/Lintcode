// 注意起始为 0 的情况
// com 用 List<String> 比较好, 最后再 build string
public class Solution {
    /**
     * @param s: the IP string
     * @return: All possible valid IP addresses
     */
    public List<String> restoreIpAddresses(String s) {
        // write your code here
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0){
            return res;
        }
        
        
        List<String> com = new ArrayList<>();
        helper(s, com, 0, res);
        
        return res;
    }
    
    private void helper (String s, List<String> com, int startIndex, List<String> res){
        if (startIndex >= s.length()){
            if (com.size() != 4){
                return;
            }
            String rst = "";
            for (String str: com){
                rst += str;
                rst += ".";
            }
            rst = rst.substring(0, rst.length() - 1);
            res.add(new String(rst));
            return;
        }
        
        if (s.charAt(startIndex) == '0'){
            com.add("0");
            helper(s, com, startIndex + 1, res);
            com.remove(com.size() - 1);
        }
        
        else{
            for (int i = 0; i < 3 && startIndex + i < s.length(); i++){
                String str = s.substring(startIndex, startIndex + i + 1);
                int num = Integer.parseInt(str);
                if (num < 256){
                    com.add(str);;
                    helper(s, com, startIndex + i + 1, res);
                    com.remove(com.size() - 1);
                }
            }
        }
    }
}

// 这个版本更简洁 (自创)
public class Solution {
    /**
     * @param s: the IP string
     * @return: All possible valid IP addresses
     */
    public List<String> restoreIpAddresses(String s) {
        // write your code here
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0){
            return res;
        }
        
        List<String> com = new ArrayList<>();
        helper(s, com, 0, res);
        
        return res;
    }
    
    private void helper (String s,
                         List<String> com,
                         int startIndex,
                         List<String> res){
        if (startIndex == s.length()){
            if (com.size() == 4){
                res.add(String.join(".", com));
            }

            return;
        }

        for (int i = 0; i < 3 && startIndex + i < s.length(); i++){
            String sub = s.substring(startIndex, startIndex + i + 1);

            if (sub.charAt(0) == '0' && sub.length() > 1){
                continue;
            }

            int num = Integer.parseInt(sub);
            if (num < 256){
                com.add(sub);
                helper(s, com, startIndex + i + 1, res);
                com.remove(com.size() - 1);
            }
        }
    }
}
