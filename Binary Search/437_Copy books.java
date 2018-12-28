public class Solution {
    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        int l = 0;
        int r = Integer.MAX_VALUE;

        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(pages, mid, k)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        
        if (check(pages, l, k)) {
            return l;
        }
        return r;
    }
    
    private boolean check(int[] pages, int limit, int k) {
        int num = 0;
        int left = 0;
        for (int item : pages) {
            if (item > limit) {
                return false;
            }
            if (item > left) {
                num++;
                left = limit;
            }
            left -= item;
        }
        return num <= k;
    }
}