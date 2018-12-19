public class Solution {
    /**
     * @param nums: an integer array
     * @param k: An integer
     * @return: the top k largest numbers in array
     */
    private Comparator<Integer> heapComparator = new Comparator<Integer>() {
        public int compare (Integer left, Integer right){
            return left - right;
        }
    };
    
    public int[] topk(int[] nums, int k) {
        // write your code here
        Queue<Integer> heap = new PriorityQueue<Integer>(k, heapComparator);
        
        for (int i = 0; i < nums.length; i++){
            heap.add(nums[i]);
            if (heap.size() > k){
                heap.poll();
            }
        }
        
        int[] res = new int[k];
        while (!heap.isEmpty()){
            res[--k] = heap.poll();
        }
        
        return res;
    }
}