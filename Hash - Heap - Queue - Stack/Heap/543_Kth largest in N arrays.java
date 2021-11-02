// 时间复杂度 nmlog(m) + nlog(n) + klog(k)
// 或者用 min heap, 逐个放入每个 num, 最后 pop peek.
class Element {
    public int x, y;
    public int val;
    public Element (int x, int y, int val){
        this.x = x;
        this.y = y;
        this.val = val;
    }
}

public class Solution {
    /**
     * @param arrays: a list of array
     * @param k: An integer
     * @return: an integer, K-th largest element in N arrays
     */
    private Comparator<Element> elemCom = new Comparator<Element>(){
        public int compare (Element left, Element right){
            return right.val - left.val;
        }
    };
    
    public int KthInArrays(int[][] arrays, int k) {
        // write your code here
        Queue<Element> heap = new PriorityQueue<Element>(k, elemCom);
        
        for (int i = 0; i < arrays.length; i++){
            if (arrays[i] == null || arrays[i].length == 0){
                continue;
            }
            int len = arrays[i].length;
            Arrays.sort(arrays[i]);
            heap.offer(new Element(i, len - 1, arrays[i][len - 1]));
        }
        
        for (int i = 0; i < k - 1; i++){
            Element curt = heap.poll();
            if (curt.y - 1 >= 0){
                curt.y--;
                curt.val = arrays[curt.x][curt.y];
                heap.add(curt);
            }
        }
        
        return heap.peek().val;
    }
}
