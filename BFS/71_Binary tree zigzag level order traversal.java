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
// Queue + reverse
public class Solution {
    /**
     * @param root: A Tree
     * @return: A list of lists of integer include the zigzag level order traversal of its nodes' values.
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        int sign = 1;
        
        if (root == null){
            return res;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()){
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
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
            if (sign == -1){
                Collections.reverse(level);
            }
            sign = -sign;
            res.add(level);
        }
        
        return res;
    }
}

// Dequeue
public class Solution {
    /**
     * @param root: A Tree
     * @return: A list of lists of integer include the zigzag level order traversal of its nodes' values.
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (null == root ) return result;
        
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        boolean normalOrder = true;
        
        queue.offer(root);
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> temp = new LinkedList<>();
            for (int i = 0; i < size; ++i) {
                TreeNode p ;
                if (normalOrder) {
                    p = queue.poll();
                    temp.addLast(p.val);    
                } else {
                    p = queue.poll();
                    temp.addFirst(p.val);    
                }
                
                if (p.left != null)
                    queue.offer(p.left);
                if (p.right != null)
                    queue.offer(p.right);
            }
            normalOrder = !normalOrder;
            result.add(temp);
        }
        return result;
    }
}
