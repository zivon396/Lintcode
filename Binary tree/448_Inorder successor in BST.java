/* Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// version 1: stack
public class Solution {
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Stack<TreeNode> stack = new Stack<>();
        
        TreeNode curt = root;
        int sign = 0;
        
        while (curt != null || !stack.isEmpty()){
            while (curt != null){
                stack.push(curt);
                curt = curt.left;
            }
            
            curt = stack.pop();
            if (sign == 1){
                return curt;
            }
            if (curt == p){
                sign = 1;
            }
            curt = curt.right;
        }
        
        return null;
    }
}

// version 2: while iteration
public class Solution {
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while (root != null && root.val != p.val) {
            if (root.val > p.val) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        
        if (root == null) {
            return null;
        }
        
        if (root.right == null) {
            return successor;
        }
        
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        
        return root;
    }
}

// version 3: recursion
public class Solution {
    private TreeNode suc = null;
    private int sign = 0;
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // write your code here
        helper(root, p);
        
        return suc;
    }
    
    private void helper (TreeNode root, TreeNode p){
        if (root == null || p == null){
            return;
        }
        
        helper(root.left, p);
        if (sign == 1){
            suc = root;
            sign = 0;
        }
        if (root == p){
            sign = 1;
        }
        helper(root.right, p);
    }
}

// version 4: 超简单
public class Solution {
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode predecessor = null;
        
        while (root != null){
            if (root.val <= p.val) {
                root = root.right;
            } else {
                predecessor = root;
                root = root.left;
            }
        }
        
        return predecessor;
    }
}