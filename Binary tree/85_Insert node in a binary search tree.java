// 迭代
public class Solution {
    /*
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        // write your code here
        if (root == null){
            return node;
        }
        TreeNode pre = null;
        TreeNode curt = root;
        while (curt != null){
            pre = curt;
            if (node.val < r.val){
                curt = curt.left;
            }
            else if (node.val > curt.val){
                curt = curt.right;
            }
        }

        if (node.val < pre.val){
            pre.left = node;
        }
        else if (node.val > pre.val){
            pre.right = node;
        }

        return root;
    }
}

// 递归
public class Solution {
    /*
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        if (root == null) {
            return node;
        }
        
        if (root.val > node.val) {
            root.left = insertNode(root.left, node);
        } else {
            root.right = insertNode(root.right, node);
        }

        return root;
    }
}
