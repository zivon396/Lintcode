/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */

// 边 bfs 边 clone 是不行的，因为每次都会 new 一个新的 neighbor 出来 (丢失遍历过的 node) -> 其实可以的!!! 见方法二
// 主要的思想是 先把所有 node 找到 (BFS) 并放到一个可用 for 循环遍历的数据结构里, 此题选择 List 来存放
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

// 边 BFS 边 clone (独创):
public class Solution {
    /*
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // write your code here
        if (node == null){
            return node;
        }

        return bfs(node);
    }

    private UndirectedGraphNode bfs (UndirectedGraphNode node){
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> visited = new HashSet<>();
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        UndirectedGraphNode res = new UndirectedGraphNode(node.label);
        queue.offer(node);
        map.put(node, res);
        visited.add(node);


        while (!queue.isEmpty()){
            UndirectedGraphNode curt = queue.poll();
            UndirectedGraphNode copy = map.get(curt);
            for (UndirectedGraphNode neighbor: curt.neighbors){
                UndirectedGraphNode neighborCopy = map.getOrDefault(neighbor, new UndirectedGraphNode(neighbor.label));
                copy.neighbors.add(neighborCopy);

                if (!visited.contains(neighbor)){
                    queue.offer(neighbor);
                    map.put(neighbor, neighborCopy);
                    visited.add(neighbor);
                }
            }
        }

        return res;
    }
}
