// 将起飞时间和降落时间放到同一个数组中, 标识出是起飞还是降落时间, 然后对数组排序
// 遍历数组即可, 碰到起飞计数器加一, 碰到降落计数器减一. 维护最大值作为答案
// 降落优先于起飞
class Point{
    int time;
    int flag;

    Point(int t, int s) {
        this.time = t;
        this.flag = s;
    }
    public static Comparator<Point> PointComparator = new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
            if(p1.time == p2.time) 
                return p1.flag - p2.flag;
            else 
                return p1.time - p2.time;
      }
    };
}
  
class Solution {
    /**
     * @param intervals: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {
        if (airplanes == null || airplanes.size() == 0){
            return 0;
        }
        List<Point> list = new ArrayList<>(airplanes.size() * 2);
        for (Interval i : airplanes) {
            list.add(new Point(i.start, 1));
            list.add(new Point(i.end, 0));
        }

        Collections.sort(list, Point.PointComparator);
        int count = 0, ans = 1;
        for (Point p : list) {
            if(p.flag == 1) 
                count++;
            else 
                count--;
            ans = Math.max(ans, count);
        }

        return ans;
    }
}
