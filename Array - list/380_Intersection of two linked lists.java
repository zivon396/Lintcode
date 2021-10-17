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
// 连一下就变成 103
public class Solution {
    /**
     * @param headA: the first list
     * @param headB: the second list
     * @return: a ListNode
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // write your code here
        if (headA == null || headB == null){
            return null;
        }
        ListNode node = headA;
        while (node.next != null){
            node = node.next;
        }
        node.next = headB;
        ListNode res = listCycleII(headA);
        node.next = null;
        
        return res;
    }
    
    private ListNode listCycleII (ListNode head){
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;
        
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                break;
            }
        }
        
        slow = dummy;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }
}
