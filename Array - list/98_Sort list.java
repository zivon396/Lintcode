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

// version 1: quick sort / partition
// 注意必须要有 midDummy 来存储 = mid.val 的节点 (以及多一个判断), 否则如果 mid.val 是最大值则会 stack overflow
public class Solution {
    /**
     * @param head: The head of linked list.
     * @return: You should return the head of the sorted linked list, using constant space complexity.
     */
    public ListNode sortList(ListNode head) {
        // write your code here
        if (head == null || head.next == null){
            return head;
        }
        
        ListNode mid = findMid(head);
        
        ListNode leftDummy = new ListNode(0), leftTail = leftDummy;
        ListNode rightDummy = new ListNode(0), rightTail = rightDummy;
        ListNode midDummy = new ListNode(0), midTail = midDummy;
        
        while (head != null){
            if (head.val < mid.val){
                leftTail.next = head;
                leftTail = leftTail.next;
            } else if (head.val > mid.val){
                rightTail.next = head;
                rightTail = rightTail.next;
            } else {
                midTail.next = head;
                midTail = midTail.next;
            }
            head = head.next;
        }
        
        leftTail.next = null;
        rightTail.next = null;
        midTail.next = null;
        
        ListNode left = sortList(leftDummy.next);
        ListNode right = sortList(rightDummy.next);
        
        ListNode res = merge(left, midDummy.next, right);
        
        return res;
    }
    
    private ListNode merge (ListNode left, ListNode mid, ListNode right){
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        tail.next = left; tail = getTail(tail);
        tail.next = mid; tail = getTail(tail);
        tail.next = right; tail = getTail(tail);
        
        return dummy.next;
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
    
    private ListNode getTail (ListNode head){
        while (head.next != null){
            head = head.next;
        }
        
        return head;
    }
}

// version 2: merge
// merge sort 的精华就是一上来就直接对 left 和 right 进行 self call
public class Solution {
    /**
     * @param head: The head of linked list.
     * @return: You should return the head of the sorted linked list, using constant space complexity.
     */
    public ListNode sortList(ListNode head) {
        // write your code here
        if (head == null || head.next == null){
            return head;
        }
        ListNode mid = findMid(head);
        
        ListNode right = sortList(mid.next);
        mid.next = null;
        ListNode left = sortList(head);
        
        ListNode res = merge(left, right);
        
        return res;
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
    
    private ListNode merge (ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        
        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                head.next = l1;
                l1 = l1.next;
                head = head.next;
            } else {
                head.next = l2;
                l2 = l2.next;
                head = head.next;
            }
        }
        
        if (l1 != null){
            head.next = l1;
        }
        if (l2 != null){
            head.next = l2;
        }
        
        return dummy.next;
    }
}
