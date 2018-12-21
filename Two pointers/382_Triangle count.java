public class Solution {
    /**
     * @param S: A list of integers
     * @return: An integer
     */
    public int triangleCount(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0){
            return 0;
        }
        Arrays.sort(nums);
        
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            int left = 0, right = i - 1;
            while (left < right){
                if (nums[left] + nums[right] > nums[i]){
                    count += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        
        return count;
    }
}