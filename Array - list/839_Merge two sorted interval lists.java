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
// last 是 merge 之后的, curt 是 merge 之前的
// curt 永远取 start 小的那个
public class Solution {
    /**
     * @param list1: one of the given list
     * @param list2: another list
     * @return: the new sorted list of interval
     */
    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        // write your code here
        if (list1 == null || list1.size() == 0){
            return list2;
        }
        if (list2 == null || list2.size() == 0){
            return list1;
        }
        List<Interval> res = new ArrayList<>();
        
        int i = 0, j = 0;
        Interval last = null, curt = null;
        while (i < list1.size() && j < list2.size()){
            if (list1.get(i).start < list2.get(j).start){
                curt = list1.get(i++);
            } else {
                curt = list2.get(j++);
            }
            
            last = merge(res, last, curt);
        }
        
        while (i < list1.size()){
            last = merge(res, last, list1.get(i++));
        }
        while (j < list2.size()){
            last = merge(res, last, list2.get(j++));
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

// 根本就不用 last
public class Solution {
    /**
     * @param list1: one of the given list
     * @param list2: another list
     * @return: the new sorted list of interval
     */
    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        // write your code here
        List<Interval> res = new ArrayList<>();

        int n = list1.size(), m = list2.size();
        int i = 0, j = 0;
        while (i < n && j < m){
            if (list1.get(i).start < list2.get(j).start){
                addRes(res, list1.get(i));
                i++;
            }
            else {
                addRes(res, list2.get(j));
                j++;
            }
        }

        while (i < n){
            addRes(res, list1.get(i));
            i++;
        }
        while (j < m){
            addRes(res, list2.get(j));
            j++;
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
