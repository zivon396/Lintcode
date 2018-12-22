public class Solution {
    /**
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public List<List<Integer>> subsets(int[] nums) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0){
            res.add(new ArrayList<>());
            return res;
        }
        
        Arrays.sort(nums);
        List<Integer> com = new ArrayList<>();
        helper(nums, com, 0, res);
        
        return res;
    }
    
    private void helper (int[] nums,
                         List<Integer> com,
                         int startIndex,
                         List<List<Integer>> res){
        res.add(new ArrayList(com));
        
        for (int i = startIndex; i < nums.length; i++){
            com.add(nums[i]);
            helper(nums, com, i + 1, res);
            com.remove(com.size() - 1);
        }
    }
}