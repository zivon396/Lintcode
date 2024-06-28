public class Solution {
    /*
     * @param A: Given an integer array
     * @return: nothing
     */
    public void heapify(int[] nums) {
        // write your code here
        for (int i = nums.length / 2; i >= 0; i--){
            helper(nums, i, nums.length - 1);
        }
    }
    
    private void helper (int[] nums, int start, int end){
        int parent = start;
        
        while (parent <= end){
            int left = parent * 2 + 1;
            int right = parent * 2 + 2;
            int child = -1;
            
            if (left <= end && right <= end){
                child = nums[left] <= nums[right] ? left : right;
            } else if (left <= end){
                child = left;
            } else {
                return;
            }
            
            if (nums[parent] <= nums[child]){
                return;
            }
            
            int tmp = nums[parent];
            nums[parent] = nums[child];
            nums[child] = tmp;
            
            parent = child;
        }
    }
}
