// 方法 1: 以 nums[mid] 和 nums[nums.length - 1] 为双重比较标准
public class Solution {
    /**
     * @param A: an integer rotated sorted array
     * @param target: an integer to be searched
     * @return: an integer
     */
    public int search(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0){
            return -1;
        }
        
        int start = 0, end = nums.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] < nums[nums.length - 1]){
                if (target >= nums[mid] && target <= nums[nums.length - 1]){
                    start = mid;
                } else {
                    end = mid;
                }
            } else {
                if (target >= nums[0] && target <= nums[mid]){
                    end = mid;
                } else {
                    start = mid;
                }
            }
        }
        
        if (nums[start] == target){
            return start;
        }
        if (nums[end] == target){
            return end;
        }
        
        return -1;
    }
}

// 方法 2: 以 target 和 nums[nums.length - 1] 为双重比较标准
public class Solution {
    /**
     * @param A: an integer rotated sorted array
     * @param target: an integer to be searched
     * @return: an integer
     */
    public int search(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0){
            return -1;
        }
        
        int start = 0, end = nums.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (target <= nums[nums.length - 1]){
                if (nums[mid] > target && nums[mid] <= nums[nums.length - 1]){
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (nums[mid] > nums[nums.length - 1] && nums[mid] < target){
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        
        if (nums[start] == target){
            return start;
        }
        if (nums[end] == target){
            return end;
        }
        
        return -1;
    }
}
