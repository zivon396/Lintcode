public class Solution {
    /**
     * @param nums: A list of integers
     * @return: the median of numbers
     */
    private Queue<Integer> maxHeap, minHeap;
    private int size = 0;
    
    private Comparator<Integer> heapComparator = new Comparator<Integer> (){
        public int compare (Integer left, Integer right){
            return right - left;
        }
    };
    
    public int[] medianII(int[] nums) {
        // write your code here
        int cnt = nums.length;
        maxHeap = new PriorityQueue<Integer>(cnt, heapComparator);
        minHeap = new PriorityQueue<Integer>(cnt);
        
        int[] ans = new int[cnt];
        for (int i = 0; i < cnt; i++){
            addNumber(nums[i]);
            ans[i] = getMedian();
        }
        
        return ans;
    }
    
    private void addNumber (int num){
        maxHeap.add(num);
        if (size % 2 == 0){
            if (minHeap.isEmpty()){
                size++;
                return;
            } else if (maxHeap.peek() > minHeap.peek()){
                Integer maxPeek = maxHeap.poll();
                Integer minPeek = minHeap.poll();
                maxHeap.add(minPeek);
                minHeap.add(maxPeek);
            }
        } else {
            minHeap.add(maxHeap.poll());
        }
        size++;
    }
    
    private int getMedian() {
        return maxHeap.peek();
    }
}