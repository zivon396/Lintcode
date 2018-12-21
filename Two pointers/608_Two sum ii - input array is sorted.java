public class Solution {
    /**
     * @param nums: an array of Integer
     * @param target: target = nums[index1] + nums[index2]
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum(int[] nums, int target) {
        // write your code here
        int[] res = {-1, -1};
        if (nums == null || nums.length == 0){
            return res;
        }
        
        int left = 0, right = nums.length - 1;
        while (left <= right){
            if (nums[left] + nums[right] == target){
                res[0] = left + 1;
                res[1] = right + 1;
                return res;
            } else if (nums[left] + nums[right] < target){
                left++;
            } else {
                right--;
            }
        }
        
        return res;
    }
}