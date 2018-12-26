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
     * @param root: The root of binary tree
     * @return: An integer
     */
    public int minDepth(TreeNode root) {
        // write your code here
        if (root == null){
            return 0;
        }
        int mini_dep = helper(root);
        return mini_dep;
    }
    
    private int helper (TreeNode root){
        if (root == null){
            return Integer.MAX_VALUE;
        }
        
        if (root.left == null && root.right == null){
            return 1;
        }
        
        int left = helper(root.left);
        int right = helper(root.right);
        
        
        return Math.min(left, right) + 1;
    }
}