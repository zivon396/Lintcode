// 因为是循环数组, double 之后再遍历
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length, result[] = new int[n];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>(); // index stack
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n]; 
            while (!stack.isEmpty() && nums[stack.peek()] < num)
                result[stack.pop()] = num;
            if (i < n) stack.push(i);
        }   
        return result;
    }
}
