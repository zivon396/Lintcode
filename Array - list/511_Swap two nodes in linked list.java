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
// 注意分 3 种情况: 1) n1 是 n2 前一个 2) n2 是 n1 前一个 3) n1 和 n2 不挨着
public class Solution {
    /**
     * @param head: a ListNode
     * @param v1: An integer
     * @param v2: An integer
     * @return: a new head of singly-linked list
     */
    public ListNode swapNodes(ListNode head, int v1, int v2) {
        // write your code here
        if (head == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode n1Pre = null, n2Pre = null;
        ListNode curt = dummy;
        
        while (curt.next != null){
            if (curt.next.val == v1){
                n1Pre = curt;
            }
            if (curt.next.val == v2){
                n2Pre = curt;
            }
            curt = curt.next;
        }
        
        if (n1Pre == null || n2Pre == null){
            return dummy.next;
        }
        
        ListNode n1Node = n1Pre.next;
        ListNode n1Post = n1Node.next;
        ListNode n2Node = n2Pre.next;
        ListNode n2Post = n2Node.next;
        
        if (n1Post == n2Node){
            n1Pre.next = n2Node;
            n2Node.next = n1Node;
            n1Node.next = n2Post;
        } else if (n2Post == n1Node){
            n2Pre.next = n1Node;
            n1Node.next = n2Node;
            n2Node.next = n1Post;
        } else {
            n1Pre.next = n2Node;
            n2Node.next = n1Node.next;
            n2Pre.next = n1Node;
            n1Node.next = n2Post;
        }
        
        
        return dummy.next;
    }
}
