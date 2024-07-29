// Div & Conq
public class Solution {
    /**
     * @param root: the tree
     * @param sum: the sum
     * @return:  if the tree has a root-to-leaf path 
     */
    public boolean pathSum(TreeNode root, int sum) {
        // Write your code here.
        return traverse(root, sum, 0);
    }

    private boolean traverse (TreeNode root, int target, int sum){
        if (root == null){
            return false;
        }
        if ((sum + root.val) == target && root.left == null && root.right == null){
            return true;
        }

        boolean left = traverse(root.left, target, sum + root.val);
        boolean right = traverse(root.right, target, sum + root.val);

        return left || right;
    }
}
