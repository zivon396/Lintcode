public class Solution {
    /**
     * @param nums: an integer unsorted array
     * @param k: an integer from 1 to n
     * @return: the kth largest element
     */
    public int kthLargestElement2(int[] nums, int k) {
        // write your code here
        Queue<Integer> heap = new PriorityQueue<Integer>(k);
        
        for (int i = 0; i < nums.length; i++){
            heap.add(nums[i]);
            if (heap.size() > k){
                heap.poll();
            }
        }
        
        return heap.peek();
    }
}