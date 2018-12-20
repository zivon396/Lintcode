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
     * @param head: The head of linked list.
     * @return: nothing
     */
    public void reorderList(ListNode head) {
        // write your code here
        if (head == null){
            return;
        }
        ListNode mid = findMid(head);
        ListNode newHead = reverse(mid.next);
        mid.next = null;
        
        merge(head, newHead);
    }
    
    private void merge (ListNode l1, ListNode l2){
        int sign = 0;
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        
        while (l1 != null && l2 != null){
            if (sign % 2 == 0){
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
            sign += 1;
        }
        
        if (l1 != null){
            head.next = l1;
        }
        if (l2 != null){
            head.next = l2;
        }
    }
    
    private ListNode reverse (ListNode head){
        ListNode pre = null;
        
        while (head != null){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        
        return pre;
    }
    
    private ListNode findMid (ListNode head){
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;
        
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
}