// Traverse
// 1 2 8 4 5 6 7 3 9
// 在 8 处, 下一个下降; 在 3 处, 此处下降
public class Solution {
    /**
     * @param root: the given tree
     * @return: the tree after swapping
     */
    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode last = new TreeNode(Integer.MIN_VALUE);
    public TreeNode bstSwappedNode(TreeNode root) {
        // write your code here
        traverse(root);

        if (first == null || second == null){
            return root;
        }

        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;

        return root;
    }

    private void traverse (TreeNode root){
        if (root == null){
            return;
        }

        traverse(root.left);
        if (root.val <= last.val){
            if (first == null){
                first = last;
            }
            second = root;
        }

        last = root;
        traverse(root.right);
    }
}
