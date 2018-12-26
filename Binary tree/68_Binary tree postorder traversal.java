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
     * @return: Postorder in ArrayList which contains node values.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        // write your code here
        List<Integer> res = new ArrayList<>();
        
        helper(root, res);
        
        return res;
    }
    
    private void helper (TreeNode root, List<Integer> res){
        if (root == null){
            return;
        }
        
        helper(root.left, res);
        helper(root.right, res);
        res.add(root.val);
    }
}

//version 2: stack
public class Solution {
    /**
     * @param root: A Tree
     * @return: Postorder in ArrayList which contains node values.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        // write your code here
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curt = root;
        TreeNode pre = null;
        
        if (root == null){
            return res;
        }
        
        stack.push(root);
        while (!stack.isEmpty()){
            curt = stack.peek();
            if (pre == null || pre.left == curt || pre.right == curt){
                if (curt.left != null){
                    stack.push(curt.left);
                } else if (curt.right != null){
                    stack.push(curt.right);
                }
            } else if (curt.left == pre){
                if (curt.right != null){
                    stack.push(curt.right);
                }
            } else {
                res.add(curt.val);
                stack.pop();
            }
            pre = curt;
        }
        
        return res;
    }
}

// version 3: classType + stack
class ResultType {
    public TreeNode node;
    public int sign;
    public ResultType (TreeNode node, int sign){
        this.node = node;
        this.sign = sign;
    }
}

public class Solution {
    /**
     * @param root: A Tree
     * @return: Postorder in ArrayList which contains node values.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        // write your code here
        List<Integer> res = new ArrayList<>();
        Stack<ResultType> stack = new Stack<>();
        
        if (root == null){
            return res;
        }
        
        stack.push(new ResultType(root, 1));
        
        while (!stack.isEmpty()){
            ResultType curt = stack.pop();
            if (curt.sign == 0){
                res.add(curt.node.val);
            } else {
                stack.push(new ResultType(curt.node, 0));
                if (curt.node.right != null){
                    stack.push(new ResultType(curt.node.right, 1));
                }
                if (curt.node.left != null){
                    stack.push(new ResultType(curt.node.left, 1));
                }
            }
        }
        
        return res;
    }
}