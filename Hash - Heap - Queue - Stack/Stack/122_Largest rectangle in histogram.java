// 不严格单调递增 stack
// 每当遇到下降的柱子, 说明以在它左边比它高的柱子为左边界最多能到这里 => 计算并记录一下矩形面积
// 注意 edge 处理
public class Solution {
    /**
     * @param height: A list of integer
     * @return: The area of largest rectangle in the histogram
     */
    public int largestRectangleArea(int[] height) {
        // write your code here
        if (height == null || height.length == 0) {
            return 0;
        }
        
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        for (int i = 0; i <= height.length; i++) {
            int curt = (i == height.length) ? -1 : height[i];
            while (!stack.isEmpty() && curt < height[stack.peek()]) { // 这里也可是 curt <= height[stack.peek()]
                int h = height[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1; // i - (-1) - 1 就是 i
                max = Math.max(max, h * w);
            }
            stack.push(i);
        }
        
        return max;
    }
}
