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
// traverse + divConq
// 等于把 node 和 maxAvg 放到全局去了
class ResultType {
    public int sum;
    public int num;
    public ResultType (int sum, int num){
        this.sum = sum;
        this.num = num;
    }
}

public class Solution {
    private TreeNode node;
    private double max_aver = Double.MIN_VALUE;
    /**
     * @param root: the root of binary tree
     * @return: the root of the maximum average of subtree
     */
    public TreeNode findSubtree2(TreeNode root) {
        // write your code here
        ResultType res = helper(root);
        
        return node;
    }
    
    private ResultType helper (TreeNode root){
        if (root == null){
            return new ResultType(0, 0);
        }
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        ResultType average = new ResultType (
            left.sum + right.sum + root.val,
            left.num + right.num + 1
        );
        
        if (node == null || average.sum * 1.0 / (average.num * 1.0) > max_aver){
            max_aver = average.sum * 1.0 / (average.num * 1.0);
            node = root;
        }
        
        return average;
    }
}

// Pure divConq
class ResultType {
    int sum;
    int numOfNodes;
    double maxAvg;
    TreeNode node;
    public ResultType(int sum, int numOfNodes, double maxAvg, TreeNode node) {
        this.sum = sum;
        this.numOfNodes = numOfNodes;
        this.maxAvg = maxAvg;
        this.node = node;
    }
}

public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the root of the maximum average of subtree
     */
    public TreeNode findSubtree2(TreeNode root) {
        // write your code here
        if (root == null){
            return root;
        }

        ResultType res = helper(root);

        return res.node;
    }

    private ResultType helper(TreeNode root) {
        if (root == null){
            return new ResultType(0, 0, Integer.MIN_VALUE, root);
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        ResultType res = new ResultType(
            root.val + left.sum + right.sum,
            left.numOfNodes + right.numOfNodes + 1,
            (root.val + left.sum + right.sum) / (double) (left.numOfNodes + right.numOfNodes + 1),
            root
        );

        if (left.maxAvg > res.maxAvg){
            res.maxAvg = left.maxAvg;
            res.node = left.node;
        }

        if (right.maxAvg > res.maxAvg){
            res.maxAvg = right.maxAvg;
            res.node = right.node;
        }

        return res;
    }
}
