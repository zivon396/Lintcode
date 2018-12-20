/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        // write your code here
        Map<RandomListNode, RandomListNode> hash = new HashMap<>();
        RandomListNode dummy = new RandomListNode(0);
        dummy.next = head;
        
        while (head != null){
            RandomListNode node = new RandomListNode(head.label);
            hash.put(head, node);
            head = head.next;
        }
        
        head = dummy.next;
        while (head != null){
            hash.get(head).next = hash.get(head.next);
            hash.get(head).random = hash.get(head.random);
            head = head.next;
        }
        
        return hash.get(dummy.next);
    }
}