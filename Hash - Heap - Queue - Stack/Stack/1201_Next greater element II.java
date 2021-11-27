// 因为是循环数组, double 之后再遍历
// 也可用 1206 的无 while 版本
public class Solution {
    /**
     * @param nums: an array
     * @return: the Next Greater Number for every element
     */
    public int[] nextGreaterElements(int[] nums) {
        // Write your code here
        if (nums == null || nums.length == 0){
            return new int[] {};
        }
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        
        Stack<Integer> stack = new Stack<>(); // Index stack
        
        for (int i = 0; i < n * 2; i++){
            int num = nums[i % n];
            while (!stack.isEmpty() && num > nums[stack.peek()]){
                res[stack.pop()] = num;
            }
            
            if (i < n) stack.push(i);
        }
        
        return res;
    }
}
