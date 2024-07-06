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
// version 1: for + while
// 和模板一样, 每次先 pop, 然后和 peek 比较
public class Solution {
    /**
     * @param A: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */
    public TreeNode maxTree(int[] A) {
        // write your code here
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = null;
        
        for (int i = 0; i <= A.length; i++){
            TreeNode node = i == A.length ? new TreeNode(Integer.MAX_VALUE) : new TreeNode(A[i]);
            while (!stack.isEmpty() && node.val > stack.peek().val){
                TreeNode son = stack.pop();
                if (stack.isEmpty()){
                    // 已知 son 的右边没有比 son 大的, stack 空说明 son 的左边也没有比 son 大的了, 因此 son 就是 node 左边最大的
                    node.left = son;
                    break;
                }
                
                TreeNode left = stack.peek();
                if (left.val < node.val){
                    // 说明 peek 右边比 peek 大的只有 son 一个
                    left.right = son;
                } else {
                    // 说明 node 左边比 node 小的只有 son 一个
                    node.left = son;
                }
            }
            stack.push(node);
        }
        
        return stack.peek().left;
    }
}

//version 2: 无 while loop 版
public class Solution {
    /**
     * @param A: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */
    public TreeNode maxTree(int[] A) {
        // write your code here
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = new TreeNode(A[0]);
        
        for (int i = 0; i <= A.length; i++){
            if (stack.isEmpty() || node.val <= stack.peek().val){
                stack.push(node);
                node = i >= A.length - 1 ? new TreeNode(Integer.MAX_VALUE) : new TreeNode(A[i+1]);
            }
            else {
                TreeNode son = stack.pop();
                if (stack.isEmpty()){
                    node.left = son;
                    i--;
                    continue;
                }
                
                TreeNode left = stack.peek();
                if (left.val < node.val){
                    left.right = son;
                } else {
                    node.left = son;
                }
                i--;
            }
        }
        
        return stack.peek().left;
    }
}
