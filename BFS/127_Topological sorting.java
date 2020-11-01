/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */
// while loop 里面的 degrees.get() 不需要 default value, 因为之前 initialize degrees 的时候所有 neighbors 的 degree 都至少是 1 了。
public class Solution {
    /*
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        Map<DirectedGraphNode, Integer> degrees = new HashMap<>();
        
        for (int i = 0; i < graph.size(); i++){
            for (DirectedGraphNode neighbor: graph.get(i).neighbors){
                degrees.put(neighbor, degrees.getOrDefault(neighbor, 0) + 1);
            }
        }
        
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (int i = 0; i < graph.size(); i++){
            if (degrees.getOrDefault(graph.get(i), 0) == 0){
                queue.offer(graph.get(i));
            }
        }
        
        int count = 0;
        while (!queue.isEmpty()){
            count++;
            DirectedGraphNode curt = queue.poll();
            res.add(curt);
            for (DirectedGraphNode neighbor: curt.neighbors){
                degrees.put(neighbor, degrees.get(neighbor) - 1);
                if (degrees.get(neighbor) == 0){
                    queue.offer(neighbor);
                }
            }
        }
        
        if (count != graph.size()){
            return new ArrayList<>();
        }
        return res;
    }
}
