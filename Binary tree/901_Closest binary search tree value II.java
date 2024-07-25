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

// 暴力算法
// O(n) O(n)
public class Solution {
    /**
     * @param root: the given BST
     * @param target: the given target
     * @param k: the given k
     * @return: k values in the BST that are closest to the target
     *          we will sort your return value in output
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // write your code here
        List<Integer> nums = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        inorderTraverse(root, nums);


        int index = 0;
        double min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size(); i++){
            if (Math.abs(nums.get(i) - target) < min){
                min = Math.abs(nums.get(i) - target);
                index = i;
            }
        }

        int l = index - 1, r = index + 1;
        res.add(nums.get(index));
        while (res.size() < k && l >= 0 && r < nums.size()){
            if (target - nums.get(l) > nums.get(r) - target){
                res.add(nums.get(r++));
            }
            else {
                res.add(nums.get(l--));
            }
        }

        while (res.size() < k && l >= 0){
            res.add(nums.get(l--));
        }
        while (res.size() < k && r < nums.size()){
            res.add(nums.get(r++));
        }

        return res;
    }

    private void inorderTraverse (TreeNode root, List<Integer> res){
        if (root == null){
            return;
        }

        inorderTraverse(root.left, res);
        res.add(root.val);
        inorderTraverse(root.right, res);
    }
}
