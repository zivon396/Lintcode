// DP
public class Solution {
    /**
     * @param n: An integer
     * @return: An Integer
     */
    public int climbStairs2(int n) {
        if (n <= 1){
            return 1;
        }
        if (n == 2){
            return 2;
        }
        int a = 1, b = 1, c = 2;
        for (int i = 3; i < n + 1; i ++){
            int next = a + b + c;
            a = b;
            b = c;
            c = next;
        }
        return c;
    }
}

// 记忆化搜索
// 用 map 或 array 都可
public class Solution {
    /**
     * @param n: An integer
     * @return: An Integer
     */
    public int climbStairs2(int n) {
        return dfs(n, new HashMap<>());  
    }
    
    // 递归的定义：返回从起点0到终点i的方案数
    private int dfs(int i, Map<Integer, Integer> memo) {
        // 递归的出口
        if (i == 0 || i == 1) {
            return 1;
        }
        
        if (i == 2) {
            return 2;
        }
        
        if (memo.containsKey(i)) {
            return memo.get(i);
        }
        
        // 递归的拆解
        int num = dfs(i - 1, memo) + dfs(i - 2, memo) + dfs(i - 3, memo);
        memo.put(i, num);
        
        return num;
    }
}
