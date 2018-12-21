public class Solution {
    /**
     * @param numbers: Give an array numbers of n integer
     * @return: Find all unique triplets in the array which gives the sum of zero.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return res;
        }
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 1; i++){
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            int target = -nums[i];
            int left = i + 1, right = nums.length - 1;
            
            twoSum(nums, left, right, target, res);
        }
        
        return res;
    }
    
    private void twoSum (int[] nums,
                         int left,
                         int right,
                         int target,
                         List<List<Integer>> res){
        if (left >= right){
            return;
        }
        
        while (left < right){
            if (nums[left] + nums[right] == target){
                List<Integer> tri = new ArrayList<>();
                tri.add(-target);
                tri.add(nums[left]);
                tri.add(nums[right]);
                res.add(tri);
                
                left++;
                right--;
                while (left < right && nums[left] == nums[left - 1]){
                    left++;
                }
                while (left < right && nums[right] == nums[right + 1]){
                    right--;
                }
            } else if (nums[left] + nums[right] < target){
                left++;
            } else {
                right--;
            }
        }
    }
}