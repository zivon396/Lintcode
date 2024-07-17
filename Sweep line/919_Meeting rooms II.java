// 和 391 完全一样
class Point{
    int time;
    int flag;

    Point(int t, int s){
        this.time = t;
        this.flag = s;
    }

    public static Comparator<Point> PointComparator  = new Comparator<Point>(){
        public int compare(Point p1, Point p2){
            if (p1.time == p2.time){
                return p1.flag - p2.flag;
            }
            else {
                return p1.time - p2.time;
            }
        }
    };
}

public class Solution {
    public int minMeetingRooms(List<Interval> intervals) {
        List<Point> list = new ArrayList<>(intervals.size() * 2);
        for(Interval i : intervals){
            list.add(new Point(i.start, 1));
            list.add(new Point(i.end, 0));
        }
    
        Collections.sort(list, Point.PointComparator);
        int count = 0, ans = 0;
        for (Point p : list){
            if(p.flag == 1) {
                count++;
            }
            else {
                count--;
            }
            ans = Math.max(ans, count);
        }
    
        return ans;
    }
}

// version 2:
public class Solution {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: the minimum number of conference rooms required
     */
    static class Node {
        public int time;
        public int cost;

        // 时间，开始时间cost为1，结束时间cost为-1
        public Node(int time, int cost) {
            this.time = time;
            this.cost = cost;
        }
    }
    //先按照时间升序，再按照cost升序排序
    static Comparator<Node> cNode = new Comparator<Node>() {
        public int compare(Node o1, Node o2) {
            if (o1.time != o2.time) {
                return o1.time - o2.time;
            }
            return o1.cost - o2.cost;
        }
    };

    public int minMeetingRooms(List<Interval> intervals) {
        //扫描线数组
        List<Node>room = new ArrayList<Node>();
        for(int i = 0; i < intervals.size(); i++) {
            room.add(new Node(intervals.get(i).start, 1));
            room.add(new Node(intervals.get(i).end, -1));
        }
        //排序
        Collections.sort(room, cNode);
        int ans = 0;
        int tmp = 0;
        for (int i = 0; i < room.size(); i++) {
            tmp += room.get(i).cost;
            ans = Math.max(ans, tmp);
        }

        return ans;
    }
}
