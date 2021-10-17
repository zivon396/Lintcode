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
// fast 走的路程比 slow 走的路程多出来的部分, 与 slow 走的路程重合的部分, 是连结处到相遇处.
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
