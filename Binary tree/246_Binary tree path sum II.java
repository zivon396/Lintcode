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

// init 一个全局的 res 和 path, traverse tree. 因为 valid_path 里的元素和层数 (level) 是一一对应的, 每往下 traverse 一层就以当前 level 为终点, 逆向 check.
public class Solution {
    /*
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        
        helper(root, path, target, 0, res);
        
        return res;
    }
    
    private void helper (TreeNode root,
                         List<Integer> path,
                         int target,
                         int level,
                         List<List<Integer>> res){
        if (root == null){
            return;
        }
        int temp = target;
        path.add(root.val);
        for (int i = level; i >=0; i--){
            temp -= path.get(i);
            if (temp == 0){
                List<Integer> valid_path = new ArrayList<>();
                for (int j = i; j <= level; j++){
                    valid_path.add(path.get(j));
                }
                res.add(valid_path);
                // 不能加break
            }
        }
        
        helper(root.left, path, target, level + 1, res);
        helper(root.right, path, target, level + 1, res);
        path.remove(path.size() - 1);
    }
}

// level 不是必要的
public class Solution {
    /*
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        
        helper(root, path, res, target);
        
        return res;
    }
    
    private void helper (TreeNode root, 
                         List<Integer> path, 
                         List<List<Integer>> res, 
                         int target){
        if (root == null){
            return;
        }
        
        int tmp = target;
        path.add(root.val);
        for (int i = path.size() - 1; i >= 0; i--){
            tmp -= path.get(i);
            if (tmp == 0){
                List<Integer> temp = new ArrayList<>();
                for (int j = i; j <= path.size() - 1; j++){
                    temp.add(path.get(j));
                }
                res.add(temp);
                // 不能加break
            }
        }
        
        helper(root.left, path, res, target);
        helper(root.right, path, res, target);
        path.remove(path.size() - 1);
    }
}

// 原创, 可以省去一个 temp
public class Solution {
    /**
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     *          we will sort your return value in output
     */
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }

        List<Integer> path = new ArrayList<>();
        helper(root, target, path, res);

        return res;
    }

    private void helper(TreeNode root,
                        int target,
                        List<Integer> path,
                        List<List<Integer>> res) {
        if (root == null){
            return;
        }

        path.add(root.val);
        List<Integer> level = new ArrayList<>();
        int size = path.size();
        int sum = 0;
        for(int i = size - 1; i >= 0; i--) {
            sum += path.get(i);
            level.add(path.get(i));
            if (sum == target){
                Collections.reverse(level);
                res.add(new ArrayList<>(level));
                Collections.reverse(level);
            }
        }

        helper(root.left, target, path, res);
        helper(root.right, target, path, res);
        path.remove(path.size() - 1);
    }
}


// version 2
public class Solution {
    /*
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        List<Integer> path = new ArrayList<>();
        
        path.add(root.val);
        helper(root, path, res, target);
        
        return res;
    }
    
    private void helper (TreeNode root,
                         List<Integer> path, 
                         List<List<Integer>> res, 
                         int target){
        
        int tmp = target;
        for (int i = path.size() - 1; i >= 0; i--){
            tmp -= path.get(i);
            if (tmp == 0){
                List<Integer> temp = new ArrayList<>();
                for (int j = i; j <= path.size() - 1; j++){
                    temp.add(path.get(j));
                }
                res.add(temp);
                //不能加break
            }
        }
        
        if (root.left != null){
            path.add(root.left.val);
            helper(root.left, path, res, target);
            path.remove(path.size() - 1);
        }

        if (root.right != null){
            path.add(root.right.val);
            helper(root.right, path, res, target);
            path.remove(path.size() - 1);
        }
    }
}
