// set 里加的是 index, 因为数字会重复
// 注意 continue 的条件: set 不包含前一个重复的数字 -> 说明途经这个数字的所有排列都一定被遍历过一次了.
public class Solution {
    /*
     * @param :  A list of integers
     * @return: A list of unique permutations
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0){
            res.add(new ArrayList<>());
            return res;
        }
        
        Arrays.sort(nums);
        Set<Integer> visited = new HashSet<>();
        List<Integer> per = new ArrayList<>();
        helper(nums, per, visited, res);
        
        return res;
    }
    
    private void helper (int[] nums,
                         List<Integer> per,
                         Set<Integer> visited,
                         List<List<Integer>> res){
        if (per.size() == nums.length){
            res.add(new ArrayList(per));
            return;
        }
        
        for (int i = 0; i < nums.length; i++){
            if (i > 0 && !visited.contains(i - 1) && nums[i] == nums[i - 1]){
                continue;
            }
            if (visited.contains(i)){
                continue;
            }
            per.add(nums[i]);
            visited.add(i);
            helper(nums, per, visited, res);
            visited.remove(i);
            per.remove(per.size() - 1);
        }
    }
}
