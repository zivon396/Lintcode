// 桶排序
// O(n) time complexity
// 把数字平均分到 (n - 1) 个 bucket 里. Maximum gap 一定是相邻两个桶的 min - max.
public class Solution {
    /**
     * @param nums: an array of integers
     * @return: the maximun difference
     */
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        
        int min = nums[0], max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        if (min == max) {
            return 0;
        }
        
        int n = nums.length;
        long[] bucketMin = new long[n];
        long[] bucketMax = new long[n];
        Arrays.fill(bucketMin, Long.MAX_VALUE);
        Arrays.fill(bucketMax, Long.MIN_VALUE);
        
        int gap = (int)Math.ceil((double)(max - min) / (n - 1));
        
        for (int num : nums) {
            int bucketIdx = ((num - min) / gap);
            bucketMin[bucketIdx] = Math.min(num, bucketMin[bucketIdx]);
            bucketMax[bucketIdx] = Math.max(num, bucketMax[bucketIdx]);
        }
        
        long res = 0;
        int pre = 0;
        for (int i = 0; i < bucketMin.length; i++) {
            if (bucketMin[i] != Long.MAX_VALUE) {
                res = Math.max(res, bucketMin[i] - bucketMax[pre]);
                pre = i;
            }
        }
        
        return (int)res;
    }
}
