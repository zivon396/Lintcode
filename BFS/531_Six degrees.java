/**
 * Definition for Undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { 
 *         label = x;
 *         neighbors = new ArrayList<UndirectedGraphNode>(); 
 *     }
 * };
 */

// version 1: 用 map 存储 degree, 不用再加一层 for 循环
public class Solution {
    /*
     * @param graph: a list of Undirected graph node
     * @param s: Undirected graph node
     * @param t: Undirected graph nodes
     * @return: an integer
     */
    public int sixDegrees(List<UndirectedGraphNode> graph, UndirectedGraphNode s, UndirectedGraphNode t) {
        // write your code here
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Map<UndirectedGraphNode, Integer> visited = new HashMap<>();
        
        if (s == t){
            return 0;
        }
        
        queue.offer(s);
        visited.put(s, 0);
        
        while (!queue.isEmpty()){
            UndirectedGraphNode node = queue.poll();
            int count = visited.get(node);
            for (UndirectedGraphNode neighbor: node.neighbors){
                if (neighbor == t){
                    return count + 1;
                }
                if (!visited.containsKey(neighbor)){
                    visited.put(neighbor, count + 1);
                    queue.offer(neighbor);
                }
            }
        }
        
        return -1;
    }
}


//version 2:
public class Solution {
    /*
     * @param graph: a list of Undirected graph node
     * @param s: Undirected graph node
     * @param t: Undirected graph nodes
     * @return: an integer
     */
    public int sixDegrees(List<UndirectedGraphNode> graph, UndirectedGraphNode s, UndirectedGraphNode t) {
        // write your code here
        if (graph == null || graph.size() == 0){
            return -1;
        }
        if (s == t){
            return 0;
        }
        
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> set = new HashSet<>();
        set.add(s);
        queue.offer(s);
        
        int count = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++){
                UndirectedGraphNode curt = queue.poll();
                for (UndirectedGraphNode neighbor: curt.neighbors){
                    if (neighbor == t){
                        return count;
                    }
                    if (!set.contains(neighbor)){
                        set.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }
        
        return -1;
    }
}
