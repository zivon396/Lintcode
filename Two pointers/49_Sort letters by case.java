// 相向
public class Solution {
    /**
     * @param chars: The letter array you should sort by Case
     * @return: nothing
     */
    public void sortLetters(char[] chars) {
        // write your code here
        if (chars == null || chars.length == 0){
            return;
        }

        int left = 0, right = chars.length - 1;
        while (left <= right){
            while (left <= right && Character.isLowerCase(chars[left])){
                left++;
            }
            while (left <= right && Character.isUpperCase(chars[right])){
                right--;
            }

            if (left <= right){
                char tmp = chars[left];
                chars[left] = chars[right];
                chars[right] = tmp;
                left++;
                right--;
            }
        }
    }
}

// 同向
public class Solution {
    /**
     * @param chars: The letter array you should sort by Case
     * @return: nothing
     */
    public void sortLetters(char[] chars) {
        // write your code here
        if (chars == null || chars.length == 0){
            return;
        }

        int left = 0, right = 0;
        while (right < chars.length){
            if (Character.isLowerCase(chars[right])){
                char tmp = chars[left];
                chars[left] = chars[right];
                chars[right] = tmp;
                left++;
            }
            right++;
        }
    }
}
