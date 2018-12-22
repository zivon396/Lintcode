public class Solution {
    /**
     * @param s: A string
     * @return: Whether the string is a valid palindrome
     */
    public boolean isPalindrome(String s) {
        // write your code here
        if (s == null || s.length() == 0){
            return true;
        }
        
        int len = s.length();
        int left = 0, right = len - 1;
        while (left < right){
            while (left < len && !isValid(s.charAt(left))){
                left++;
            }
            if (left == len){
                return true;
            }
            
            while (right >= 0 && !isValid(s.charAt(right))){
                right--;
            }
            
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                break;
            } else {
                left++;
                right--;
            }
        }
        
        return right <= left;
    }
    private boolean isValid (char c){
        return Character.isLetter(c) || Character.isDigit(c);
    }
}