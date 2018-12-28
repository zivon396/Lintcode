public class Solution {
    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        // write your code here
        int l = 1, res = 0;
        int r = 0;
        for (int item : L) {
            r = Math.max(r, item);
        }
        
        while (l <= r) {
            int mid = l + (r - l) / 2; // (l + r) / 2 may overflow
            if (count(L, mid) >= k) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        
        return res;
    }
    
    private int count(int[] L, int len) {
        int sum = 0;
        for (int item : L) {
            sum += item / len;
        }
        return sum;
    }
}