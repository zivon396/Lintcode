// 若 edges.length != n - 1 直接返回 false
// 若 edges.length = n - 1, 则要么 valid, 要么: 存在环且不连通
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
            int fa_x = compressed_find(x);
            int fa_y = compressed_find(y);
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

// version 2
public class Solution {
    /**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    class UnionFind {
        HashMap<Integer, Integer> father = new HashMap<>();
        int size;
        UnionFind (int n){
            for (int i = 0; i < n; i++){
                father.put(i, i);
            }
            size = n;
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
            int fa_x = compressed_find(x);
            int fa_y = compressed_find(y);
            if (fa_x != fa_y){
                father.put(fa_x, fa_y);
                size--;
            }
        }

        int getSize (){
            return size;
        }
    }
    
    public boolean validTree(int n, int[][] edges) {
        // write your code here
        if (edges.length != n - 1){
            return false;
        }
        
        UnionFind uf = new UnionFind(n);
        
        for (int i = 0; i < edges.length; i++){
            uf.union(edges[i][0], edges[i][1]);
        }
        
        return uf.getSize() == 1;
    }
}
