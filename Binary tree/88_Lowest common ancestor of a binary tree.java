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
// 分几种情况: 1) A 和 B 分别在两侧 -> root 一定是 LCA. 2) A 和 B 在同一侧 -> LCA在那一侧, 且 LCA 会被一直返回到最高节点
public class Solution {
    /*
     * @param root: The root of the binary search tree.
     * @param A: A TreeNode in a Binary.
     * @param B: A TreeNode in a Binary.
     * @return: Return the least common ancestor(LCA) of the two nodes.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        TreeNode res = helper(root, A, B);
        
        return res;
    }
    
    private TreeNode helper (TreeNode root, TreeNode A, TreeNode B){
        if (root == null){
            return null;
        }
        if (root == A || root == B){
            return root;
        }
        
        TreeNode left = helper(root.left, A, B);
        TreeNode right = helper(root.right, A, B);
        
        if (left != null && right != null){
            return root;
        }
        
        if (left != null){
            return left;
        }
        if (right != null){
            return right;
        }
        
        return null;
    }
}
