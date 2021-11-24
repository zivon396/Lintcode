// version 0: Two pointers
public class Solution {
    /**
     * @param heights: an array of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        // write your code here
        int left = 0, right = heights.length - 1; 
        int res = 0;
        if(left >= right)
            return res;
        int leftheight = heights[left];
        int rightheight = heights[right];
        while(left < right) {
            if(leftheight < rightheight) {
                left ++;
                // 每次下降才存水
                if(leftheight > heights[left]) {
                    res += (leftheight - heights[left]);
                } else {
                    leftheight = heights[left];
                }
            } else {
                right --;
                // 每次下降才存水
                if(rightheight > heights[right]) {
                    res += (rightheight - heights[right]);
                } else {
                    rightheight = heights[right];
                }
            }
        }
        return res;
    }
}

// version 2: 单调栈
// 栈中元素永远是不严格单调递减的
// 若遇到升高的柱子, 则说明前面可能会存水, 依次 poll 出栈顶元素并记录水量
// 每次计算的是最新升高的柱子和 stack 倒数第二个柱子之间 (inclusive) 的存水量 -> 倒数第一个柱子的高度为底 + 两边的较小高度为顶.
// 每次 poll 出一个柱子, 相当于用 min(最新高度, 此时的 peek) 填满 poll 出位置的缺口.
public class Solution {
    /**
     * @param heights: a list of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        // write your code here
        int water = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        
        for (int i = 0; i < heights.length; i++) {
            while (!queue.isEmpty() && heights[i] > heights[queue.peekLast()]) {
                int j = queue.pollLast();
                // 前面必须有堵足够高的墙挡着才行
                if (!queue.isEmpty()) {
                    int w = i - queue.peekLast() - 1;
                    water += w * (Math.min(heights[i], heights[queue.peekLast()]) - heights[j]);
                }
            }
            queue.offer(i);
        }
        return water;
    }
}
