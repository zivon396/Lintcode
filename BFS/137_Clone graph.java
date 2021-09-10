/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */

// 边 bfs 边 clone 是不行的，因为每次都会 new 一个新的 neighbor 出来 (丢失遍历过的 node).
public class Solution {
    /*
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // write your code here
        if (node == null){
            return null;
        }
        List<UndirectedGraphNode> nodes = getNodes(node);
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        
        for (UndirectedGraphNode n: nodes){
            map.put(n, new UndirectedGraphNode(n.label));
        }
        
        for (UndirectedGraphNode n: nodes){
            for (UndirectedGraphNode neighbor: n.neighbors){
                map.get(n).neighbors.add(map.get(neighbor));
            }
        }
        
        return map.get(node);
    }
    
    private List<UndirectedGraphNode> getNodes (UndirectedGraphNode node){
        List<UndirectedGraphNode> res = new ArrayList<>();
        
        Set<UndirectedGraphNode> set = new HashSet<>();
        set.add(node);
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);
        
        while (!queue.isEmpty()){
            node = queue.poll();
            res.add(node);
            for (UndirectedGraphNode next: node.neighbors){
                if (!set.contains(next)){
                    set.add(next);
                    queue.offer(next);
                }
            }
        }
        
        return res;
    }
}
