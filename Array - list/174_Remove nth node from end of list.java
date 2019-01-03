/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param head: The first node of linked list.
     * @param n: An integer
     * @return: The head of linked list.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n < 1){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode preDelete = dummy;
        for (int i = 0; i < n; i++){
            if (head == null){
                return head;
            }
            head = head.next;
        }
        
        while (head != null){
            head = head.next;
            preDelete = preDelete.next;
        }
        
        preDelete.next = preDelete.next.next;
        
        return dummy.next;
    }
}