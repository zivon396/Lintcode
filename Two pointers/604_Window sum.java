// Prefix sum
public class Solution {
    /**
     * @param nums: a list of integers.
     * @param k: length of window.
     * @return: the sum of the element inside the window at each moving.
     */
    public int[] winSum(int[] nums, int k) {
        if (k == 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] sum = new int[n + 1];
        sum[0] = 0;

        // 计算前缀和
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        int[] res = new int[n - k + 1];
        for (int i = k; i <= n; i++) {
            res[i - k] = sum[i] - sum[i - k];
        }

        return res;
    }
}
