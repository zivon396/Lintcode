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
