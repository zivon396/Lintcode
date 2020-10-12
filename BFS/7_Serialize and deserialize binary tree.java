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
    // Queue里的元素仍然是 TreeNode; 直接把 String 的第一个值放到 Queue 里开始, 每次往后看 2 个值
    // index++ 最好在 if 中, 不然进入 else 语句后并不会自增
    // 为什么 vals[index++] 最后不会溢出? (以 {3, 9, 20, #, #, 15, 7} 为例)
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
