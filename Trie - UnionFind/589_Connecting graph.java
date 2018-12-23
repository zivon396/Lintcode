public class ConnectingGraph {
    /*
    * @param n: An integer
    */
    private int[] father = null;
    
    public ConnectingGraph(int n) {
        // do intialization if necessary
        father = new int[n + 1];
        for (int i = 1; i <= n; i++){
            father[i] = i;
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
        if (root_a != root_b){
            father[root_a] = root_b;
        }
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: A boolean
     */
    public boolean query(int a, int b) {
        // write your code here
        int root_a = find(a);
        int root_b = find(b);
        return root_a == root_b;
    }
}