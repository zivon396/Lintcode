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
// version 1: traverse + divConq
public class Solution {
    
    private int min = Integer.MAX_VALUE;
    private TreeNode node = null;
    /**
     * @param root: the root of binary tree
     * @return: the root of the minimum subtree
     */
    public TreeNode findSubtree(TreeNode root) {
        // write your code here
        helper(root);
        
        return node;
    }
    
    private int helper(TreeNode root){
        if (root == null){
            return 0;
        }
        
        int left = helper(root.left);
        int right = helper(root.right);
        int sum = root.val + left + right;
        
        if (sum < min){
            min = sum;
            node = root;
        }
        
        return sum;
    }
}

// version 2: pure divConq
// 必须额外存储每棵子树的 sum!!!
class ResultType {
    public TreeNode minSubtree;
    public int mini_sum, sum;
    public ResultType (TreeNode minSubtree, int mini_sum, int sum){
        this.minSubtree = minSubtree;
        this.mini_sum = mini_sum;
        this.sum = sum;
    }
}

public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the root of the minimum subtree
     */
    public TreeNode findSubtree(TreeNode root) {
        // write your code here
        if (root == null){
            return root;
        }
        
        ResultType res = helper(root);
        
        return res.minSubtree;
    }
    
    private ResultType helper (TreeNode root){
        if (root == null){
            return new ResultType(null, Integer.MAX_VALUE, 0);
        }
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        ResultType res = new ResultType(
            root,
            left.sum + right.sum + root.val,
            left.sum + right.sum + root.val
        );
        
        if (left.mini_sum < res.mini_sum){
            res.mini_sum = left.mini_sum;
            res.minSubtree = left.minSubtree;
        }
        if (right.mini_sum < res.mini_sum){
            res.mini_sum = right.mini_sum;
            res.minSubtree = right.minSubtree;
        }
        
        return res;
    }
}
