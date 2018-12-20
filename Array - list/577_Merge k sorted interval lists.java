/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */
class Element {
    public int row, col;
    public Interval interval;
    public Element (int row, int col, Interval interval){
        this.row = row;
        this.col = col;
        this.interval = interval;
    }
}

public class Solution {
    /**
     * @param intervals: the given k sorted interval lists
     * @return:  the new sorted interval list
     */
    private Comparator<Element> intervalCom = new Comparator<Element>(){
        public int compare (Element left, Element right){
            return left.interval.start - right.interval.start;
        }
    };
    
    public List<Interval> mergeKSortedIntervalLists(List<List<Interval>> intervals) {
        // write your code here
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0){
            return res;
        }
        
        Queue<Element> heap = new PriorityQueue<Element>(intervals.size(), intervalCom);
        for (int i = 0; i < intervals.size(); i++){
            if (intervals.get(i) == null || intervals.get(i).size() == 0){
                continue;
            }
            heap.add(new Element(i, 0, intervals.get(i).get(0)));
        }
        
        Interval last = null, curt = null;
        while (!heap.isEmpty()){
            Element ele = heap.poll();
            curt = ele.interval;
            last = merge(res, last, curt);
            if (ele.col + 1 < intervals.get(ele.row).size()){
                heap.add(new Element(
                    ele.row,
                    ele.col + 1,
                    intervals.get(ele.row).get(ele.col + 1)
                ));
            }
        }
        res.add(last);
        
        return res;
    }
    
    private Interval merge (List<Interval> res, Interval last, Interval curt){
        if (last == null){
            return curt;
        }
        
        if (curt.start > last.end){
            res.add(last);
            return curt;
        }
        
        last.end = Math.max(last.end, curt.end);
        return last;
    }
}