// Version 1 (原创)
public class Solution {
    /**
     * @param root: The root of the binary search tree.
     * @param value: Remove the node with given value.
     * @return: The root of the binary search tree after removal.
     */
    public TreeNode removeNode(TreeNode root, int value) {
        // write your code here
        if (root == null){
            return root;
        }

        if (root.val == value){
            if (root.left == null && root.right == null){
                root = null;
            }
            else if (root.left == null){
                root = root.right;
            }
            else if (root.right == null){
                root = root.left;
            }
            else {
                TreeNode max = findMax(root.left);
                max.right = root.right;
                root = root.left;
            }
        }
        else if (root.val > value){
            root.left = removeNode(root.left, value);
        }
        else {
            root.right = removeNode(root.right, value);
        }

        return root;
    }

    private TreeNode findMax (TreeNode root){
        if (root.right == null){
            return root;
        }

        return findMax(root.right);
    }
}

// Version 2
// 只有 else 里面的逻辑不同
public class Solution {
    /**
     * @param root: The root of the binary search tree.
     * @param value: Remove the node with given value.
     * @return: The root of the binary search tree after removal.
     */
    public TreeNode removeNode(TreeNode root, int value) {
        // write your code here
        if (root == null){
            return root;
        }

        if (root.val == value){
            if (root.left == null && root.right == null){
                root = null;
            }
            else if (root.left == null){
                root = root.right;
            }
            else if (root.right == null){
                root = root.left;
            }
            else {
                int maxLeft = findMax(root.left);
                root.val = maxLeft;
                root.left = removeNode(root.left, maxLeft);
            }
        }
        else if (root.val > value){
            root.left = removeNode(root.left, value);
        }
        else {
            root.right = removeNode(root.right, value);
        }

        return root;
    }

    private int findMax (TreeNode root){
        if (root.right == null){
            return root.val;
        }

        return findMax(root.right);
    }
}
