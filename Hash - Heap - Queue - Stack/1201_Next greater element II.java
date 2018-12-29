public class Solution {
    /**
     * @param nums: an array
     * @return: the Next Greater Number for every element
     */
    public int[] nextGreaterElements(int[] nums) {
        // Write your code here
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n * 2; i++){
            if (stack.isEmpty() || nums[i % n] <= nums[stack.peek()]){
                if (i < n){
                    stack.push(i);
                }
            } else {
                res[stack.pop()] = nums[i % n];
                i--;
            }
        }
        
        return res;
    }
}