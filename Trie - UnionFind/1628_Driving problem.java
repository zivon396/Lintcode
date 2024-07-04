// 若上下两堵墙可以被连通, 则不可通过; 反之则可以
// 上下两堵墙分别连结到 size + 1 和 size + 2
class UnionFind {
    int[] father;
  
    public UnionFind(int n) {
        father = new int[n];
        for (int i = 0; i < n; i++){
            father[i] = i;
        }
    }

    public int find (int x){
        if (father[x] == x){
            return x;
        }
        return father[x] = find(father[x]);
    }

    public void union(int a, int b) {
        // write your code here
        int root_a = find(a);
        int root_b = find(b);
        if (root_a != root_b){
            father[root_a] = root_b;
        }
    }

    public boolean isConnected(int a, int b) {
        return find(a) == find(b);
    }
}

public class Solution {
    /**
     * @param l: the length
     * @param w: the width
     * @param p:  the obstacle coordinates
     * @return: yes or no
     */
    public String drivingProblem(int l, int w, double[][] p) {
        // Write your code here.
        if (l <= 4 || w <= 4){
            return "no";
        }
        if (p == null || p.length == 0){
            return "yes";
        }
        int size = p.length;
        UnionFind uf = new UnionFind(size + 2);
        for (int i = 0; i < size; i++){
            if (p[i][1] <= 5) {
                uf.union(i, size);
            }
            if (w - p[i][1] <= 5) {
                uf.union(i, size + 1);
            }
        }

        for (int i = 0; i < size; i++){
            for (int j = i + 1; j < size; j++){
                if (getDistance(p[i][0], p[i][1], p[j][0], p[j][1]) <= 6 * 6){
                    uf.union(i, j);
                }
            }
        }

        return uf.isConnected(size, size + 1) ? "no" : "yes";
    }

    private double getDistance (double x1, double y1, double x2, double y2){
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }
}
