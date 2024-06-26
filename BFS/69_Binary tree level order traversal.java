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
     * @param root: A Tree
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        
        bfs(root, res);
        
        return res;
    }
    
    private void bfs (TreeNode root, List<List<Integer>> res){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> level;
        
        while (!queue.isEmpty()){
            int size = queue.size();
            level = new ArrayList<>();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
            res.add(level);
        }
    }
}
