// 注意理解 valid tree 的条件: 点数 = 边数 + 1
// 注意 edge 要加两遍 -> 因为是 Undirected Graph
// 注意 遍历 List 元素时要转换成 int
// 注意不能用 for(List edge: edges) 来遍历
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
        
        return count == n; // 这里也可以直接返回 set.size() == n
    }
}

// UnionFind
public class Solution {
    /**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    class UnionFind {
        HashMap<Integer, Integer> father = new HashMap<>();
        UnionFind (int n){
            for (int i = 0; i < n; i++){
                father.put(i, i);
            }
        }
        
        int compressed_find (int x){
            int parent = father.get(x);
            while (parent != father.get(parent)){
                parent = father.get(parent);
            }
            
            int fa = x;
            while (fa != father.get(fa)){
                int tmp = father.get(fa);
                father.put(fa, parent);
                fa = tmp;
            }
            
            return parent;
        }
        
        void union (int x, int y){
            int fa_x = father.get(x);
            int fa_y = father.get(y);
            if (fa_x != fa_y){
                father.put(fa_x, fa_y);
            }
        }
    }
    
    public boolean validTree(int n, int[][] edges) {
        // write your code here
        if (edges.length != n - 1){
            return false;
        }
        
        UnionFind uf = new UnionFind(n);
        
        for (int i = 0; i < edges.length; i++){
            if (uf.compressed_find(edges[i][0]) == uf.compressed_find(edges[i][1])) {
                return false;
            }
            uf.union(edges[i][0], edges[i][1]);
        }
        
        return true;
    }
}
