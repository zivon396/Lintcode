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
    /*
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        
        helper(root, path, target, 0, res);
        
        return res;
    }
    
    private void helper (TreeNode root,
                         List<Integer> path,
                         int target,
                         int level,
                         List<List<Integer>> res){
        if (root == null){
            return;
        }
        int temp = target;
        path.add(root.val);
        for (int i = level; i >=0; i--){
            temp -= path.get(i);
            if (temp == 0){
                List<Integer> valid_path = new ArrayList<>();
                for (int j = i; j <= level; j++){
                    valid_path.add(path.get(j));
                }
                res.add(valid_path);
                //不能加break
            }
        }
        
        helper(root.left, path, target, level + 1, res);
        helper(root.right, path, target, level + 1, res);
        path.remove(path.size() - 1);
    }
}