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
// Assumption:
// 一个 node 的左子树 -> 它左边遇到比它大的数之前的所有数组成的树
// 一个 node 的右子树 -> 它右边遇到比它大的数之前的所有数组成的树
// 单调递减栈中, 每逢增加 -> stack.peek() 一定是叶子结点
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
                TreeNode son = stack.pop(); // son 一定是叶子结点 / 某个节点的孩子 -> 接下来的任务就是找到它的 parent
                if (stack.isEmpty()){
                    // 栈空 -> 它的 parent 一定就是 node
                    node.left = son;
                    break;
                }
                
                TreeNode left = stack.peek();
                if (left.val < node.val){
                    // 说明 peek 右边遇到比 peek 大的数之前只有 son 一个
                    left.right = son;
                } else {
                    // 说明 node 左边遇到比 node 大的数之前只有 son 一个
                    node.left = son;
                }
            }
            stack.push(node);
        }
        
        return stack.peek().left;
    }
}

// version 2: 无 while loop 版
// 必须 push 完之后先生成下一个 node, 不然 i-- 之后每次会生成新的 TreeNode
// 也可以每次先生成的时候判断一下是否重复
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
