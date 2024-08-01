// 同 657
public class LoadBalancer {
    private int n = 0;
    private Map<Integer, Integer> hash;
    private List<Integer> list;
    private Random rand;
    public LoadBalancer() {
        // do intialization if necessary
        this.hash = new HashMap<>();
        this.list = new ArrayList<>();
        this.rand = new Random();
    }

    /*
     * @param server_id: add a new server to the cluster
     * @return: nothing
     */
    public void add(int server_id) {
        // write your code here
        if (hash.containsKey(server_id)){
            return;
        }
        
        list.add(server_id);
        hash.put(server_id, list.size() - 1);
    }

    /*
     * @param server_id: server_id remove a bad server from the cluster
     * @return: nothing
     */
    public void remove(int server_id) {
        // write your code here
        if (!hash.containsKey(server_id)){
            return;
        }
        
        int index = hash.get(server_id);
        // 这里不判断也行
        if (index != list.size() - 1){
            int last = list.get(list.size() - 1);
            list.set(index, last);
            hash.put(last, index);
        }
        
        hash.remove(server_id);
        list.remove(list.size() - 1);
    }

    /*
     * @return: pick a server in the cluster randomly with equal probability
     */
    public int pick() {
        // write your code here
        return list.get(rand.nextInt(list.size()));
    }
}
