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
     * @param head: The first node of linked list
     * @param x: An integer
     * @return: A ListNode
     */
    public ListNode partition(ListNode head, int x) {
        // write your code here
        if (head == null){
            return head;
        }
        
        ListNode dummyL = new ListNode(0), dummyH = new ListNode(0);
        ListNode headL = dummyL, headH = dummyH;
        
        while (head != null){
            if (head.val < x){
                headL.next = head;
                headL = head;
            } else {
                headH.next = head;
                headH = head;
            }
            head = head.next;
        }
        
        headL.next = dummyH.next;
        headH.next = null;
        
        return dummyL.next;
    }
}