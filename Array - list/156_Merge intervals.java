public class Solution {
    /**
     * @param intervals: interval list.
     * @return: A new interval list.
     */
    private Comparator<Interval> com = new Comparator<Interval>(){
        public int compare (Interval a, Interval b){
            if (a.start == b.start){
                return a.end - b.end;
            }
            return a.start - b.start;
        }
    };
    public List<Interval> merge(List<Interval> intervals) {
        // write your code here
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0){
            return res;
        }
        Collections.sort(intervals, com);

        for (Interval interval: intervals){
            addRes(res, interval);
        }

        return res;
    }

    private void addRes (List<Interval> res, Interval interval){
        if (res.size() == 0){
            res.add(new Interval(interval.start, interval.end));
        }

        Interval last = res.get(res.size() - 1);
        int end = last.end;
        if (interval.start <= end){
            res.set(res.size() - 1, new Interval(last.start, Math.max(last.end, interval.end)));
        }
        else {
            res.add(new Interval(interval.start, interval.end));
        }
    }
}
