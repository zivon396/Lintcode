public class Solution {
    public void rotateString(char[] str, int offset) {
        if (str == null || str.length == 0) return;
        offset %= str.length;
        reverse(str, 0, str.length - offset - 1);
        reverse(str, str.length - offset, str.length - 1);
        reverse(str, 0, str.length - 1);
    }
    
    private void reverse(char[] str, int start, int end) {
        while (start < end) {
            char temp = str[start];
            str[start] = str[end];
            str[end] = temp;
            start += 1;
            end -= 1;
        }
    }
}
