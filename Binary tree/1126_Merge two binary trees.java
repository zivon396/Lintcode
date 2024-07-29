// 类似先序遍历
// 对照 1360
public class Solution {
    /**
     * @param t1: the root of the first tree
     * @param t2: the root of the second tree
     * @return: the new binary tree after merge
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        // Write your code here
        if (t1 == null) return t2;
        if (t2 == null) return t1;

        TreeNode t3 = new TreeNode(t1.val + t2.val);

        t3.left = mergeTrees(t1.left, t2.left);
        t3.right = mergeTrees(t1.right, t2.right);
        
        return t3;
    }
}
