/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */
// Create 一个 dummy, 最后直接返回 dummy.next
// Heap 的 size 只需要是 k
public class Solution {
    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    private Comparator<ListNode> lnCom = new Comparator<ListNode> (){
        public int compare (ListNode left, ListNode right){
            return left.val - right.val;
        }
    };
    
    public ListNode mergeKLists(List<ListNode> lists) {  
        // write your code here
        if (lists == null || lists.size() == 0){
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        
        Queue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), lnCom);
        for (int i = 0; i < lists.size(); i++){
            if (lists.get(i) != null){
                heap.add(lists.get(i));
            }
        }
        
        while (!heap.isEmpty()){
            ListNode node = heap.poll();
            head.next = node;
            head = node;
            if (node.next != null){
                heap.add(node.next);
            }
        }
        
        return dummy.next;
    }
}
