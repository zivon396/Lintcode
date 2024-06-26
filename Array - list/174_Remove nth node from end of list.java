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
// 先让 head 向后移动 n 个节点, 此时 head 距离末尾节点 (size - n - 1) 个节点, 再让 preDelete 和 head 同时向后移动, 即可找到倒数第 n 个的前一个节点
// 也可用 170 的方法找节点
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

// version 2:
public class Solution {
    /**
     * @param head: The first node of linked list.
     * @param n: An integer
     * @return: The head of linked list.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // write your code here
        if (head == null || n < 0){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        ListNode slow = dummy, fast = dummy;
        for (int i = 0; i < n; i++){
            fast = fast.next;
        }

        while (fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }

        ListNode n1 = slow.next;
        slow.next = n1.next;
        n1.next = null;

        return dummy.next;
    }
}
