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
// 先找 nm 和 nn
// 注意 nm 是 第 m 个的 pre (即第 m - 1 个)
// 不必再单独用一个 nPre 来存储第 m 个的 pre 了
public class Solution {
    /**
     * @param head: ListNode head is the head of the linked list 
     * @param m: An integer
     * @param n: An integer
     * @return: The head of the reversed ListNode
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // write your code here
        if (head == null || m > n){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        ListNode nm = head, nn = head;
        for (int i = 0; i < n; i++){
            if (i < m - 1){
                nm = nm.next;
            }
            nn = nn.next;
            if (nn == null){
                return dummy.next;
            }
        }
        
        ListNode n1 = nm.next;
        ListNode nnplus = nn.next;
        
        ListNode pre = null;
        ListNode curt = n1;
        
        while (curt != nnplus){
            ListNode next = curt.next;
            curt.next = pre;
            pre = curt;
            curt = next;
        }
        
        n1.next = nnplus;
        nm.next = nn;
        
        return dummy.next;
    }
}
