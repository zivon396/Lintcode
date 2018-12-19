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
    private ListNode dummy, tail;
    private int capacity, size;
    private Map<Integer, ListNode> keyToPre;
    
    public LRUCache(int capacity) {
        // do intialization if necessary
        dummy = new ListNode(0, 0);
        tail = dummy;
        this.capacity = capacity;
        size = 0;
        keyToPre = new HashMap<>();
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    
    private void moveToTail (int key){
        ListNode pre = keyToPre.get(key);
        ListNode curt = pre.next;
        
        if (curt == tail){
            return;
        }
        
        pre.next = pre.next.next;
        tail.next = curt;
        // if (pre.next != null){
            keyToPre.put(pre.next.key, pre);
        // }
        keyToPre.put(curt.key, tail);
        tail = curt;
    }
    
    public int get(int key) {
        // write your code here
        if (!keyToPre.containsKey(key)){
            return -1;
        }
        
        moveToTail(key);
        
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
            ListNode pre = keyToPre.get(key);
            pre.next.val = value;
            return;
        }
        
        if (size < capacity){
            ListNode node = new ListNode(key, value);
            tail.next = node;
            keyToPre.put(key, tail);
            tail = node;
            size++;
            
            return;
        }
        
        ListNode first = dummy.next;
        keyToPre.remove(first.key);
        
        first.key = key;
        first.val = value;
        keyToPre.put(key, dummy);
        moveToTail(key);
    }
}