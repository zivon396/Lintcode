public class Solution {
    /**
     * @param a: An array of integers
     * @return: An integer
     */
    public int firstMissingPositive(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0){
            return 1;
        }
        int n = nums.length;
        boolean[] set = new boolean[n];

        for (Integer num :nums){
            if (num <= 0 || num > n){
                continue;
            }
            set[num - 1] = true;
        }

        for (int i = 0; i < n; i++){
            if (!set[i]){
                return i + 1;
            }
        }

        return n + 1;
    }
}
