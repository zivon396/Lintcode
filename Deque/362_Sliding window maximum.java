public class Solution {
    /**
     * @param nums: A list of integers.
     * @param k: An integer
     * @return: The maximum number inside the window at each moving.
     */
    private int[] a;
    
    void inQueue(Deque<Integer> deque, int pos) {
        while (!deque.isEmpty() && a[deque.peekLast()] <= a[pos]) {
            deque.pollLast();
        }
        deque.offer(pos);
    }
    
    void outQueue(Deque<Integer> deque, int pos) {
        if (deque.peekFirst() == pos) {
            deque.pollFirst();
        }
    }
    
    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        // write your code here
        a = nums;
        
        ArrayList<Integer> ans = new ArrayList<Integer>();
        Deque<Integer> deque = new ArrayDeque<Integer>();
        if (nums.length == 0) {
            return ans;
        }
        for (int i = 0; i < k - 1; i++) {
            inQueue(deque, i);
        }
        
        for(int i = k - 1; i < nums.length; i++) {
            inQueue(deque, i);
            ans.add(a[deque.peekFirst()]);
            outQueue(deque, i - k + 1);
        }
        return ans;
    }
}