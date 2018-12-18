public class Solution {
    /**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] graph) {
        // write your code here
        if (n < 1 || n > 1 && (graph == null || graph.length == 0 || graph[0].length == 0)){
            return false;
        }
        if (graph.length != n - 1){
            return false;
        }
        List[] edges = new List[n];
        
        for (int i = 0; i < n; i++){
            edges[i] = new ArrayList<>();
        }
        
        for (int[] edge: graph){
            edges[edge[0]].add(edge[1]);
            edges[edge[1]].add(edge[0]);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        set.add(0);
        queue.offer(0);
        
        int count = 0;
        while (!queue.isEmpty()){
            int curt = queue.poll();
            count++;
            for (int i = 0; i < edges[curt].size(); i++){
                int edge = (int) edges[curt].get(i);
                if (!set.contains(edge)){
                    set.add(edge);
                    queue.offer(edge);
                }
            }
        }
        
        return count == n;
    }
}