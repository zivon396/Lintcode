// f[i][j] => 前 i 个物品选一些放入容量为 j 的背包所组成的最大价值
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     * @return: The maximum value
     */

    public int backPackII(int m, int[] A, int V[]) {
        // write your code here
        int[][] dp = new int[A.length + 1][m + 1];
        for (int i = 0; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (A[i - 1] > j) {
                    dp[i][j] = dp[(i - 1)][j];
                } else {
                    dp[i][j] = Math.max(dp[(i - 1)][j], dp[(i - 1)][j - A[i - 1]] + V[i - 1]);
                }
            }
        }
      
        return dp[A.length][m];
    }
}

// 滚动数组优化
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     */
    public int backPackII(int m, int[] A, int V[]) {
        // write your code here
        int[] f = new int[m + 1];
        for (int i = 0; i <= m ; ++i) f[i] = 0;
        int n = A.length , i, j;
      
        for (i = 0; i < n; i++) {
            for (j = m; j >= A[i]; j--) {
                if (f[j] < f[j - A[i]] + V[i])
                    f[j] = f[j - A[i]] + V[i];
            }
        }
      
        return f[m];
    }
}

// 打印所有方案
// 与上面的不同, f[i][j] => 前 i 个物品选一些组成容量为 j 的最大价值 (容量 exactly 为 j)
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int V[]) {
        int n = A.length;
        if (n == 0) {
            return 0;
        }

        int[][] f = new int[n + 1][m + 1];
        int[][] pi = new int[n + 1][m + 1];
        // pi[i][j] = 0: f[i][j] does not use item i-1
        // pi[i][j] = 1: f[i][j] uses item i-1
        int i, j;
        f[0][0] = 0;
        for (i = 1; i <= m; ++i) {
            f[0][i] = -1; // cannot
        }

        for (i = 1; i <= n; ++i) {
            for (j = 0; j <= m; ++j) {
                f[i][j] = f[i - 1][j]; // do not use item i-1
                pi[i][j] = 0;
                if (j >= A[i - 1] && f[i - 1][j - A[i - 1]] != -1) {
                    // use item i-1
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - A[i - 1]] + V[i - 1]);
                    if (f[i][j] == f[i - 1][j - A[i - 1]] + V[i - 1]) {
                        pi[i][j] = 1;
                    }
                }
            }
        }

        int res = 0;
        int w = 0;
        for (j = 0; j <= m; ++j) {
            if (f[n][j] != -1) {
                res = Math.max(res, f[n][j]);
                if (res == f[n][j]) {
                    w = j;
                }
            }
        }

        for (i = n; i >= 1; --i) {
            if (pi[i][w] == 1) {
                System.out.println("Item: " + (i - 1) + " Weight: " + A[i - 1] + "  Value: " + V[i - 1]);
                w -= A[i - 1];
            }
        }

        return res;
    }
}
