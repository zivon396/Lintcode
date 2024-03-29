// 每次固定最大的一边, 这样可以保证另外两边之差一定小于第三边. 然后就变成了 Two sum greater than target.
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
