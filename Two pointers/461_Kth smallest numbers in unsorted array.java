public class Solution {
    /**
     * @param k: An integer
     * @param nums: An integer array
     * @return: kth smallest element
     */
    public int kthSmallest(int k, int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length){
            return -1;
        }
        return partition(nums, 0, nums.length - 1, k - 1);
    }
    
    private int partition(int[] nums, int start, int end, int k) {
        // 这里可以是 start == end (且此情况只会发生在 pivot 为最值的时候)
        if (start >= end) {
            return nums[k];
        }
        
        int left = start, right = end;
        int pivot = nums[(start + end) / 2];
        
        while (left <= right) {
            // 这里不能是 nums[left] <= pivot -> 如果 pivot 是最大值则会无限迭代
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            // 这里不能是 nums[right] >= pivot -> 如果 pivot 是最小值则会无限迭代
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        
        if (k <= right) {
            return partition(nums, start, right, k);
        }
        if (k >= left) {
            return partition(nums, left, end, k);
        }
        return nums[k];
    }    
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
