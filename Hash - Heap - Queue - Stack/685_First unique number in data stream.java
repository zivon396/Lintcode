class ListNode {
    public int val;
    public ListNode next;
    public ListNode (int val){
        this.val = val;
        this.next = null;
    }
}

class DataStream {
    private ListNode dummy, tail;
    private Map<Integer, ListNode> hash;
    private Set<Integer> set;
    
    public DataStream (){
        dummy = new ListNode(0);
        tail = dummy;
        hash = new HashMap<>();
        set = new HashSet<>();
    }
    
    public void add(int val){
        if (set.contains(val)){
            return;
        }
        
        if (!hash.containsKey(val)){
            ListNode node = new ListNode(val);
            hash.put(val, tail);
            tail.next = node;
            tail = node;
            return;
        }
        
        remove(val);
    }
    
    public void remove (int val){
        ListNode pre = hash.get(val);
        pre.next = pre.next.next;
        if (pre.next == null){
            tail = pre;
        } else {
            hash.put(pre.next.val, pre);
        }
        
        hash.remove(val);
        set.add(val);
    }
    
    public int findFirstUnique (){
        return dummy.next == null ? -1 : dummy.next.val;
    }
}

public class Solution {
    /**
     * @param nums: a continuous stream of numbers
     * @param number: a number
     * @return: returns the first unique number
     */
    public int firstUniqueNumber(int[] nums, int number) {
        // Write your code here
        DataStream ds = new DataStream();
        for (int i = 0; i < nums.length; i++){
            ds.add(nums[i]);
            if (nums[i] == number){
                return ds.findFirstUnique();
            }
        }
        
        return -1;
    }
}