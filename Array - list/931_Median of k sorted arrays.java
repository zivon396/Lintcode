// 基于二分法
// 设共有 n 个数组, 数组平均长度为 m, 则时间复杂度为 O(log(Range)nlog(m)), 其中 log(Range) 最大为32.
// 时间复杂度与 k 无关
public class Solution {
    /**
     * @param nums: the given k sorted arrays
     * @return: the median of the given k sorted arrays
     */
    public double findMedian(int[][] nums) {
        // write your code here
        if (nums == null || nums.length == 0){
            return 0.0;
        }
        
        int size = getTotal(nums);
        if (size == 0){
            return 0.0;
        }
        
        if (size % 2 == 1){
            return findKth(nums, size / 2 + 1);
        }
        
        return findKth(nums, size / 2) / 2.0 + findKth(nums, size / 2 + 1) / 2.0;
    }
    
    private int findKth (int[][] nums, int k){
        int start = 0, end = Integer.MAX_VALUE;
        
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (getNumLeq(nums, mid) < k){
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (getNumLeq(nums, start) == k){
            return start;
        }
        return end;
    }
    // 因为是要找从左到右数第 k 个, 所以必须用 Leq.
    private int getNumLeq (int[][] nums, int target){
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == null || nums[i].length == 0){
                continue;
            }
            sum += getNumLeq_each(nums[i], target);
        }
        
        return sum;
    }
    
    private int getNumLeq_each (int[] nums, int target){
        int start = 0, end = nums.length - 1;
        
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target){
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (nums[end] <= target){
            return end + 1;
        }
        if (nums[start] <= target){
            return start + 1;
        }
        
        return 0;
    }
    
    private int getTotal (int[][] nums){
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == null || nums[i].length == 0){
                continue;
            }
            sum += nums[i].length;
        }
        return sum;
    }
}
