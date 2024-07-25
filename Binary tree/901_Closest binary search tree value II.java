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

// 最优算法
// 时间复杂度 O(k + log(n)) ? (还是 O(klog(n)) ?)
// 空间复杂度 O(log(n))
// getStack() => 在假装插入 target 的时候, 看看一路走过的节点都是哪些, 放到 stack 里, 用于 iterate
// moveUpper(stack) => 根据 stack, 挪动到 next node
// moveLower(stack) => 根据 stack, 挪动到 prev node
// 有了这些函数之后, 就可以把整个树当作一个数组一样来处理, 只不过每次 i++ 的时候要用 moveUpper, i-- 的时候要用 moveLower
class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> values = new ArrayList<>();
        
        if (k == 0 || root == null) {
            return values;
        }
        
        Stack<TreeNode> lowerStack = getStack(root, target);
        Stack<TreeNode> upperStack = new Stack<>();
        upperStack.addAll(lowerStack);
        if (target < lowerStack.peek().val) {
            moveLower(lowerStack);
        } else {
            moveUpper(upperStack);
        }
        
        for (int i = 0; i < k; i++) {
            if (lowerStack.isEmpty() ||
                   !upperStack.isEmpty() && target - lowerStack.peek().val > upperStack.peek().val - target) {
                values.add(upperStack.peek().val);
                moveUpper(upperStack);
            } else {
                values.add(lowerStack.peek().val);
                moveLower(lowerStack);
            }
        }

        return values;
    }
    
    private Stack<TreeNode> getStack(TreeNode root, double target) {
        Stack<TreeNode> stack = new Stack<>();
        
        while (root != null) {
            stack.push(root);
            
            if (target < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        
        return stack;
    }
    // 挪到下一个节点
    // 如果当前点存在右子树, 那么就是右子树中 "一路向西" 最左边的那个点
    // 如果当前点不存在右子树, 则是从当前点往回走的路径中, 第一个右拐的点
    public void moveUpper(Stack<TreeNode> stack) {
        TreeNode node = stack.peek();
        if (node.right == null) {
            node = stack.pop();
            while (!stack.isEmpty() && stack.peek().right == node) {
                node = stack.pop();
            }
            return;
        }
        
        node = node.right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
    // 挪到上一个节点
    // 如果当前点存在左子树, 那么就是左子树中 "一路向东" 最右边的那个点
    // 如果当前点不存在左子树, 则是从当前点往回走的路径中, 第一个左拐的点
    public void moveLower(Stack<TreeNode> stack) {
        TreeNode node = stack.peek();
        if (node.left == null) {
            node = stack.pop();
            while (!stack.isEmpty() && stack.peek().left == node) {
                node = stack.pop();
            }
            return;
        }
        
        node = node.left;
        while (node != null) {
            stack.push(node);
            node = node.right;
        }
    }
}


// 暴力算法
// O(n) O(n)
public class Solution {
    /**
     * @param root: the given BST
     * @param target: the given target
     * @param k: the given k
     * @return: k values in the BST that are closest to the target
     *          we will sort your return value in output
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // write your code here
        List<Integer> nums = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        inorderTraverse(root, nums);


        int index = 0;
        double min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size(); i++){
            if (Math.abs(nums.get(i) - target) < min){
                min = Math.abs(nums.get(i) - target);
                index = i;
            }
        }

        int l = index - 1, r = index + 1;
        res.add(nums.get(index));
        while (res.size() < k && l >= 0 && r < nums.size()){
            if (target - nums.get(l) > nums.get(r) - target){
                res.add(nums.get(r++));
            }
            else {
                res.add(nums.get(l--));
            }
        }

        while (res.size() < k && l >= 0){
            res.add(nums.get(l--));
        }
        while (res.size() < k && r < nums.size()){
            res.add(nums.get(r++));
        }

        return res;
    }

    private void inorderTraverse (TreeNode root, List<Integer> res){
        if (root == null){
            return;
        }

        inorderTraverse(root.left, res);
        res.add(root.val);
        inorderTraverse(root.right, res);
    }
}
