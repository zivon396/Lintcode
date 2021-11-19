public class Solution {

    public static int[] findRepeatAndMissing (int[] nums){
        if (nums == null || nums.length == 0){
            return new int[] {-1, -1};
        }
        int[] res = new int[2];

        int size = nums.length;
        for (int i = 0; i < size; i++) {
            int abs_val = Math.abs(nums[i]);
            if (nums[abs_val - 1] > 0)
                nums[abs_val - 1] = -nums[abs_val - 1];
            else
                res[0] = abs_val;
        }

        for (int i = 0; i < size; i++){
            if (nums[i] > 0){
                res[1] = i + 1;
            }
        }

        return res;
    }
}
