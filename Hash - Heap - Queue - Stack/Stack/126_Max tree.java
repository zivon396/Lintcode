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
 */

public class Solution {
    /**
     * @param A: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */
    public TreeNode maxTree(int[] A) {
        // write your code here
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = null;
        
        for (int i = 0; i <= A.length; i++){
            TreeNode node = i == A.length ? new TreeNode(Integer.MAX_VALUE) : new TreeNode(A[i]);
            while (!stack.isEmpty() && node.val > stack.peek().val){
                TreeNode son = stack.pop();
                if (stack.isEmpty()){
                    node.left = son;
                    break;
                }
                TreeNode left = stack.peek();
                if (left.val < node.val){
                    left.right = son;
                } else {
                    node.left = son;
                }
            }
            stack.push(node);
        }
        
        return stack.peek().left;
    }
}