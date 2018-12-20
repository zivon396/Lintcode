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
public class Solution {
    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    private Comparator<ListNode> ListNodeCom = new Comparator<ListNode> (){
        public int compare (ListNode left, ListNode right){
            return left.val - right.val;
        }
    };
    
    public ListNode mergeKLists(List<ListNode> lists) {  
        // write your code here
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        if (lists == null || lists.size() == 0){
            return null;
        }
        
        Queue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), ListNodeCom);
        for (int i = 0; i < lists.size(); i++){
            if (lists.get(i) != null){
                heap.add(lists.get(i));
            }
        }
        
        while (!heap.isEmpty()){
            ListNode curt = heap.poll();
            tail.next = curt;
            tail = curt;
            if (curt.next != null){
                heap.add(curt.next);
            }
        }
        
        return dummy.next;
    }
}
