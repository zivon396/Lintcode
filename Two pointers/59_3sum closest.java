public class Solution {
    /**
     * @param numbers: Give an array numbers of n integer
     * @param target: An integer
     * @return: return the sum of the three integers, the sum closest target.
     */
    private int min = Integer.MAX_VALUE;
    private int sum = 0;
    
    public int threeSumClosest(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0){
            return Integer.MAX_VALUE;
        }
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 1; i++){
            int first = nums[i];
            int left = i + 1, right = nums.length - 1;
            twoSum(nums, left, right, first, target);
        }
        
        return sum;
    }
    
    private void twoSum (int[] nums, int left, int right, int first, int target){
        if (left >= right){
            return;
        }
        
        while (left < right){
            if (nums[left] + nums[right] + first == target){
                min = 0;
                sum = nums[left] + nums[right] + first;
                return;
            } else if (nums[left] + nums[right] + first < target){
                if (target - (nums[left] + nums[right] + first) < min){
                    min = target - (nums[left] + nums[right] + first);
                    sum = (nums[left] + nums[right] + first);
                }
                left++;
            } else {
                if ((nums[left] + nums[right] + first) - target < min){
                    min = (nums[left] + nums[right] + first) - target;
                    sum = (nums[left] + nums[right] + first);
                }
                right--;
            }
        }
    }
}