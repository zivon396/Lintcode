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
// version 1: traverse
public class Solution {
    /**
     * @param root: A Tree
     * @return: Inorder in ArrayList which contains node values.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        // write your code here
        List<Integer> res = new ArrayList<Integer>();
        
        helper(root, res);
        
        return res;
    }
    
    private void helper(TreeNode root, List<Integer> res){
        if (root == null){
            return;
        }
        
        helper(root.left, res);
        res.add(root.val);
        helper(root.right, res);
    }
}

// version 2: stack
// 每次都先一直深入到最左边
public class Solution {
    /**
     * @param root: A Tree
     * @return: Inorder in ArrayList which contains node values.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        // write your code here
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<>();
        
        TreeNode curt = root;
        
        while (curt != null || !stack.isEmpty()){
            while (curt != null){
                stack.push(curt);
                curt = curt.left;
            }
            
            curt = stack.pop();
            res.add(curt.val);
            curt = curt.right;
        }
        
        return res;
    }
}
