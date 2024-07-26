// 最简洁版本
// 访问所有节点用时 O(n), 均摊下来每个节点就是 O(1)
// 不必再回溯, 因为一直在 pop, 可以保证每次 pop 的一定是上一个 pop 节点回溯路径中第一个右拐的点
// 注意 901 无法用这个方法, 因为 901 初始放进来的节点不是一直向左走到底的, 放进来的顺序和 pop 的逻辑有矛盾
public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();
    
    // @param root: The root of binary tree.
    public BSTIterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    
    //@return: return next node
    public TreeNode next() {
        if (stack.isEmpty())
            return null;
            
        TreeNode node = stack.pop();    
        // push all left path of right subtree
        TreeNode right = node.right;  
        while (right != null) {
            stack.push(right);
            right = right.left;
        }

        return node;
    }
}


// 和 901 一样
// 只不过初始路径是一直向左到最底
public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();
    
    // @param root: The root of binary tree.
    public BSTIterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    
    //@return: return next node
    public TreeNode next() {
        TreeNode curt = stack.peek();
        TreeNode node = curt;
        
        // move to the next node
        if (node.right == null) {
            node = stack.pop();
            while (!stack.isEmpty() && stack.peek().right == node) {
                node = stack.pop();
            }
        } else {
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        
        return curt;
    }
}
