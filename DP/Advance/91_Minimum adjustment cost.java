// dp[i][j] => 元素 A[i]=j 时，A[i] 与 A[i-1] 差值不大于 target 所需要付出的最小代价
public class Solution {
    /*
     * @param A: An integer array
     * @param target: An integer
     * @return: An integer
     */
    public int minAdjustmentCost(List<Integer> A, int target) {
        int n = A.size();
        // dp[i][j]表示元素A[i]=j时，A[i]与A[i-1]差值不大于target所需要付出的最小代价
        int[][] dp = new int[n][101];
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= 100; j++) {
                // 初始化为极大值
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= 100; j++) {
                if (i == 0) {
                    // 临界值：第一个元素A[0]调整为j的代价 
                    dp[0][j] = Math.abs(j - A.get(0));
                }
                else {
                    // left为A[i]=j时，A[i-1]与A[i]差值不大于target的A[i-1]最小值
                    // right为A[i]=j时，A[i-1]与A[i]差值不大于target的A[i-1]最大值
                    int left = Math.max(1, j - target);  
                    int right = Math.min(100, j + target);  
                    for (int k = left; k <= right; k++) {
                        // 当A[i-1]=k时，答案为A[i-1]=k的代价dp[i-1][k]，加上A[i]=j的调整代价abs(j-A[i])
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.abs(j - A.get(i))); 
                    }
                }
                
            }
        }

        int mincost = Integer.MAX_VALUE;
        for (int i = 1; i <= 100; i++) {
            mincost = Math.min(mincost, dp[n - 1][i]);
        }

        return mincost;
    }
}
