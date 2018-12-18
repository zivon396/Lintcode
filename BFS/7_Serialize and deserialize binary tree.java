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
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        // write your code here
        String res = "";
        if (root == null){
            return res;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node == null){
                res += "#,";
            } else {
                res += node.val + ",";
            }
            if (node != null){
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        
        res = res.substring(0, res.length() - 1);
        return res;
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        // write your code here
        if (data == null || data.length() == 0){
            return null;
        }
        
        String[] vals = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        int index = 1;
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (!vals[index++].equals("#")){
                node.left = new TreeNode(Integer.parseInt(vals[index - 1]));
                queue.offer(node.left);
            } else {
                node.left = null;
            }
            if (!vals[index++].equals("#")){
                node.right = new TreeNode(Integer.parseInt(vals[index - 1]));
                queue.offer(node.right);
            } else {
                node.right = null;
            }
        }
        
        return root;
    }
}