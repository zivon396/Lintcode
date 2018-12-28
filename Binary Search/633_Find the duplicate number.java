public class Solution {
    /**
     * @param nums: an array containing n + 1 integers which is between 1 and n
     * @return: the duplicate one
     */
    public int findDuplicate(int[] nums) {
        // Write your code here
        int l = 1;
        int r = nums.length - 1;  // n
        
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (count(nums, mid) <= mid) {
                l = mid;
            } else {
                r = mid;
            }
        }
        
        if (count(nums, l) > l) {
            return l;
        }
        return r;
    }
    
    private int count(int[] nums, int mid) {
        int cnt = 0;
        for (int item : nums) {
            if (item <= mid) {
                cnt++;
            }
        }
        return cnt;
    }
}