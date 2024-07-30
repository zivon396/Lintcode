// 因为要保持原有 order, 不能用相向指针
public class Solution {
    /**
     * @param nums: an integer array
     * @return: nothing
     */
    public void moveZeroes(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0){
            return;
        }
        
        int left = 0, right = 0;
        while (right < nums.length){
            if (nums[right] != 0){
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
            }
            right++;
        }
        
    }
}
