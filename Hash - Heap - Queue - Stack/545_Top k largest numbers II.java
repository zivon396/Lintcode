public class Solution {
    /*
    * @param k: An integer
    */
    private Queue<Integer> heap;
    private int size;
    
    public Solution(int k) {
        // do intialization if necessary
        heap = new PriorityQueue<Integer>();
        size = k;
    }

    /*
     * @param num: Number to be added
     * @return: nothing
     */
    public void add(int num) {
        // write your code here
        heap.add(num);
        if (heap.size() > size){
            heap.poll();
        }
    }

    /*
     * @return: Top k element
     */
    public List<Integer> topk() {
        // write your code here
        Iterator it = heap.iterator();
        List<Integer> res = new ArrayList<>();
        while (it.hasNext()){
            res.add((int)it.next());
        }
        Collections.sort(res, Collections.reverseOrder());
        
        return res;
    }
}