// Union Find
class UnionFind {
    private int k;
    private int[] father;
    private int[] size;
    private int count;
    private int min;
    private int groups;

    public UnionFind (int n, int k){
        this.k = k;
        this.father = new int[n];
        this.size = new int[n];
        for (int i = 0; i < n; i++){
            father[i] = i;
            size[i] = 0;
        }
        this.count = 0;
        this.min = 0;
        this.groups = 0;
    }

    public int find (int x){
        if (father[x] == x){
            return x;
        }

        return father[x] = find(father[x]);
    }

    public void connect (int a, int b){
        int root_a = find(a);
        int root_b = find(b);
        if (root_a != root_b){
            if (this.size[root_a] >= k){
                this.groups--;
            }
            if (this.size[root_b] >= k){
                this.groups--;
            }

            father[root_a] = root_b;
            this.size[root_b] += size[root_a];

            this.count--;
            if (this.size[root_b] >= k){
                this.groups++;
            }
        }
    }

    public void setSize (int x){
        this.size[x] = 1;
        if (this.k == 1){
            this.groups++;
        }
    }

    public int getSize (int x){
        return this.size[x];
    }

    public void addCount (){
        this.count++;
    }

    public int getGroups (){
        return this.groups;
    }
}

public class Solution {
    /**
     * @param flowers: an array
     * @param k: an integer
     * @param m: an integer
     * @return: the last day
     */
    public int flowerProblem(int[] flowers, int k, int m) {
        // Write your code here
        int n = flowers.length;
        boolean[] bloom = new boolean[n];
        UnionFind uf = new UnionFind(n, k);
        int max = -1;
        for (int i = 0; i < n; i++){
            int index = flowers[i] - 1;
            uf.setSize(index);
            uf.addCount();
            bloom[index] = true;
            if (index > 0 && bloom[index - 1]){
                uf.connect(index, index - 1);
            }
            if (index < n - 1 && bloom[index + 1]){
                uf.connect(index, index + 1);
            }

            if (uf.getGroups() == m){
                max = i + 1;
            }
        }

        return max;
    }
}

// 这个版本是必须同时有且仅有 m 簇, 每簇 ≥ k
class UnionFind {
    private int[] father;
    private int[] size;
    private TreeSet<Integer> heap;
    private int count;
    private int min;
    private Comparator<Integer> com = new Comparator<Integer>(){
        public int compare (Integer a, Integer b){
            if (size[a] == size[b]){
                return a - b;
            }

            return size[a] - size[b];
        }
    };

    public UnionFind (int n){
        this.father = new int[n];
        this.size = new int[n];
        for (int i = 0; i < n; i++){
            father[i] = i;
            size[i] = 0;
        }
        this.heap = new TreeSet(this.com);
        this.count = 0;
        this.min = 0;
    }

    public int find (int x){
        if (father[x] == x){
            return x;
        }

        return father[x] = find(father[x]);
    }

    public void connect (int a, int b){
        int root_a = find(a);
        int root_b = find(b);
        if (root_a != root_b){
            this.heap.remove(root_a);
            this.heap.remove(root_b);

            father[root_a] = root_b;
            this.size[root_b] += size[root_a];
            this.count--;
            
            this.heap.add(root_b);
        }
    }

    public void setSize (int x){
        this.size[x] = 1;
        this.heap.add(x);
    }

    public int getSize (int x){
        return this.size[x];
    }

    public int getMinSize (){
        return this.size[(int)heap.first()];
    }

    public void addCount (){
        this.count++;
    }

    public int getCount (){
        return this.count;
    }
}

public class Solution {
    /**
     * @param flowers: an array
     * @param k: an integer
     * @param m: an integer
     * @return: the last day
     */
    public int flowerProblem(int[] flowers, int k, int m) {
        // Write your code here
        int n = flowers.length;
        boolean[] bloom = new boolean[n];
        UnionFind uf = new UnionFind(n);
        int max = -1;
        for (int i = 0; i < n; i++){
            int index = flowers[i] - 1;
            uf.setSize(index);
            uf.addCount();
            bloom[index] = true;
            if (index > 0 && bloom[index - 1]){
                uf.connect(index, index - 1);
            }
            if (index < n - 1 && bloom[index + 1]){
                uf.connect(index, index + 1);
            }

            if (uf.getMinSize() >= k && uf.getCount() == m){
                max = i + 1;
            }
        }

        return max;
    }
}
