public class Solution {
    /**
     * @param s a string
     * @return it's index
     */
    public int firstUniqChar(String s) {
        // Write your code here
        int[] cnt = new int[256];

        for (char c: s.toCharArray()) {
            cnt[c]++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (cnt[s.charAt(i)] == 1) {
                return i;
            }
        }
        
        return -1;
    }
}
