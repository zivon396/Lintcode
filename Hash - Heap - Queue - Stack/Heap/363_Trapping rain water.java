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
                if(leftheight > heights[left]) {
                    res += (leftheight - heights[left]);
                } else {
                    leftheight = heights[left];
                }
            } else {
                right --;
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
