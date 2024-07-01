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
// 每次翻转 k 个
// 每次从下一组 k 个 node 的 pre 开始翻转 (每次返回这个 pre)
// 翻转时, 先找到 nk, 然后保存一头一尾 (n1 和 nkPlus)
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

// 原创版本
public class Solution {
    /**
     * @param head: a ListNode
     * @param k: An integer
     * @return: a ListNode
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // write your code here
        if (k < 2){
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        int count = 0;
        ListNode nPre, nNext;
        while (head != null){
            nPre = head;
            head = head.next;
            count++;
            while (count % k != 0 && head != null){
                head = head.next;
                count++;
            }

            if (head == null){
                break;
            }

            nNext = head.next;
            head.next = null;
            head = reverse(nPre, nNext);
        }

        return dummy.next;
    }

    private ListNode reverse (ListNode nPre, ListNode nNext){
        ListNode head = nPre.next;
        nPre.next = null;
        ListNode pre = null;

        while (head != null){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        nPre.next = pre;
        head = pre;
        while (head.next != null){
            head = head.next;
        }

        head.next = nNext;

        return head;
    }
}
