public class ConnectingGraph2 {
    /*
    * @param n: An integer
    */
    private int[] father = null;
    private int[] size = null;
    public ConnectingGraph2(int n) {
        // do intialization if necessary
        father = new int[n + 1];
        size = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            father[i] = i;
            size[i] = 1;
        }
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    
    public int find (int x){
        if (father[x] == x){
            return x;
        }
        return father[x] = find(father[x]);
    }
    
    public void connect(int a, int b) {
        // write your code here
        int root_a = find(a);
        int root_b = find(b);
        if (root_a != root_b) {
            father[root_a] = root_b;
            size[root_b] += size[root_a];
        }
    }

    /*
     * @param a: An integer
     * @return: An integer
     */
    public int query(int a) {
        // write your code here
        int root_a = find(a);
        return size[root_a];
    }
}