public class Solution {
    /**
     * @param n: An integer
     * @return: a list of combination
     */
    public List<List<Integer>> getFactors(int n) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (n < 4){
            return res;
        }
        
        List<Integer> com = new ArrayList<>();
        helper(n, com, 2, res);
        
        return res;
    }
    
    private void helper (int n,
                         List<Integer> com,
                         int startIndex,
                         List<List<Integer>> res){
        if (n == 1){
            res.add(new ArrayList(com));
            return;
        }
        
        for (int i = startIndex; i <= Math.sqrt(n); i++){
            if (n % i == 0){
                com.add(i);
                helper(n / i, com, i, res);
                com.remove(com.size() - 1);
            }
        }
        
        if (!com.isEmpty()){
            com.add(n);
            helper(1, com, startIndex, res);
            com.remove(com.size() - 1);
        }
    }
}