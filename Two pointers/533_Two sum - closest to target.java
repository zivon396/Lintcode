public class Solution {
    /**
     * @param nums: an integer array
     * @param target: An integer
     * @return: the difference between the sum and the target
     */
    public int twoSumClosest(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0){
            return Integer.MAX_VALUE;
        }
        
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        int diff = Integer.MAX_VALUE;
        while (left < right){
            if (nums[left] + nums[right] == target){
                return 0;
            } else if (nums[left] + nums[right] < target){
                diff = Math.min(diff, Math.abs(nums[left] + nums[right] - target));
                left++;
            } else {
                diff = Math.min(diff, Math.abs(nums[left] + nums[right] - target));
                right--;
            }
        }
        
        return diff;
    }
}