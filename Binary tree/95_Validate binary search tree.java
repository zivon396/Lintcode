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
// version 1: traverse
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    private long lastVal = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (lastVal >= root.val) {
            return false;
        }
        lastVal = root.val;
        if (!isValidBST(root.right)) {
            return false;
        }
        return true;
    }
}

//version 2: divConq
class ResultType {
    public boolean isValidBST;
    public long max, min;

    public ResultType (boolean isValidBST, long max, long min){
        this.isValidBST = isValidBST;
        this.max = max;
        this.min = min;
    }
}

public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        // write your code here
        ResultType res = helper(root);

        return res.isValidBST;
    }

    private ResultType helper (TreeNode root){
        if (root == null){
            return new ResultType(true, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        if (!left.isValidBST || !right.isValidBST){
            return new ResultType(false, -1, -1);
        }

        if (left.max >= root.val || right.min <= root.val){
            return new ResultType(false, -1, -1);
        }

        ResultType res = new ResultType (
            true,
            Math.max(Math.max(root.val, left.max), right.max),
            Math.min(Math.min(root.val, left.min), right.min)
        );

        return res;
    }
}
