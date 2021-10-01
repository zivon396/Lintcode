// Quick sort
    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }
    
    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        
        int left = start;
        int right = end;
        int mid = start + (end - start) / 2;
        int pivot = nums[mid];
        
        // overview sorted
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            
            // swap left >= p with right <= p to make left <= p right >= p
            if (left <= right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        
        quickSort(nums, start, right);
        quickSort(nums, left, end);
    }

// Merge sort
    public void mergeSort(int[] nums) {
        int[] tmp = new int[nums.length];
        mergeSort(nums, tmp, 0, nums.length - 1);
    }
    
    private void mergeSort(int[] nums, int[] tmp, int start, int end) {
        if (start >= end) {
            return;
        }
        
        // divide
        int mid = start + (end - start) / 2;
        mergeSort(nums, tmp, start, mid);
        mergeSort(nums, tmp, mid + 1, end);
        
        // merge
        int index = start;
        int leftStart = start;
        int rightStart = mid + 1;
        
        while (leftStart <= mid && rightStart <= end) {
            if (nums[leftStart] <= nums[rightStart]) {
                tmp[index++] = nums[leftStart++];
            } else {
                tmp[index++] = nums[rightStart++];
            }
        }
        
        while (leftStart <= mid) {
            tmp[index++] = nums[leftStart++];
        }
        
        while (rightStart <= end) {
            tmp[index++] = nums[rightStart++];
        }
        
        for (int i = start; i <= end; i++) {
            nums[i] = tmp[i];
        }
    }

// Heap sort
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
