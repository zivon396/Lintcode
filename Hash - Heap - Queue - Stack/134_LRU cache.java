class ListNode {
    public int key, val;
    public ListNode next;
    public ListNode (int key, int val){
        this.key = key;
        this.val = val;
        this.next = null;
    }
}

public class LRUCache {
    /*
    * @param capacity: An integer
    */
    private int capacity, size;
    private Map<Integer, ListNode> int2pre;
    private ListNode dummy, tail;
    
    public LRUCache(int capacity) {
        // do intialization if necessary
        this.int2pre = new HashMap<>();
        this.dummy = new ListNode(0, 0);
        this.tail = dummy;
        this.capacity = capacity;
        this.size = 0;
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    
    public void move2tail (int key){
        ListNode pre = int2pre.get(key);
        ListNode curt = pre.next;
        if (curt == tail){
            return;
        }
        
        pre.next = pre.next.next;
        int2pre.put(pre.next.key, pre);
        
        tail.next = curt;
        int2pre.put(key, tail);
        tail = curt;
    }
    
    public int get(int key) {
        // write your code here
        if (!int2pre.containsKey(key)){
            return -1;
        }
        move2tail(key);
        
        return tail.val;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        // write your code here
        if (get(key) != -1){
            ListNode pre = int2pre.get(key);
            pre.next.val = value;
            return;
        }
        
        if (size < capacity){
            ListNode curt = new ListNode(key, value);
            tail.next = curt;
            int2pre.put(key, tail);
            tail = curt;
            size++;
            return;
        }
        
        ListNode first = dummy.next;
        int2pre.remove(first.key);
        first.key = key;
        first.val = value;
        
        int2pre.put(key, dummy);
        move2tail(key);
    }
}
