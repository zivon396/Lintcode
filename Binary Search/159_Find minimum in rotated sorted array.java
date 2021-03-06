public class Solution {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    // 注意要用 nums[nums.length - 1] 来比较 -> array可能并没有rotated
    public int findMin(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0){
            return -1;
        }
        
        int start = 0, end = nums.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] < nums[nums.length - 1]){
                end = mid;
            } else {
                start = mid;
            }
        }
        
        if (nums[start] < nums[nums.length - 1]){
            return nums[start];
        }
        return nums[end];
    }
}
