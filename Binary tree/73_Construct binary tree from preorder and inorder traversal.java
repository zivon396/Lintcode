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
// 背过
public class Solution {
    /**
     * @param inorder: A list of integers that inorder traversal of a tree
     * @param postorder: A list of integers that postorder traversal of a tree
     * @return: Root of a tree
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder.length != preorder.length) {
            return null;
        }
        return myBuildTree(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
    }
    
    private TreeNode myBuildTree(int[] inorder, int instart, int inend,
                                 int[] preorder, int prestart, int preend){
        if (instart > inend){
            return null;
        }
        
        TreeNode root = new TreeNode(preorder[prestart]);
        int position = findPosition(inorder, instart, inend, root.val);
        
        root.left = myBuildTree(inorder, instart, position - 1, preorder, prestart + 1, prestart + position - instart);
        root.right = myBuildTree(inorder, position + 1, inend, preorder, preend + position - inend + 1, preend);
        
        return root;
    }
    
    private int findPosition(int[] arr,
                             int start,
                             int end,
                             int key){
        int i;
        for (i = start; i <= end; i++){
            if (arr[i] == key){
                return i;
            }
        }
        return -1;
    }
}
