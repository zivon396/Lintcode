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
// Find size -> find kth -> rotate
// 注意 k = k % size
// 也可用 174 的方法找到第 k 个节点
public class Solution {
    /**
     * @param head: the List
     * @param k: rotate to the right k places
     * @return: the list after rotation
     */
    public ListNode rotateRight(ListNode head, int k) {
        // write your code here
        if (head == null || k <= 0){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        int size = 0;
        while (head.next != null){
            size++;
            head = head.next;
        }
        ListNode nlast = head;
        
        k = k % size;
        if (k == 0){
            return dummy.next;
        }
        k = size - k;
        
        head = dummy;
        for (int i = 0; i < k; i++){
            head = head.next;
        }
        
        ListNode newHead = head.next;
        head.next = null;
        nlast.next = dummy.next;
        
        return newHead;
    }
}
