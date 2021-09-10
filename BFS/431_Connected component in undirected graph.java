/**
 * Definition for Undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */

// 此题相当于重复利用 queue, 挨个检查 node, 不在 set 里就开始一次 BFS.
// graph 版的 number of islands
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


// 也可以写个 bfs 函数, 传入 set 和 res.
public class Solution {
    /**
     * @param nodes: a array of Undirected graph node
     * @return: a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
        // write your code here
        if (nodes == null){
            return null;
        }

        List<List<Integer>> res = new ArrayList<>();
        Set<UndirectedGraphNode> set = new HashSet<>();
        
        for (UndirectedGraphNode node: nodes){
            if (!set.contains(node)){
                bfs(node, set, res);
            }
        }

        return res;
    }

    private void bfs (UndirectedGraphNode node, Set<UndirectedGraphNode> set, List<List<Integer>> res){
        List<Integer> com = new ArrayList<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);
        set.add(node);

        while (!queue.isEmpty()){
            UndirectedGraphNode curt = queue.poll();
            com.add(curt.label);
            for (UndirectedGraphNode neighbor: curt.neighbors){
                if (!set.contains(neighbor)){
                    queue.offer(neighbor);
                    set.add(neighbor);
                }
            }
        }

        Collections.sort(com);
        res.add(com);
    }
}
