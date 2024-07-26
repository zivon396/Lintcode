// Div & Conq
public class Solution {
    /**
     * @param root: root of the given tree
     * @return: whether it is a mirror of itself 
     */
    public boolean isSymmetric(TreeNode root) {
        // Write your code here
        if (root == null) {
            return true;
        }
        
        return isSymmetric(root.left, root.right);
    }
    
    private boolean isSymmetric(TreeNode leftRoot, TreeNode rightRoot) {
        if (leftRoot == null && rightRoot == null) {
            return true;
        }
        if (leftRoot == null || rightRoot == null) {
            return false;
        }
        if (leftRoot.val != rightRoot.val) {
            return false;
        }
        
        boolean left = isSymmetric(leftRoot.left, rightRoot.right);
        boolean right = isSymmetric(leftRoot.right, rightRoot.left);
        
        return left && right;
    }
}

// BFS
public class Solution {
    /**
     * @param root: root of the given tree
     * @return: whether it is a mirror of itself 
     */
    public boolean isSymmetric(TreeNode root) {
        // Write your code here
        if (root == null) {
            return true;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++){
                TreeNode curt = queue.poll();
                if (curt != null){
                    queue.offer(curt.left);
                    queue.offer(curt.right);
                    level.add(curt.val);
                }
                else {
                    level.add(-1);
                }
            }

            if (!isValid(level)){
                return false;
            }
        }

        return true;
    }

    private boolean isValid (List<Integer> list){
        for (int i = 0; i < list.size() / 2; i++){
            if (list.get(i) != list.get(list.size() - i - 1)){
                return false;
            }
        }

        return true;
    }
}
