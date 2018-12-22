public class Solution {
    /**
     * @param n: Given the range of numbers
     * @param k: Given the numbers of combinations
     * @return: All the combinations of k numbers out of 1..n
     */
    public List<List<Integer>> combine(int n, int k) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (n < 1){
            return res;
        }
        
        List<Integer> com = new ArrayList<>();
        helper(n, com, 1, res, k);
        
        return res;
    }
    
    private void helper (int n, 
                         List<Integer> com, 
                         int startIndex, 
                         List<List<Integer>> res, 
                         int k){
        if (com.size() == k){
            res.add(new ArrayList(com));
            return;
        }
        
        for (int i = startIndex; i <= n; i++){
            com.add(i);
            helper(n, com, i + 1, res, k);
            com.remove(com.size() - 1);
        }
    }
}