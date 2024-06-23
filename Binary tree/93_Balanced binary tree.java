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
    public boolean isBalanced;
    public int maxHeight;
    public ResultType (boolean isBalanced, int maxHeight){
        this.isBalanced = isBalanced;
        this.maxHeight = maxHeight;
    }
}
// 注意是每个 node 的 subtree 深度差都不能超过 1
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public boolean isBalanced(TreeNode root) {
        // write your code here
        ResultType res = helper(root);
        
        return res.isBalanced;
    }
    
    private ResultType helper (TreeNode root){
        if (root == null){
            return new ResultType(true, 0);
        }
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        if (!left.isBalanced || !right.isBalanced){
            return new ResultType(false, -1);
        }
        
        if (Math.abs(left.maxHeight - right.maxHeight) > 1){
            return new ResultType(false, -1);
        }
        
        return new ResultType(true, Math.max(left.maxHeight, right.maxHeight) + 1);
    }
}

// 原创
class ResultType {
    public int depth;
    public boolean isBalanced;
    public ResultType(int depth, boolean isBalanced) {
        this.depth = depth;
        this.isBalanced = isBalanced;
    }
}

public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public boolean isBalanced(TreeNode root) {
        // write your code here
        if (root == null){
            return true;
        }

        ResultType res = helper(root);

        return res.isBalanced;
    }

    private ResultType helper (TreeNode root){
        if (root == null){
            return new ResultType(0, true);
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        ResultType res = new ResultType(
            Math.max(left.depth, right.depth) + 1,
            !(!left.isBalanced || !right.isBalanced || Math.abs(left.depth - right.depth) > 1)
        );

        return res;
    }
}
