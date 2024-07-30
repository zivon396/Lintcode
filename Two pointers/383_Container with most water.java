// 左右 pointer 初始在两端, 每次计算 & 更新容量之后, 移动值较小的那个 pointer
// 理由: 当前的容量已经是以较小的 pointer 为端点所有可能性的最大值了
public class Solution {
    /**
     * @param heights: an array of integers
     * @return: an integer
     */
    private int computeArea(int left, int right, int[] heights) {
        return (right - left) * Math.min(heights[left], heights[right]);
    }
    
    public int maxArea(int[] heights) {
        // write your code here
        int ans = 0;
        int left = 0, right = heights.length - 1;
        while (left <= right) {
            ans = Math.max(ans, computeArea(left, right, heights));
            if (heights[left] <= heights[right])
                left++;
            else
                right--;
        }

        return ans;
    }
}
