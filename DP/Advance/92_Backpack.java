// version 1:
// f[i][j] 表示前 i 个物品选一些物品放入容量为 j 的背包中能否放满
// 时间复杂度 O(nm), 空间复杂度 O(nm)
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        boolean f[][] = new boolean[A.length + 1][m + 1];
        f[0][0] = true;

        for (int i = 1; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= A[i-1] && f[i-1][j - A[i-1]]) {
                    f[i][j] = true;
                }
            }
        }
        
        for (int i = m; i >= 0; i--) {
            if (f[A.length][i]) {
                return i;
            }
        }
        
        return 0;
    }
}

// version 2:
// dp[i][j]表示前 i 个物品放入容量为 j 的背包里, 最大的装载量
// 滚动数组优化 => 空间复杂度 O(m)
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        // write your code here
        // 如果背包容量或者物品数量为0，则直接返回
        if (A.length == 0 || m == 0) {
            return 0;
        }
        int n = A.length;
        int[] dp = new int[m + 1];
        
        for (int i = 0; i < n; i++) {
            // 滚动数组优化 倒序枚举j
            // 因为计算结果是根据前面的数据来的，如果从前面开始计算，新的计算结果会把上一轮的计算结果覆盖掉，计算结果就不对了
            for (int j = m; j >= A[i]; j--) {
                dp[j] = Integer.max(dp[j], dp[j - A[i]] + A[i]);
            }
        }
      
        return dp[m];
    }
}
