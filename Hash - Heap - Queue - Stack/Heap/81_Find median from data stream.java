public class Solution {
    PriorityQueue<Integer> minHeap, maxHeap;
    int median;
    boolean isFirstAdd;
    public Solution() {
        // 小顶堆放大于中位数的数
        // 大顶堆放小于中位数的数
        this.minHeap = new PriorityQueue<>();
        this.maxHeap = 
            new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        this.isFirstAdd = true;
    }

    public void add(int val) {
        if (this.isFirstAdd) {
            // 第一个进入数据流的数字是第一个中位数
            this.median = val;
            this.isFirstAdd = false;
            return ;
        }

        if (val < this.median) {
            // 小的数放到大顶堆
            this.maxHeap.add(val);
        }
        else {
            // 大的数放到小顶堆
            this.minHeap.add(val);
        }

        // 比较堆中数字数量，调整堆和中位数
        if (this.maxHeap.size() > this.minHeap.size()) {
            this.minHeap.add(this.median);
            this.median = this.maxHeap.poll();
        }
        if (this.maxHeap.size() < this.minHeap.size() - 1) {
            this.maxHeap.add(this.median);
            this.median = this.minHeap.poll();
        }
    }

    public int getMedian() {
        // 返回目前维护的中位数
        return this.median;
    }
}



// 有待细看这是啥
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: the median of numbers
     */
    private Comparator<Integer> heapCom = new Comparator<Integer>(){
        public int compare (Integer x, Integer y){
            return y - x;
        }
    };
    private int size = 0;
    private Queue<Integer> minHeap, maxHeap;
    
    public int[] medianII(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0){
            return new int[] {};
        }
        int len = nums.length;
        int[] res = new int[len];
        
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(heapCom);
        
        for (int i = 0; i < len; i++){
            addNum(nums[i]);
            res[i] = maxHeap.peek();
        }
        
        return res;
    }
    
    private void addNum (int num){
        if (size % 2 == 0){
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }
        if (minHeap.size() > 0 && maxHeap.peek() > minHeap.peek()){
            Integer maxPeek = maxHeap.poll();
            Integer minPeek = minHeap.poll();
            maxHeap.add(minPeek);
            minHeap.add(maxPeek);
        }
        size++;
    }
}
