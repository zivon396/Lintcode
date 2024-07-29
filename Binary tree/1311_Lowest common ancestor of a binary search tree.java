public class Solution {
    /**
     * @param root: root of the tree
     * @param p: the node p
     * @param q: the node q
     * @return: find the LCA of p and q
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // write your code here
        if (root == null){
            return null;
        }
        if (root == p || root == q){
            return root;
        }

        if (p.val < root.val && q.val < root.val){
            return lowestCommonAncestor(root.left, p, q);
        }
        else if (p.val > root.val && q.val > root.val){
            return lowestCommonAncestor(root.right, q, p);
        }

        return root;
    }
}
