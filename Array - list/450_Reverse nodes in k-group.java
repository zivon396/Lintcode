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
     * @param head: a ListNode
     * @param k: An integer
     * @return: a ListNode
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // write your code here
        if (k <= 1 || head == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        while (head != null){
            head = reverse(head, k);
        }
        
        return dummy.next;
    }
    
    private ListNode reverse (ListNode head, int k){
        ListNode nk = head;
        for (int i = 0; i < k; i++){
            nk = nk.next;
            if (nk == null){
                return nk;
            }
        }
        
        ListNode nkPlus = nk.next;
        ListNode n1 = head.next;
        
        ListNode pre = null;
        ListNode curt = n1;
        
        while (curt != nkPlus){
            ListNode next = curt.next;
            curt.next = pre;
            pre = curt;
            curt = next;
        }
        
        head.next = nk;
        n1.next = nkPlus;
        
        return n1;
    }
}