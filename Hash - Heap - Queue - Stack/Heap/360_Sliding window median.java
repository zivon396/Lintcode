//version 1: 舒服版本
public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The median of the element inside the window at each moving
     */
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return new ArrayList<Integer>();
        }
        int size = 0;
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer> () {
                public int compare(Integer x, Integer y) {
                    return y - x;
                }
            });
        
        int curMedian;
        if (k > 1) {
            for (int i = 0; i < k - 1; i++){
                maxHeap.add(nums[i]);
                if (size % 2 == 0){
                    if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()){
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
        } else {
            curMedian = 0;
        }
        
        for (int i = k - 1; i < nums.length; i++) {
            maxHeap.add(nums[i]);
            if (size % 2 == 0){
                if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()){
                    Integer maxPeek = maxHeap.poll();
                    Integer minPeek = minHeap.poll();
                    maxHeap.add(minPeek);
                    minHeap.add(maxPeek);
                }
            } else {
                minHeap.add(maxHeap.poll());
            }
            size++;
            curMedian = maxHeap.peek();
            res.add(curMedian);
            if (nums[i - k + 1] <= curMedian) {
                maxHeap.remove(nums[i - k + 1]);
                if (maxHeap.size() < minHeap.size()){
                    maxHeap.add(minHeap.poll());
                }
            } else {
                minHeap.remove(nums[i - k + 1]);
                if (minHeap.size() + 1< maxHeap.size()){
                    minHeap.add(maxHeap.poll());
                }
            }
            size--;
        }
        
        return res;
    }
}

//version 2: 答案heap版
public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The median of the element inside the window at each moving
     */
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return new ArrayList<Integer>();
        }
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer> () {
                public int compare(Integer x, Integer y) {
                    return y - x;
                }
            });
        
        int curMedian;
        if (k > 1) {
            maxHeap.add(nums[0]);
            for (int i = 1; i < k - 1; i++) {
                int x = maxHeap.peek();
                if (nums[i] <= x) {
                    maxHeap.add(nums[i]);
                } else {
                    minHeap.add(nums[i]);
                }
            }
            curMedian = maxHeap.peek();
        } else {
            curMedian = 0;
        }
        
        for (int i = k - 1; i < nums.length; i++) {
            if (nums[i] <= curMedian) {
                maxHeap.add(nums[i]);
            } else {
                minHeap.add(nums[i]);
            }
            while (maxHeap.size() > minHeap.size()+1) {
                minHeap.add(maxHeap.poll());
            }
            while (maxHeap.size() < minHeap.size()) {
                maxHeap.add(minHeap.poll());
            }
            curMedian = maxHeap.peek();
            res.add(curMedian);
            if (nums[i - k + 1] <= curMedian) {
                maxHeap.remove(nums[i - k + 1]);
            } else {
                minHeap.remove(nums[i - k + 1]);
            }
        }
        
        return res;
    }
}

//version 3: TreeSet
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