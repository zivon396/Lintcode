public class Solution {
    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0){
            return -1;
        }
        
        int start = 0, end = nums.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[mid + 1]){
                end = mid;
            } else {
                start = mid;
            }
        }
        
        if (nums[start] < nums[end]){
            return end;
        } else {
            return start;
        }
    }
}
