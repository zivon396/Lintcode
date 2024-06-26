// version 1: quick select (partition)
public class Solution {
    /**
     * @param n: An integer
     * @param nums: An array
     * @return: the Kth largest element
     */
    public int kthLargestElement(int k, int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0){
            return 0;
        }
        
        int res = partition(nums, 0, nums.length - 1, nums.length - k);
        
        return res;
    }
    
    private int partition (int[] nums, int start, int end, int k){
        if (start >= end){
            return nums[k];
        }
        
        int left = start, right = end;
        int mid = start + (end - start) / 2;
        int pivot = nums[mid];
        while (left <= right){
            while (left <= right && nums[left] < pivot){
                left++;
            }
            while (left <= right && nums[right] > pivot){
                right--;
            }
            
            if (left <= right){
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        
        if (k <= right){
            return partition(nums, start, right, k);
        }
        if (k >= left){
            return partition(nums, left, end, k);
        }
        return nums[k];
    }
}

// version 2:
// 快速排序
public class Solution {
    /**
     * @param n: An integer
     * @param nums: An array
     * @return: the Kth largest element
     */
    public int kthLargestElement(int k, int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0){
            return 0;
        }
        
        int res = partition(nums, 0, nums.length - 1, nums.length - k);
        
        return res;
    }
    
    private int partition (int[] nums, int start, int end, int k){
        int left = start, right = end;
        int now = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= now) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= now) {
                left++;
            }
            nums[right] = nums[left];
        }
        if (left == k) {
            return now;
        } else if (left < k) {
            return partition(nums, left + 1, end, k);
        } else {
            return partition(nums, start, right - 1, k);
        }
    }
}
