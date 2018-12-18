/**
 * Definition for Undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */


public class Solution {
    /*
     * @param nodes: a array of Undirected graph node
     * @return: a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (nodes == null || nodes.size() == 0){
            return res;
        }
        
        Set<UndirectedGraphNode> set = new HashSet<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        
        for (int i = 0; i < nodes.size(); i++){
            UndirectedGraphNode node = nodes.get(i);
            if (!set.contains(node)){
                List<Integer> level = new ArrayList<>();
                queue.offer(node);
                set.add(node);
                
                
                while (!queue.isEmpty()){
                    UndirectedGraphNode n = queue.poll();
                    level.add(n.label);
                    for (UndirectedGraphNode neighbor: n.neighbors){
                        if (!set.contains(neighbor)){
                            queue.offer(neighbor);
                            set.add(neighbor);
                        }
                    }
                }
                Collections.sort(level);
                res.add(level);
            }
        }
        return res;
    }
}