public class Solution {
    /**
     * @param candidates: A list of integers
     * @param target: An integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0){
            return res;
        }
        
        Arrays.sort(candidates);
        List<Integer> com = new ArrayList<>();
        helper(candidates, com, 0, res, target);
        
        return res;
    }
    
    private void helper (int[] candidates,
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
        
        for (int i = startIndex; i < candidates.length; i++){
            if (i != startIndex && candidates[i] == candidates[i - 1]){
                continue;
            }
            com.add(candidates[i]);
            helper(candidates, com, i, res, target - candidates[i]);
            com.remove(com.size() - 1);
        }
    }
}