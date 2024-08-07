// version 1: Heap
// 不同于 81, 不用额外保存一个 median, 因为涉及到删除可能会麻烦. 直接每次输出 maxHeap 的 peek.
// !!!永远满足: 1) maxHeap 里的所有元素都小于 minHeap 里的所有元素 2) 每次加完一定有 maxHeap.size() = half
// add 的时间复杂度为 log(k) (即维护两个 size 为 k / 2 的 heap)
// 注意传统 heap 无法保证 remove 的时间复杂度为 log(k)
public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The median of the element inside the window at each moving
     */
    private int half;
    private Queue<Integer> minHeap;
    private Queue<Integer> maxHeap;
    public Solution (){
        this.minHeap = new PriorityQueue<>();
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        // write your code here
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return res;
        }

        half = (k + 1) / 2;
        for (int i = 0; i < k - 1; i++){
            addNum(nums[i]);
        }

        for (int i = k - 1; i < nums.length; i++){
            addNum(nums[i]);
            res.add(maxHeap.peek());
            removeNum(nums[i - k + 1]);
        }

        return res;
    }

    private void addNum (int num){
        if (maxHeap.size() < half){
            maxHeap.add(num);
        }
        else {
            minHeap.add(num);
        }

        if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()){
            int tmp = maxHeap.poll();
            maxHeap.add(minHeap.poll());
            minHeap.add(tmp);
        }
    }

    private void removeNum (int num){
        if (maxHeap.contains(num)){
            maxHeap.remove(num);
        }
        else {
            minHeap.remove(num);
        }
    }
}


// 下面两种答案现在都在 95% 的时候 fail 了

// Heap 九章答案版本
// Fail 的原因是 heapCom. 如果用 Collections.reverseOrder() 就可以通过
public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The median of the element inside the window at each moving
     */
    private Comparator<Integer> heapCom = new Comparator<Integer>() {
        public int compare (Integer x, Integer y){
            return y - x;
        }
    };
    private int half;
    private Queue<Integer> maxHeap, minHeap;
    
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        // write your code here
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return res;
        }
        int len = nums.length;
        half = (k + 1) / 2;
        
        maxHeap = new PriorityQueue<>(heapCom);
        minHeap = new PriorityQueue<>();
        
        for (int i = 0; i < k - 1; i++){
            addNum(nums[i]);
        }
        
        for (int i = k - 1; i < len; i++){
            addNum(nums[i]);
            res.add(maxHeap.peek());
            remove(nums[i - k + 1]);
        }
        
        return res;
    }
    
    private void addNum (int num){
        if (maxHeap.size() < half){
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }
        if (minHeap.size() > 0 && maxHeap.peek() > minHeap.peek()) {
            Integer maxPeek = maxHeap.poll();
            Integer minPeek = minHeap.poll();
            maxHeap.add(minPeek);
            minHeap.add(maxPeek);
        }
    }
    
    private void remove (int num){
        if (num <= maxHeap.peek()){
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
    }
}


// version 2: TreeSet
// TreeSet 可保证 add / remove 的时间复杂度均为 log(k)
import java.util.*;

public class Solution {
    /**
     * @param nums
     *            : A list of integers.
     * @return: The median of the element inside the window at each moving.
     */
    private int half;
    private TreeSet<Node> minHeap;
    private TreeSet<Node> maxHeap;
    public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        minHeap = new TreeSet<Node>();
        maxHeap = new TreeSet<Node>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        if (k == 0)
            return result;
        
        half = (k + 1) / 2;
        for(int i = 0; i < k - 1; i++) {
            add(new Node(i, nums[i]));
        }
        for(int i = k - 1; i < n; i++) {
            add(new Node(i, nums[i]));
            result.add(maxHeap.last().val);
            remove(new Node(i - k + 1, nums[i - k + 1]));
        }
        
        return result;
    }
    
    void add(Node node) {
        // add 的过程可以保证 median 一定是 maxHeap 的 peek
        if (maxHeap.size() < half) {
            maxHeap.add(node);
        }
        else {
            minHeap.add(node);
        }
        if (maxHeap.size() == half) { // 这个 if 其实可以省去
            if (minHeap.size() > 0 && minHeap.first().val < maxHeap.last().val) {
                Node s = minHeap.first();
                Node b = maxHeap.last();
                minHeap.remove(s);
                maxHeap.remove(b);
                minHeap.add(b);
                maxHeap.add(s);
            }
        }
    }
    
    void remove(Node node) {
        if (maxHeap.contains(node)) {
            maxHeap.remove(node);
        }
        else {
            minHeap.remove(node);
        }
    }
}

class Node implements Comparable<Node>{
    int id;
    int val;
    Node(int id, int val) {
        this.id = id;
        this.val = val;
    }
    public int compareTo(Node other) {
        Node a = (Node)other;
        if (this.val == a.val) {
            return this.id - a.id;
        }
        return this.val - a.val;
    }
}
