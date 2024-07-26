// Div & Conq
public class Solution {
    /**
     * @param a: an integer array
     * @return: A tree node
     */
    public TreeNode sortedArrayToBST(int[] a) {
        // write your code here
        if (a == null || a.length == 0){
            return null;
        }

        return convertToBST(a, 0, a.length - 1);
    }

    private TreeNode convertToBST (int[] a, int start, int end){
        if (start > end){
            return null;
        }
        if (start == end){
            return new TreeNode(a[start]);
        }

        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(a[mid]);

        root.left = convertToBST(a, start, mid - 1);
        root.right = convertToBST(a, mid + 1, end);

        return root;
    }
}
