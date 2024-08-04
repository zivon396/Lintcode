public class Solution {
    /**
     * @param l1: the first list
     * @param l2: the second list
     * @return: the sum list of l1 and l2 
     */
    public ListNode addLists(ListNode l1, ListNode l2) {
        // write your code here
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;

        int carry = 0;
        while (l1 != null && l2 != null){
            head.next = new ListNode((l1.val + l2.val + carry) % 10);
            carry = (l1.val + l2.val + carry) / 10;
            l1 = l1.next;
            l2 = l2.next;
            head = head.next;
        }

        while (l1 != null){
            head.next = new ListNode((l1.val + carry) % 10);
            carry = (l1.val + carry) / 10;
            l1 = l1.next;
            head = head.next;
        }
        while (l2 != null){
            head.next = new ListNode((l2.val + carry) % 10);
            carry = (l2.val + carry) / 10;
            l2 = l2.next;
            head = head.next;
        }
        if (carry > 0){
            head.next = new ListNode(carry);
        }

        return dummy.next;
    }
}
