// Data Stream (map + set)
// map 用来 1)存储 pre node 2)判断第一次重复. 若重复则加到 set 里, 以后一律跳过
// 不能只用 map, 否则多次重复时会发生错误
class ListNode {
    public char val;
    public ListNode next;
    public ListNode (char val){
        this.val = val;
        this.next = null;
    }
}

class DataStream {
    private Map<Character, ListNode> hash;
    private Set<Character> dupChars;
    private ListNode dummy, tail;
    
    public DataStream (){
        this.hash = new HashMap<>();
        this.dupChars = new HashSet<>();
        this.dummy = new ListNode('.');
        this.tail = dummy;
    }
    
    public void add(char c) {
        if (dupChars.contains(c)){
            return;
        }
        
        if (!hash.containsKey(c)){
            ListNode node = new ListNode(c);
            tail.next = node;
            hash.put(c, tail);
            tail = node;
            return;
        }
        
        ListNode pre = hash.get(c);
        pre.next = pre.next.next;
        if (pre.next != null){
            hash.put(pre.next.val, pre);
        } else {
            tail = pre;
        }
        hash.remove(c); // 也可以不 remove
        dupChars.add(c);
    }
    
    public char getFirstUnique (){
        return dummy.next.val;
    }
}

public class Solution {
    /**
     * @param str: str: the given string
     * @return: char: the first unique character in a given string
     */
    public char firstUniqChar(String str) {
        // Write your code here
        DataStream ds = new DataStream();
        for (int i = 0; i < str.length(); i++){
            ds.add(str.charAt(i));
        }
        char res = ds.getFirstUnique();
        
        return res;
    }
}

// Map
class Solution {
    public int firstUniqChar(String s) {
        if(s == null){
            return -1;
        }
        int[] record = new int[256];
        for(int i = 0; i < s.length(); i++){
            record[s.charAt(i)]++;
        }
        for(int i = 0; i < s.length(); i++){
            if(record[s.charAt(i)] == 1){
                return i;
            }
        }
        return -1;
    }
}
