//version 1: Heap
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


//version 2: TreeSet
import java.util.*;

public class Solution {
    /**
     * @param nums
     *            : A list of integers.
     * @return: The median of the element inside the window at each moving.
     */
    public  ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        TreeSet<Node> minheap = new TreeSet<Node>();
        TreeSet<Node> maxheap = new TreeSet<Node>();
        ArrayList<Integer> result = new ArrayList<Integer> ();
        
        if (k == 0)
            return result;
        
        int half = (k+1)/2;
        for(int i=0; i<k-1; i++) {
            add(minheap, maxheap, half, new Node(i, nums[i]));
        }
        for(int i=k-1; i<n; i++) {
            add(minheap, maxheap, half, new Node(i, nums[i]));
            result.add(maxheap.last().val);
            remove(minheap, maxheap, new Node(i-k+1, nums[i-k+1]));
        }
        return result;
    }
    
    void add(TreeSet<Node>minheap, TreeSet<Node> maxheap, int size, Node node) {
        if (maxheap.size() < size) {
            maxheap.add(node);
        }
        else {
            minheap.add(node);
        }
        if (maxheap.size() == size) {
            if (minheap.size() > 0 && minheap.first().val < maxheap.last().val) {
                Node s = minheap.first();
                Node b = maxheap.last();
                minheap.remove(s);
                maxheap.remove(b);
                minheap.add(b);
                maxheap.add(s);
            }
        }
    }
    
    void remove(TreeSet<Node>minheap, TreeSet<Node> maxheap, Node node) {
        if (maxheap.contains(node)) {
            maxheap.remove(node);
        }
        else {
            minheap.remove(node);
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
        Node a =(Node)other;
        if (this.val == a.val) {
            return this.id - a.id;
        }
        return this.val - a.val;
    }
}
