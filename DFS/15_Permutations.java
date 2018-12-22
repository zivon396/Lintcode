public class Solution {
    /*
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0){
            res.add(new ArrayList<>());
            return res;
        }
        
        Arrays.sort(nums);
        List<Integer> per = new ArrayList<>();
        Set<Integer> hash = new HashSet<>();
        helper(nums, per, hash, res);
        
        return res;
    }
    
    private void helper (int[] nums, List<Integer> per, Set<Integer> hash, List<List<Integer>> res){
        if (per.size() == nums.length){
            res.add(new ArrayList(per));
        }
        
        for (int i = 0; i < nums.length; i++){
            if (hash.contains(nums[i])){
                continue;
            }
            per.add(nums[i]);
            hash.add(nums[i]);
            helper(nums, per, hash, res);
            hash.remove(nums[i]);
            per.remove(per.size() - 1);
        }
    }
}