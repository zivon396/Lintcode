public class Solution {
    /**
     * @param num: Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return res;
        }
        
        Arrays.sort(nums);
        List<Integer> com = new ArrayList<>();
        helper(nums, com, 0, res, target);
        
        return res;
    }
    
    private void helper (int[] nums,
                         List<Integer> com,
                         int startIndex,
                         List<List<Integer>> res,
                         int target){
        if (target == 0){
            res.add(new ArrayList(com));
            return;
        }
        if (target < 0){
            return;
        }
        
        for (int i = startIndex; i < nums.length; i++){
            if (i != startIndex && nums[i] == nums[i - 1]){
                continue;
            }
            com.add(nums[i]);
            helper(nums, com, i + 1, res, target - nums[i]);
            com.remove(com.size() - 1);
        }
    }
}