/* Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 递归 Traverse version
public class Solution {
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
    TreeNode succ = null;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // write your code here
        dfs(root, p);
        return succ;
    }
    
    private void dfs(TreeNode root, TreeNode p) {
        if (root == null) {
            return;
        }
        
        if (p.val < root.val) {
            succ = root;
            dfs(root.left, p);
        } else {
            dfs(root.right, p);
        }
    }
}

// 迭代 Traverse version
public class Solution {
    /**
     * @param root: the given BST
     * @param p: the given node
     * @return: the in-order successor of the given node in the BST
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // write your code here
        TreeNode succ = null;
        while (root != null) {
            if (p.val < root.val) {
                succ = root;
                root = root.left;
            }
            else {
                root = root.right;
            }
        }
        
        return succ;        
    }
}

// Stack version
// 类似 86
public class Solution {
    /**
     * @param root: the given BST
     * @param p: the given node
     * @return: the in-order predecessor of the given node in the BST
     */
    TreeNode succ = null;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // write your code here
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.right;
        }
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == p) {
                return succ;
            }
            succ = node;
            
            node = node.left;
            while (node != null) {
                stack.push(node);
                node = node.right;
            }
        }

        return null;
    }
}
