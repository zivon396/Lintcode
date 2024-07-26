// 递归 Traverse version
public class Solution {
    /**
     * @param root: the given BST
     * @param p: the given node
     * @return: the in-order predecessor of the given node in the BST
     */
    TreeNode pre = null;
    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        // write your code here
        dfs(root, p);
        return pre;
    }
    
    private void dfs(TreeNode root, TreeNode p) {
        if (root == null) {
            return;
        }
        
        if (p.val > root.val) {
            pre = root;
            dfs(root.right, p);
        } else {
            dfs(root.left, p);
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
    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        // write your code here
        TreeNode pred = null;
        while (root != null) {
            if (p.val <= root.val) {
                root = root.left;
            }
            else {
                pred = root;
                root = root.right;
            }
        }
        
        return pred;        
    }
}

// Stack version
// 同 86
public class Solution {
    /**
     * @param root: the given BST
     * @param p: the given node
     * @return: the in-order predecessor of the given node in the BST
     */
    TreeNode pre = null;
    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        // write your code here
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == p) {
                return pre;
            }
            pre = node;
            
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        return null;
    }
}
