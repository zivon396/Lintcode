// 同向指针 (原创)
public class Solution {
    /**
     * @param a: An integer array.
     * @return: nothing
     */
    public void rerange(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0){
            return;
        }
        int sign = -1;
        if (getNumOfPositive(nums) > nums.length / 2){
            sign = 1;
        }

        int left = 0, p = 1;
        while (p < nums.length){
            if (left >= p){
                p++;
            }

            if (nums[left] * sign > 0){
                left++;
                sign = -sign;
                continue;
            }
            else if (nums[p] * sign > 0){
                int tmp = nums[left];
                nums[left] = nums[p];
                nums[p] = tmp;
                left++;
                sign = -sign;
            }
            p++;
        }
    }

    private int getNumOfPositive (int[] nums){
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] > 0){
                count++;
            }
        }

        return count;
    }
}
