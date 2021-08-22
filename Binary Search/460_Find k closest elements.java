public class Solution {
    /**
     * @param A: an integer array
     * @param target: An integer
     * @param k: An integer
     * @return: an integer array
     */
    // 先二分查找找位置, 再双指针. 直接用二分得到的 start 和 end 就可以, 最后也不用判断, 直接双 while 循环.
    // 其实不用 Math.abs(), 因为一定有 nums[end] >=target && nums[start] <= target.
    public int[] kClosestNumbers(int[] nums, int target, int k) {
        // write your code here
        int[] res = new int[k];
        if (nums == null || nums.length == 0){
            return res;
        }
        
        int start = 0, end = nums.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target){
                end = mid;
            } else {
                start = mid;
            }
        }
        
        int count = 0;
        while (start >= 0 && end < nums.length && count < k){
            if (Math.abs(nums[start] - target) <= Math.abs(nums[end] - target)){
                res[count++] = nums[start--];
            } else{
                res[count++] = nums[end++];
            }
        }
        
        while (start >= 0 && count < k){
            res[count++] = nums[start--];
        }
        while (end < nums.length && count < k){
            res[count++] = nums[end++];
        }
        
        return res;
    }
}
