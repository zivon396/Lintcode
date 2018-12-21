public class Solution {
    /**
     * @param A: an integer array
     * @return: nothing
     */
    public void sortIntegers2(int[] nums) {
        // write your code here
        for (int i = nums.length / 2; i >= 0; i--){
            maxHeapify(nums, i, nums.length - 1);
        }
        
        for (int i = nums.length - 1; i >= 0; i--){
            int tmp = nums[0];
            nums[0] = nums[i];
            nums[i] = tmp;
            maxHeapify(nums, 0, i - 1);
        }
    }
    
    private void maxHeapify (int[] nums, int start, int end){
        int parent = start;
        
        while (parent <= end){
            int left = parent * 2 + 1;
            int right = parent * 2 + 2;
            int child = -1;
            
            if (left <= end && right <= end){
                child = nums[left] >= nums[right] ? left : right;
            } else if (left <= end){
                child = left;
            } else {
                return;
            }
            
            if (nums[parent] >= nums[child]){
                return;
            }
            
            int temp = nums[parent];
            nums[parent] = nums[child];
            nums[child] = temp;
            
            parent = child;
        }
    }
}