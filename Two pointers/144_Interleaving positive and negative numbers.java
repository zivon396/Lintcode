// 同向指针 (原创)
public class Solution {
    /**
     * @param a: An integer array.
     * @return: nothing
     */
    public void rerange(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0){
            return;
        }
        int sign = -1;
        if (getNumOfPositive(nums) > nums.length / 2){
            sign = 1;
        }

        int left = 0, p = 1;
        while (p < nums.length){
            if (left >= p){
                p++;
            }

            if (nums[left] * sign > 0){
                left++;
                sign = -sign;
                continue;
            }
            else if (nums[p] * sign > 0){
                int tmp = nums[left];
                nums[left] = nums[p];
                nums[p] = tmp;
                left++;
                sign = -sign;
            }
            p++;
        }
    }

    private int getNumOfPositive (int[] nums){
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] > 0){
                count++;
            }
        }

        return count;
    }
}

// 先 partition, 再交换正负数
public class Solution {
    public void rerange(int[] A) {
        int pos = 0, neg = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) {
                pos++;
            } else {
                neg++;
            }
        }
        
        partition(A, pos > neg);
        interleave(A, pos == neg);
    }
    
    private void partition(int[] A, boolean startPositive) {
        int flag = startPositive ? 1 : -1;
        int left = 0, right = A.length - 1;
        while (left <= right) {
            while (left <= right && A[left] * flag > 0) {
                left++;
            }
            while (left <= right && A[right] * flag < 0) {
                right--;
            }
            
            if (left <= right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;
                
                left++;
                right--;
            }
        }
    }
    
    private void interleave(int[] A, boolean has_same_length) {
        int left = 1, right = A.length - 1;
        if (has_same_length) {
            right = A.length - 2;
        }
        
        while (left < right) {
            int temp = A[left];
            A[left] = A[right];
            A[right] = temp;
            
            left += 2;
            right -= 2;
        }
    }
}
