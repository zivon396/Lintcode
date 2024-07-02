// 注意用 count == 0 来判断是不是 heap 的第一个元素
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
        int last = 0; // 也可以让 last = -1, 这样就可以不用 count == 0 来判断是不是第一个元素了
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

// version 2:
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
    Comparator<Element> comparator = new Comparator<Element>(){
        public int compare (Element left, Element right){
            return left.val - right.val;
        }
    };

    public int intersectionOfArrays(int[][] arrs) {
        // write your code here
        int res = 0;
        if (arrs == null || arrs.length == 0){
            return res;
        }
        int n = arrs.length;
        Queue<Element> heap = new PriorityQueue<>(n, comparator);

        for (int i = 0; i < n; i++){
            if (arrs[i] != null && arrs[i].length > 0){
                Arrays.sort(arrs[i]);
                heap.offer(new Element(
                    i,
                    0,
                    arrs[i][0]
                ));
            }
        }

        int last = -1, curt = 0;
        int count = 1;
        while (!heap.isEmpty()){
            Element ele = heap.poll();
            curt = ele.val;
            if (curt != last){
                last = curt;
                count = 1;
            }
            else {
                if (++count == n){
                    res++;
                }
            }
            if (ele.col + 1 < arrs[ele.row].length){
                heap.offer(new Element(
                    ele.row,
                    ele.col + 1,
                    arrs[ele.row][ele.col + 1]
                ));
            }
        }

        return res;
    }
}
