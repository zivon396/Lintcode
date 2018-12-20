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
     * @param arrs: the arrays
     * @return: the number of the intersection of the arrays
     */
    private Comparator<Element> elemCom = new Comparator<Element>(){
        public int compare (Element left, Element right){
            return left.val - right.val;
        }
    };
    
    public int intersectionOfArrays(int[][] nums) {
        // write your code here
        if (nums == null || nums.length == 0){
            return 0;
        }
        
        Queue<Element> heap = new PriorityQueue<Element>(nums.length, elemCom);
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == null || nums[i].length == 0){
                continue;
            }
            Arrays.sort(nums[i]);
            heap.add(new Element(i, 0, nums[i][0]));
        }
        
        int count = 0;
        int last = 0;
        int size = nums.length;
        int sum = 0;
        while (!heap.isEmpty()){
            Element curt = heap.poll();
            if (count == 0 || curt.val != last){
                if (count == size){
                    sum++;
                }
                last = curt.val;
                count = 1;
            } else {
                count++;
            }
            
            if (curt.col + 1 < nums[curt.row].length){
                curt.col ++;
                curt.val = nums[curt.row][curt.col];
                heap.add(curt);
            }
        }
        
        if (count == size){
            sum++;
        }
        
        return sum;
    }
}