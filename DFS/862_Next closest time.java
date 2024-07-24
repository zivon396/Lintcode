// 自创
public class Solution {
    /**
     * @param time: the given time
     * @return: the next closest time
     */
    private int diff = Integer.MAX_VALUE;
    private String result = "";
    public String nextClosestTime(String time) {
        // write your code here
        Set<Integer> set = new HashSet<>();
        set.add(Integer.parseInt(time.substring(0, 1)));
        set.add(Integer.parseInt(time.substring(1, 2)));
        set.add(Integer.parseInt(time.substring(3, 4)));
        set.add(Integer.parseInt(time.substring(4, 5)));

        if (set.size() == 1){
            return time;
        }

        List<Integer> digits = new ArrayList<>(set);
        int minutes = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
        dfs(digits, "", 0, minutes);

        return result;
    }

    private void dfs (List<Integer> digits, String s, int index, int minutes){
        if (index == 4){
            int hour = Integer.parseInt(s.substring(0, 2));
            int minute = Integer.parseInt(s.substring(2));
            if (hour > 23 || minute > 59){
                return;
            }

            int value = hour * 60 + minute;
            if (value == minutes){
                return;
            }
            int d = value - minutes > 0 ? value - minutes : 1440 + value - minutes;
            if (d < diff) {
                diff = d;
                result = s.substring(0, 2) + ":" + s.substring(2);
            }

            return;
        }

        for (int i = 0; i < digits.size(); i++){
            s += Integer.toString(digits.get(i));
            dfs(digits, s, index + 1, minutes);
            s = s.substring(0, s.length() - 1);
        }
    }
}

// 九章答案, 在进入下一个 dfs 之前就 check 合法性
public class Solution {
    /**
     * @param time: the given time
     * @return: the next closest time
     */
    int diff = Integer.MAX_VALUE;
    String result = "";     
    public String nextClosestTime(String time) {
        // write your code here
        Set<Integer> set = new HashSet<>();
        set.add(Integer.parseInt(time.substring(0, 1)));
        set.add(Integer.parseInt(time.substring(1, 2)));
        set.add(Integer.parseInt(time.substring(3, 4)));
        set.add(Integer.parseInt(time.substring(4, 5)));
        
        if (set.size() == 1) {
            return time;
        }
        
        List<Integer> digits = new ArrayList<>(set);
        int minute = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));

        dfs(digits, "", 0, minute);

        return result;        
    }
    private void dfs(List<Integer> digits, String cur, int pos, int target) {
        if (pos == 4) {
            int m = Integer.parseInt(cur.substring(0, 2)) * 60 + Integer.parseInt(cur.substring(2, 4));
            if (m == target) return;
            int d = m - target > 0 ? m - target : 1440 + m - target;
            if (d < diff) {
                diff = d;
                result = cur.substring(0, 2) + ":" + cur.substring(2, 4);
            }
            return;
        }

        for (int i = 0; i < digits.size(); i++) {
            if (pos == 0 && digits.get(i) > 2) {
                continue;
            }
            if (pos == 1 && Integer.parseInt(cur) * 10 + digits.get(i) > 23) {
                continue;
            }
            if (pos == 2 && digits.get(i) > 5) {
                continue;
            }
            if (pos == 3 && Integer.parseInt(cur.substring(2)) * 10 + digits.get(i) > 59) {
                continue;
            }
            dfs(digits, cur + digits.get(i), pos + 1, target);
        }
    }    
}
