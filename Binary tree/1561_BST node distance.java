// Build BST + find LCA
public class Solution {
    /**
     * @param numbers: the given list
     * @param node1: the given node1
     * @param node2: the given node2
     * @return: the distance between two nodes
     */
    public int bstDistance(int[] numbers, int node1, int node2) {
        // Write your code here
        if (numbers == null || numbers.length < 2) {
            return -1;
        }
        if (!check(numbers, node1, node2)) {
            return -1;
        }

        TreeNode root = buildTree(numbers);

        while (node1 < root.val && node2 < root.val || node1 > root.val && node2 > root.val) {
            if (node1 < root.val && node2 < root.val) {
                root = root.left;
            }
            else {
                root = root.right;
            }
        }

        return findDis(root, node1) + findDis(root, node2);
    }

    private boolean check(int[] numbers, int node1, int node2) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < numbers.length; i ++) {
            set.add(numbers[i]);
        }

        if (set.contains(node1) && set.contains(node2)) {
            return true;
        }

        return false;
    }

    private TreeNode buildTree (int[] numbers){
        TreeNode root = new TreeNode(numbers[0]);
        for (int i = 1; i < numbers.length; i++){
            insert(root, numbers[i]);
        }

        return root;
    }

    private TreeNode insert (TreeNode root, int num){
        if (root == null){
            return new TreeNode(num);
        }

        if (num < root.val){
            root.left = insert(root.left, num);
        }
        else {
            root.right = insert(root.right, num);
        }

        return root;
    }

    private int findDis (TreeNode root, int value){
        if (root.val == value){
            return 0;
        }

        if (value < root.val){
            return findDis(root.left, value) + 1;
        }
        else {
            return findDis(root.right, value) + 1;
        }
    }
    // private int findDis (TreeNode root, int value){
    //     int dis = 0;
    //     while (root.val != value) {
    //         dis ++;
    //         if (root.val > value) {
    //             root = root.left;
    //         } else {
    //             root = root.right;
    //         }
    //     }
        
    //     return dis;
    // }
}
