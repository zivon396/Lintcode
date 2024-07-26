/* Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 递归 Traverse version
public class Solution {
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
    TreeNode succ = null;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // write your code here
        dfs(root, p);
        return succ;
    }
    
    private void dfs(TreeNode root, TreeNode p) {
        if (root == null) {
            return;
        }
        
        if (p.val < root.val) {
            succ = root;
            dfs(root.left, p);
        } else {
            dfs(root.right, p);
        }
    }
}

// 迭代 Traverse version
