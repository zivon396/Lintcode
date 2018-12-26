/**
 * Definition of ParentTreeNode:
 * 
 * class ParentTreeNode {
 *     public ParentTreeNode parent, left, right;
 * }
 */


public class Solution {
    /*
     * @param root: The root of the tree
     * @param A: node in the tree
     * @param B: node in the tree
     * @return: The lowest common ancestor of A and B
     */
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
        // write your code here
        List<ParentTreeNode> pathA = new ArrayList<ParentTreeNode>();
        List<ParentTreeNode> pathB = new ArrayList<ParentTreeNode>();
        
        while (A != null){
            pathA.add(A);
            A = A.parent;
        }
        
        while (B != null){
            pathB.add(B);
            B = B.parent;
        }
        
        int p1 = pathA.size() - 1, p2 = pathB.size() - 1;
        ParentTreeNode LCA = null;
        
        while (p1 >= 0 && p2 >= 0 && pathA.get(p1) == pathB.get(p2)){
            LCA = pathA.get(p1);
            p1--;
            p2--;
        }
        
        return LCA;
    }
}