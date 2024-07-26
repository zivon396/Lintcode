/**
 * Definition of ParentTreeNode:
 * class ParentTreeNode {
 *     public int val;
 *     public ParentTreeNode parent, left, right;
 * }
 */
// 对每个节点为 path 的起点都 DFS 一遍 (line 31)
public class Solution {
    /*
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
    List<List<Integer>> results = new ArrayList<List<Integer>>();
    public List<List<Integer>> binaryTreePathSum3(ParentTreeNode root, int target) {
        // write your code here
        if (root == null){
            return results;
        }
        driver(root, target);
        return results;
    }
    
    public void driver(ParentTreeNode root, int target){
        if (root == null){
            return;
        }
        List<ParentTreeNode> path = new LinkedList<>();
        // dfs on current node
        dfs(root, path, target, 0);
        // do the same for left and right
        driver(root.left, target);
        driver(root.right, target);
    }
    
    public void dfs(ParentTreeNode root, List<ParentTreeNode> path, int target, int current){
        if (root == null){
            return;
        }
        
        current += root.val;
        path.add(root);
        // sum check
        if (current == target){
            List<Integer> copy = new ArrayList<> ();
            for (ParentTreeNode node: path){
                copy.add(node.val);
            }
            results.add(copy);
        }
        // need to make sure we don't go into loops...
        // not memory-efficient, but does the job.
        Set<ParentTreeNode> visited = new HashSet<>(path);
        if (!visited.contains(root.left)){
            dfs(root.left, path, target, current);
        }
        if (!visited.contains(root.right)){
            dfs(root.right, path, target, current);
        }
        if (!visited.contains(root.parent)){
            dfs(root.parent, path, target, current);
        }
        current -= root.val;
        path.remove(path.size() - 1);
    }
}
