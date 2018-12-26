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
class ResultType {
    public boolean a_exist, b_exist;
    public TreeNode node;
    public ResultType (boolean a_exist, boolean b_exist, TreeNode node){
        this.a_exist = a_exist;
        this.b_exist = b_exist;
        this.node = node;
    }
}

public class Solution {
    /*
     * @param root: The root of the binary tree.
     * @param A: A TreeNode
     * @param B: A TreeNode
     * @return: Return the LCA of the two nodes.
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        if (root == null){
            return root;
        }
        
        ResultType res = helper(root, A, B);
        if (res.a_exist && res.b_exist){
            return res.node;
        }
        return null;
    }
    
    private ResultType helper (TreeNode root, TreeNode A, TreeNode B){
        if (root == null){
            return new ResultType(false, false, null);
        }
        
        ResultType left = helper(root.left, A, B);
        ResultType right = helper(root.right, A, B);
        
        boolean a_exist = left.a_exist || right.a_exist || root == A;
        boolean b_exist = left.b_exist || right.b_exist || root == B;
        
        if (root == A || root == B){
            return new ResultType(a_exist, b_exist, root);
        }
        if (left.node != null && right.node != null){
            return new ResultType(a_exist, b_exist, root);
        }
        if (left.node != null){
            return new ResultType(a_exist, b_exist, left.node);
        }
        if (right.node != null){
            return new ResultType(a_exist, b_exist, right.node);
        }
        
        return new ResultType(a_exist, b_exist, null);
    }
}