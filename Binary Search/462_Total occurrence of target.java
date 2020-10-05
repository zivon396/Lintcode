public class Solution {
    /**
     * @param A: A an integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */
    public int totalOccurrence(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0){
            return 0;
        }
        int left = 0, right = -1;
        
        int start = 0, end = nums.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] < target){
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (nums[start] == target){
            left = start;
        } else if (nums[end] == target){
            left = end;
        }
        
        start = 0; 
        end = nums.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target){
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (nums[end] == target){
            right = end;
        } else if (nums[start] == target){
            right = start;
        }
        
        return right - left + 1;
    }
}
