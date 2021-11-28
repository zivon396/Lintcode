// DP
public class Solution {
    /**
     * @param nums: An array of integers
     * @return: An integer
     */
    public int maxProduct(int[] nums) {
        // write your code here
        int[] max = new int[2];
        int[] min = new int[2];
        
        min[0] = max[0] = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i % 2] = max[i % 2] = nums[i];
            if (nums[i] > 0) {
                max[i % 2] = Math.max(max[i % 2], max[(i - 1) % 2] * nums[i]);
                min[i % 2] = Math.min(min[i % 2], min[(i - 1) % 2] * nums[i]);
            } else if (nums[i] < 0) {
                max[i % 2] = Math.max(max[i % 2], min[(i - 1) % 2] * nums[i]);
                min[i % 2] = Math.min(min[i % 2], max[(i - 1) % 2] * nums[i]);
            }
            
            result = Math.max(result, max[i % 2]);
        }
        
        return result;
    }
}
