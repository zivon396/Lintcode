public class Solution {
    /*
     * @param A: an integer array sorted in ascending order
     * @param target: An integer
     * @return: an integer
     */
    public int closestNumber(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0){
            return -1;
        }
        
        int start = 0 , end = nums.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] == target){
                return mid;
            } else if (nums[mid] < target){
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (Math.abs(nums[start] - target) < Math.abs(nums[end] - target)){
            return start;
        } else {
            return end;
        }
    }
}