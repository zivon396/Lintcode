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

// 原创版本 (更顺眼)
public class Solution {
    /**
     * @param nums: An array of integers
     * @return: An integer
     */
    public int maxProduct(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0){
            return 0;
        }
        if (nums.length == 1){
            return nums[0];
        }
        int n = nums.length;
        int[] min = new int[2];
        int[] max = new int[2];
        min[0] = nums[0];
        max[0] = nums[0];
        min[1] = Math.min(nums[1], nums[0] * nums[1]);
        max[1] = Math.max(nums[1], nums[0] * nums[1]);
        int result = Math.max(max[0], max[1]);

        for (int i = 2; i < n; i++){
            if (nums[i] > 0){
                max[i % 2] = Math.max(max[(i - 1) % 2] * nums[i], nums[i]);
                min[i % 2] = Math.min(min[(i - 1) % 2] * nums[i], nums[i]);
            }
            else {
                max[i % 2] = Math.max(min[(i - 1) % 2] * nums[i], nums[i]);
                min[i % 2] = Math.min(max[(i - 1) % 2] * nums[i], nums[i]);
            }

            result = Math.max(result, max[i % 2]);
        }

        return result;
    }
}
