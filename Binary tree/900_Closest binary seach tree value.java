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

public class Solution {
    /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {
        // write your code here
        return helper(root, target);
    }
    
    private int helper (TreeNode root, double target){
        if (root == null){
            return Integer.MAX_VALUE;
        }
        
        if (root.val > target){
            if (root.left != null){
                int left = helper(root.left, target);
                if (Math.abs(left - target) < Math.abs(root.val - target)){
                    return left;
                }
            }
        } else {
            if (root.right != null){
                int right = helper(root.right, target);
                if (Math.abs(right - target) < Math.abs(root.val - target)){
                    return right;
                }
            }
        }
        
        return root.val;
    }
}

// 方法二: 用 MIN_VALUE, 省去两个判断
public class Solution {
    /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {
        // write your code here
        return helper(root, target);
    }

    private int helper (TreeNode root, double target){
        if (root == null){
            return Integer.MIN_VALUE;
        }

        if (root.val > target){
            int left = helper(root.left, target);
            if (Math.abs(left - target) < Math.abs(root.val - target)){
                return left;
            }
        } else {
            int right = helper(root.right, target);
            if (Math.abs(right - target) < Math.abs(root.val - target)){
                return right;
            }
        }

        return root.val;
    }
}
