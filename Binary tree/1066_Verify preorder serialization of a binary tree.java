public class Solution {
    /**
     * @param preorder: a string
     * @return: return a bool
     */
    public boolean isValidSerialization(String preorder) {
        String s = preorder;
        boolean flag = true;
        while (s.length() > 1) {
            int index = s.indexOf(",#,#");
            if (index < 0) {
                flag = false;
                break;
            }
            int start = index;
            while (start > 0 && s.charAt(start - 1) != ',')
            {
                start --;
            }
            if (s.charAt(start) == '#') {
                flag = false;
                break;
            }
            s = s.substring(0, start) + s.substring(index + 3);
        }
        if (s.equals("#") && flag) 
            return true;
        else 
            return false;
    }
}