// 若上下两堵墙可以被连通, 则不可通过; 反之则可以
// 上下两堵墙分别连结到 size + 1 和 size + 2
public class Solution {
    public String drivingProblem(int L, int W, double[][] p) {
        if (L <= 4 || W <= 4) {
            return "no";
        }
        if (p == null || p.length == 0) {
            return "yes";
        }
        int size = p.length;
        Unionfind uf = new Unionfind(size + 2);
        for (int i = 0; i < size; i++) {
            if (p[i][1] <= 5) {
                uf.connect(i, size);
            }
            if (W - p[i][1] <= 5) {
                uf.connect(i, size + 1);
            }
        }
        
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if ((p[i][0] - p[j][0]) * (p[i][0] - p[j][0]) + (p[i][1] - p[j][1]) * (p[i][1] - p[j][1]) <= 36.0) {
                    uf.connect(i, j);
                }
            }
        }
        return (uf.find(size) == uf.find(size + 1)) ? "no" : "yes";
    }
    
    class Unionfind {
        int[] father;
        
        Unionfind(int size) {
            father = new int[size];
            for (int i = 0; i < size; i++) {
                father[i] = i;
            }
        }
        
        int find(int x) {
            if (father[x] == x) {
                return x;
            }
            return father[x] = find(father[x]);
        }
        
        void connect(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx != fy) {
                father[fy] = fx;
            }
        }
    }
}
