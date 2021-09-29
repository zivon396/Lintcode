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
// version 1: recursion
// 本质上和 ResultType 是一样的 -> 都是 recursion 里初始化一个新的 res 并返回, 只不过本题的 ResultType 就是 List<String>.
public class Solution {
    /**
     * @param root: the root of the binary tree
     * @return: all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        // write your code here
        List<String> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        
        List<String> left = binaryTreePaths(root.left);
        List<String> right = binaryTreePaths(root.right);
        
        for (String path: left){
            res.add(root.val + "->" + path);
        }
        for (String path: right){
            res.add(root.val + "->" + path);
        }
        
        if (res.size() == 0){
            res.add("" + root.val);
        }
        return res;
    }
}

// helper 版
public class Solution {
    /**
     * @param root: the root of the binary tree
     * @return: all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        // write your code here

        List<String> res = helper(root);

        return res;
    }

    private List<String> helper (TreeNode root){
        if (root == null){
            return new ArrayList<>();
        }

        List<String> left = helper(root.left);
        List<String> right = helper(root.right);

        List<String> res = new ArrayList<>();

        for (String path: left){
            res.add(root.val + "->" + path);
        }

        for (String path: right){
            res.add(root.val + "->" + path);
        }

        if (res.size() == 0){
            res.add("" + root.val);
        }

        return res;
    }
}

// version 2: traverse
// 类似 246, init 一个全局的 res 和 path, traverse tree.
public class Solution {
    /**
     * @param root: the root of the binary tree
     * @return: all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        // write your code here

        List<String> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        helper(root, path, res);

        return res;
    }

    private void helper (TreeNode root, List<Integer> path, List<String> res){
        if (root == null){
            return;
        }

        path.add(root.val);

        if (root.left == null && root.right == null){
            String valid_path = "";
            for (int val: path){
                valid_path += val + "->";
            }

            valid_path = valid_path.substring(0, valid_path.length() - 2);
            res.add(valid_path);
        }

        helper(root.left, path, res);
        helper(root.right, path, res);
        path.remove(path.size() - 1);

        return;
    }
}
