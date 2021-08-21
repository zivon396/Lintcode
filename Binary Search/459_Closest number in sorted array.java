public class Solution {
    /*
     * @param A: an integer array sorted in ascending order
     * @param target: An integer
     * @return: an integer
     */
    // 二分查找时不用abs, 只需要通过比较大小找到位置。最后因为一定有 nums[start] <= target 以及 nums[end] >= target, 所以也不用abs.
    public int closestNumber(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0){
            return -1;
        }
        
        int start = 0 , end = nums.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] < target){
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (target - nums[start] < nums[end] - target){
            return start;
        } else {
            return end;
        }
    }
}
