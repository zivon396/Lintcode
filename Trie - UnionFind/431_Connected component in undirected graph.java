/**
 * Definition for Undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
class UnionFind {
    private HashMap<Integer, Integer> father = new HashMap<Integer, Integer>();
    
    public UnionFind(HashSet<Integer> hashSet){
        for (Integer now : hashSet) {
            father.put(now, now);
        }
    }
    
    public int find(int x){
        int parent = father.get(x);
        while (parent != father.get(parent)) {
            parent = father.get(parent);
        }
        int next;
        while (x != father.get(x)) {
            next = father.get(x);
            father.put(x, parent);
            x = next;
        }
        return parent;
    }

    public void union(int x, int y){
        int fa_x = find(x);
        int fa_y = find(y);
        if (fa_x != fa_y)
            father.put(fa_x, fa_y);
    }
}

public class Solution {
    /*
     * @param nodes: a array of Undirected graph node
     * @return: a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
        // write your code here
        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (UndirectedGraphNode now : nodes) {
            hashSet.add(now.label);
            for (UndirectedGraphNode neighbour : now.neighbors) {
                hashSet.add(neighbour.label);
            }
        }
        UnionFind uf = new UnionFind(hashSet);

        for (UndirectedGraphNode now : nodes) {

            for (UndirectedGraphNode neighbour : now.neighbors) {
                int fnow = uf.find(now.label);
                int fneighbour = uf.find(neighbour.label);
                if (fnow != fneighbour) {
                    uf.union(now.label, neighbour.label);
                }
            }
        }

        return print(hashSet, uf, nodes.size());
    }
    
    public List<List<Integer> > print(HashSet<Integer> hashSet, UnionFind uf, int n) {
        List<List<Integer> > ans = new ArrayList<List<Integer> >();
        HashMap<Integer, List<Integer> > hashMap = new HashMap<Integer, List<Integer> >();
        
        for (int i : hashSet) {
            int fa = uf.find(i);
            List<Integer> now = hashMap.getOrDefault(fa, new ArrayList<Integer>());
            now.add(i);
            hashMap.put(fa, now);
        }
        
        for (List<Integer> now : hashMap.values()) {
            Collections.sort(now);
            ans.add(now);
        }
        return ans;
    }
}