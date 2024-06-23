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
// version 1: divConq
// helper 返回的值是已构建好的 linked list 的最右边的 node
public class Solution {
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
        // write your code here
        helper(root);
    }
    
    private TreeNode helper(TreeNode root) {
        if (root == null){
            return root;
        }
        
        TreeNode left = helper(root.left);
        TreeNode right = helper(root.right);
        
        if (left != null){
            left.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        
        if (right != null){
            return right;
        }
        if (left != null){
            return left;
        }
        
        return root;
    }
    // 下面的也可以 (原创)
    // private TreeNode helper(TreeNode root) {
    //     if (root == null){
    //         return root;
    //     }

    //     TreeNode left = helper(root.left);
    //     TreeNode right = helper(root.right);
    //     TreeNode last = root;

    //     if (left != null){
    //         left.right = root.right;
    //         root.right = root.left;
    //         root.left = null;
    //         last = left;
    //     }

    //     if (right != null){
    //         last = right;
    //     }

    //     return last;
    // }
}

// version 2: traverse
// 正如同 preorder traverse 一样:
// 对于 right, lastNode 是 left 的最右边的 node
// 对于 left, lastNode 是 root
public class Solution {
    private TreeNode lastNode = null;
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
        // write your code here
        if (root == null) {
            return;
        }

        if (lastNode != null) {
            lastNode.left = null;
            lastNode.right = root;
        }

        lastNode = root;
        TreeNode right = root.right;
        flatten(root.left);
        flatten(right);
    }
}
//version 3: non-recursion
public class Solution {
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
        // write your code here
        if (root == null) {
            return;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            
            // connect 
            node.left = null;
            if (stack.empty()) {
                node.right = null;
            } else {
                node.right = stack.peek();
            }
        }
    }
}
