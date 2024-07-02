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
// 基本就是 Merge k sorted arrays 的 interval 版
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

// 不用 last
class Element {
    public int x, y;
    public Interval interval;
    public Element (int x, int y, Interval interval){
        this.x = x;
        this.y = y;
        this.interval = interval;
    }
}

public class Solution {
    /**
     * @param intervals: the given k sorted interval lists
     * @return:  the new sorted interval list
     */
    Comparator<Element> comparator = new Comparator<Element>(){
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
        int n = intervals.size();
        Queue<Element> heap = new PriorityQueue<>(n, comparator);

        for (int i = 0; i < n; i++){
            if (intervals.get(i).size() == 0){
                continue;
            }
            heap.offer(new Element(
                i,
                0,
                intervals.get(i).get(0)
            ));
        }

        while (!heap.isEmpty()){
            Element curt = heap.poll();
            addRes(res, curt.interval);
            int x = curt.x, y = curt.y;
            if (y + 1 < intervals.get(x).size()){
                heap.offer(new Element(
                    x,
                    y + 1,
                    intervals.get(x).get(y + 1)
                ));
            }
        }

        return res;
    }

    private void addRes (List<Interval> res, Interval interval){
        if (res.isEmpty()){
            res.add(interval);
        }

        int end = interval.end;
        Interval pre = res.get(res.size() - 1);
        if (interval.start <= pre.end){
            end = Math.max(end, pre.end);
            res.set(res.size() - 1, new Interval(pre.start, end));
            return;
        }
        else {
            res.add(interval);
        }

        return;
    }
}
