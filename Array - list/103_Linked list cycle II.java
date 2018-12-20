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
     * @return: The node where the cycle begins. if there is no cycle, return null
     */
    public ListNode detectCycle(ListNode head) {
        // write your code here
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                break;
            }
        }
        
        if (fast == null || fast.next == null){
            return null;
        }
        
        slow = dummy;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
        
    }
}