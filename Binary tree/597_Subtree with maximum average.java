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
    public int sum;
    public int num;
    public ResultType (int sum, int num){
        this.sum = sum;
        this.num = num;
    }
}

public class Solution {
    private TreeNode node;
    private double max_aver = Double.MIN_VALUE;
    /**
     * @param root: the root of binary tree
     * @return: the root of the maximum average of subtree
     */
    public TreeNode findSubtree2(TreeNode root) {
        // write your code here
        ResultType res = helper(root);
        
        return node;
    }
    
    private ResultType helper (TreeNode root){
        if (root == null){
            return new ResultType(0, 0);
        }
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        ResultType average = new ResultType (
            left.sum + right.sum + root.val,
            left.num + right.num + 1
        );
        
        if (node == null || average.sum * 1.0 / (average.num * 1.0) > max_aver){
            max_aver = average.sum * 1.0 / (average.num * 1.0);
            node = root;
        }
        
        return average;
    }
}