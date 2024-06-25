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
// 两种情况: 拐弯/不拐弯的 path
// 拐弯的 path 可由左右子树的 single path 和 root 生成
// 子树的 single path 值为 0 时 -> max path 为 1) 另一条 single path. 2) 另一子树里的拐弯 path.
// 一定记得 singe path 要和 0 比较!!!
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: An integer
     */
    private class ResultType{
        int singlePath, maxPath;
        ResultType (int singlePath, int maxPath){
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }
    
    public int maxPathSum(TreeNode root) {
        ResultType res = helper(root);
        
        return res.maxPath;
    }
    
    private ResultType helper (TreeNode root){
        if (root == null){
            return new ResultType(0, Integer.MIN_VALUE);
        }
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        int singlePath = Math.max(left.singlePath, right.singlePath) + root.val;
        singlePath = Math.max(singlePath, 0);
        
        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);
        
        return new ResultType(singlePath, maxPath);
    }
}
