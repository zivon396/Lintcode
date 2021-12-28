// 贪心算法
// 若当前 sum < 0, 则表示当前子数组的和已经为负, 累加到后面会使和更小, 因此放弃当前子数组, 重新开始.
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {

        // maxAns记录全局最大值 sum记录当前子数组的和
        int maxAns = Integer.MIN_VALUE, sum = 0;
        // 贪心
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            maxAns = Math.max(maxAns, sum);
            sum = Math.max(sum, 0);
        }

        return maxAns;
    }
}

// prefix
// 和 139 本质是一样的, 不需要排序, 只需要走一遍 + 同时记录最小值
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0){
            return 0;
        }
        
        long min = 0, max = Long.MIN_VALUE;
        
        long sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            max = Math.max(max, sum - min);
            min = Math.min(min, sum);
        }
        
        return (int)max;
    }
}
