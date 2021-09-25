/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 * Definition for Doubly-ListNode.
 * public class DoublyListNode {
 *     int val;
 *     DoublyListNode next, prev;
 *     DoublyListNode(int val) {
 *         this.val = val;
 *         this.next = this.prev = null;
 *     }
 * }
 */
// 每次 new 一个 DoublyListNode
class ResultType {
    DoublyListNode first, last;
    
    public ResultType(DoublyListNode first, DoublyListNode last) {
        this.first = first;
        this.last = last;
    }
}

public class Solution {
    /*
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    public DoublyListNode bstToDoublyList(TreeNode root) {
        // write your code here
        if (root == null){
            return null;
        }
        
        ResultType res = helper(root);
        
        return res.first;
    }
    
    private ResultType helper (TreeNode root){
        if (root == null){
            return null;
        }
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        DoublyListNode node = new DoublyListNode(root.val);
        
        ResultType res = new ResultType(null, null);
        
        if (left == null){
            res.first = node;
        } else {
            res.first = left.first;
            left.last.next = node;
            node.prev = left.last;
        }
        
        if (right == null) {
            res.last = node;
        } else {
            res.last = right.last;
            right.first.prev = node;
            node.next = right.first;
        }
        
        return res;
    }
}
