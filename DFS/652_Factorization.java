// 每次传入 recursion 的 n 都必须是 n / i, 可以理解为又迭代了一次子问题
// 因为子问题 (!com.isEmpty() 时) 不可能包括 [1, n] 的情况, 所以 for 循环之后要再加上 n 这个因子.
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
            // 如果 i > sqrt(n), 则说明 n 已经不可能再分解出 >= i 的因子了 (因为 < i 的因子已经被遍历过了), 所以此时只需要加上 n 本身即可.
            // e.g. [3, 4] n = 15
            if (n % i == 0){
                com.add(i);
                helper(n / i, com, i, res);
                com.remove(com.size() - 1);
            }
        }
        
        if (!com.isEmpty()){ // 不必再传入一个 n 来判断是否是其本身
            com.add(n);
            helper(1, com, startIndex, res);
            com.remove(com.size() - 1);
        }
    }
}
