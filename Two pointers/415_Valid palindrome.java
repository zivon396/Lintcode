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
            // 此处不必加 left < right 的判断, 因为 left 加到最后一定会停在 right 处
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

// 这个更好 (原创)
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

        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right){
            while (left < right && !isValid(chars[left])){
                left++;
            }
            while (left < right && !isValid(chars[right])){
                right--;
            }
            if (Character.toLowerCase(chars[left]) != Character.toLowerCase(chars[right])){
                break;
            }
            left++;
            right--;
        }

        return left >= right;
    }

    private boolean isValid (char c){
        return Character.isLetter(c) || Character.isDigit(c);
    }
}
