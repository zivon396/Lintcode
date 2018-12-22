public class Solution {
    /**
     * @param numbers: Give an array
     * @param target: An integer
     * @return: Find all unique quadruplets in the array which gives the sum of zero
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return res;
        }
        
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++){
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++){
                if (j > i + 1 && nums[j] == nums[j - 1]){
                    continue;
                }
                
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right){
                    if (nums[i] + nums[j] + nums[left] + nums[right] == target){
                        List<Integer> four = new ArrayList<>();
                        four.add(nums[i]);
                        four.add(nums[j]);
                        four.add(nums[left]);
                        four.add(nums[right]);
                        res.add(four);
                        left++;
                        right--;
                        
                        while (left < right && nums[left] == nums[left - 1]){
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]){
                            right--;
                        }
                    } else if (nums[i] + nums[j] + nums[left] + nums[right] < target){
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        
        return res;
    }
}