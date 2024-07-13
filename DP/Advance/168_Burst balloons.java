// 记忆化搜索
// 思路和 Stone game 一样, 都是从大到小, 思考最后一个合并 k & k + 1 的区间 / 扎破第 k 个气球
// 把原数组扩展一下更方便
public class Solution {
    /**
     * @param nums: A list of integer
     * @return: An integer, maximum coins
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2];
        int[][] visit = new int[n + 2][n + 2]; 
        int[] arr = new int[n + 2];
        for (int i = 1; i <= n; i++){
            arr[i] = nums[i - 1];
        }
        arr[0] = 1;
        arr[n + 1] = 1;
        
        return search(arr, dp, visit, 1 , n);
    }
    public int search(int[] arr, int[][] dp, int[][] visit, int left, int right) {
        if (visit[left][right] == 1)
        	return dp[left][right];
    	
    	int res = 0;
        for (int k = left; k <= right; ++k){
        	int midValue = arr[left - 1] * arr[k] * arr[right + 1];
        	int leftValue = search(arr, dp, visit, left, k - 1);
        	int rightValue = search(arr, dp, visit, k + 1, right);
            res = Math.max(res, leftValue + midValue + rightValue);
        }

        visit[left][right] = 1;
        dp[left][right] = res;

        return res;
    }
}
