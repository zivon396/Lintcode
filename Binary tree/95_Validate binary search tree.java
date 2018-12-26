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
    public boolean isValid;
    public long max, min;
    public ResultType (boolean isValid, long max, long min){
        this.isValid = isValid;
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
        ResultType res = divConq(root);
        return res.isValid;
    }
    
    private ResultType divConq (TreeNode root){
        if (root == null){
            return new ResultType(true, Long.MIN_VALUE, Long.MAX_VALUE);
        }
        
        ResultType left = divConq(root.left);
        ResultType right = divConq(root.right);
        
        if (!left.isValid || !right.isValid){
            return new ResultType(false, -1, -1);
        }
        
        if (root.val <= left.max || root.val >= right.min){
            return new ResultType(false, -1, -1);
        }
        
        long max = Math.max(left.max, right.max);
        max = Math.max(root.val, max);
        
        long min = Math.min(left.min, right.min);
        min = Math.min(root.val, min);
        
        return new ResultType(true, max, min);
    }
}