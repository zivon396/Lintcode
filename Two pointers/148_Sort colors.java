// p 和 right 交换不知道换过来的是什么, 而和 left 交换可以保证换过来的一定是 0 或 1 (因为 p 和 left 同向移动)
public class Solution {
    /**
     * @param nums: A list of integer which is 0, 1 or 2 
     * @return: nothing
     */
    public void sortColors(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0){
            return;
        }
        
        int left = 0, right = nums.length - 1;
        int p = 0;
        while (p <= right){
            if (nums[p] == 0){
                swap(nums, left, p);
                left++;
                p++;
            } else if (nums[p] == 1){
                p++;
            } else {
                swap(nums, p, right);
                right--;
            }
        }
    }
    
    private void swap (int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
