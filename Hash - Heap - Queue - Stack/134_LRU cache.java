// version 1: HashMap + LinkedList
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
    private int capacity;
    private Map<Integer, ListNode> int2pre;
    private ListNode dummy, tail;
    
    public LRUCache(int capacity) {
        // do intialization if necessary
        this.int2pre = new HashMap<>();
        this.dummy = new ListNode(0, 0);
        this.tail = dummy;
        this.capacity = capacity;
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

        add2tail(curt);
    }

    private void add2tail (ListNode curt){
        int key = curt.key;

        tail.next = curt;
        int2pre.put(key, tail);
        curt.next = null; // 最好有这个
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
        
        if (int2pre.size() < capacity){
            ListNode curt = new ListNode(key, value);
            add2tail(curt);
            return;
        }

        ListNode first = dummy.next;
        int2pre.remove(first.key);

        // 这样直接修改值能节省 ListNode 的开销
        first.key = key;
        first.val = value;
        int2pre.put(key, dummy);

        move2tail(key);
    }
}

// vresion 2: Doubly linked list
public class LRUCache {
    private class Node{
        Node prev;
        Node next;
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> hs = new HashMap<Integer, Node>();
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        tail.prev = head;
        head.next = tail;
    }

    public int get(int key) {
        if(!hs.containsKey(key)) {
            return -1;
        }

        // move current to tail
        move_to_tail(key);

        return hs.get(key).value;
    }

    public void set(int key, int value) {
        // get 这个方法会把key挪到最末端，因此，不需要再调用 move_to_tail
        if (get(key) != -1) {
            hs.get(key).value = value;
            return;
        }
        
        if (hs.size() < capacity) {
            Node insert = new Node(key, value);
            hs.put(key, insert);
            add_to_tail(insert);
            return;
        }

        Node first = head.next;
        hs.remove(first.key);

        first.key = key;
        first.value = value;
        hs.put(key, first);

        move_to_tail(key);
        
// 这种虽然看着代码少, 但是浪费空间 -> 每次挪走 head.next 并没有回收
//         if (hs.size() == capacity) {
//             hs.remove(head.next.key);
//             head.next = head.next.next;
//             head.next.prev = head;
//         }

//         Node insert = new Node(key, value);
//         hs.put(key, insert);
//         add_to_tail(insert);
    }

    private void move_to_tail(int key) {
        Node current = hs.get(key);
        if (current == tail){
            return;
        }

        current.prev.next = current.next;
        current.next.prev = current.prev;

        add_to_tail(current);
    }

    private void add_to_tail (Node current){
        current.prev = tail.prev;
        current.next = tail;
        tail.prev = current;
        current.prev.next = current;
    }
}
