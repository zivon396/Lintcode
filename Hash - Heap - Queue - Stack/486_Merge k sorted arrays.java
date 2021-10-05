// 注意需要 Element class, 因为需要确定当前 heap poll 出来的元素在原 array 中的位置.
class Element {
    public int row, col;
    public int val;
    public Element (int row, int col, int val){
        this.row = row;
        this.col = col;
        this.val = val;
    }
}

public class Solution {
    /**
     * @param arrays: k sorted integer arrays
     * @return: a sorted array
     */
    private Comparator<Element> eleCom = new Comparator<Element>(){
        public int compare (Element left, Element right){
            return left.val - right.val;
        }
    };
    
    public int[] mergekSortedArrays(int[][] arrays) {
        // write your code here
        if (arrays == null || arrays.length == 0){
            return new int[1];
        }
        
        int size = 0;
        
        Queue<Element> heap = new PriorityQueue<Element>(arrays.length, eleCom);
        for (int i = 0; i < arrays.length; i++){
            if (arrays[i] != null && arrays[i].length != 0){
                heap.add(new Element(i, 0, arrays[i][0]));
                size += arrays[i].length;
            }
        }
        int[] res = new int[size];
        
        int index = 0;
        while (!heap.isEmpty()){
            Element ele = heap.poll();
            res[index++] = ele.val;
            if (ele.col + 1 < arrays[ele.row].length){
                Element next = new Element (
                    ele.row,
                    ele.col + 1,
                    arrays[ele.row][ele.col + 1]
                );
                heap.add(next);
            }
        }
        
        return res;
    }
}
