// 先 BFS, 再倒着 DFS
public class Solution {
    /**
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: a list of lists of string
     *          we will sort your return value in output
     */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // write your code here
        List<List<String>> res = new ArrayList<>();
        dict.add(end);
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);

        List<List<String>> levels = new ArrayList<>();
        levels.add(new ArrayList<>());
        levels.get(0).add(start);
        int steps = 0;
        boolean flag = false;
        while (!queue.isEmpty()){
            int size = queue.size();
            steps++;
            if (flag){
                break;
            }
            for (int i = 0; i < size; i++){
                String curt = queue.poll();
                for (String next: getNextWords(curt, dict)){
                    if (next.equals(end)){
                        flag = true;
                    }
                    if (visited.contains(next)){
                        continue;
                    }
                    queue.offer(next);
                    visited.add(next);
                    if (levels.size() <= steps){
                        levels.add(new ArrayList<>());
                    }
                    levels.get(steps).add(next);
                }
            }
        }

        List<String> path = new ArrayList<>();
        path.add(end);
        getPaths(end, levels.size() - 2, path, levels, res);

        return res;
    }

    private void getPaths(String word,
                          int startIndex,
                          List<String> path,
                          List<List<String>> levels,
                          List<List<String>> res) {
        if (startIndex < 0){
            List<String> tmp = new ArrayList<>(path);
            Collections.reverse(tmp);
            res.add(new ArrayList<>(tmp));
            return;
        }

        List<String> level = levels.get(startIndex);
        for (int i = 0; i < level.size(); i++){
            String next = level.get(i);
            if (onlyOneDiff(word, next)){
                path.add(next);
                getPaths(next, startIndex - 1, path, levels, res);
                path.remove(path.size() - 1);
            }
        }
    }

    private List<String> getNextWords (String word, Set<String> dict){
        List<String> res = new ArrayList<>();
        // 这里遍历 char 更好, 避免 map 太大
        for (String next: dict){
            if (onlyOneDiff(word, next)){
                res.add(next);
            }
        }

        return res;
    }

    private boolean onlyOneDiff (String a, String b){
        if (a.length() != b.length()){
            return false;
        }

        int count = 0;
        for (int i = 0; i < a.length(); i++){
            if (a.charAt(i) != b.charAt(i)){
                count++;
            }
        }

        return count == 1;
    }
}

// 九章答案, 更优
// end - start => BFS
// start - end => DFS
public class Solution {
    public List<List<String>> findLadders(String start, String end,
            Set<String> dict) {
        List<List<String>> ladders = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Map<String, Integer> distance = new HashMap<String, Integer>();

        dict.add(start);
        dict.add(end);
 
        bfs(map, distance, end, start, dict);
        
        List<String> path = new ArrayList<String>();
        
        dfs(ladders, path, start, end, distance, map);

        return ladders;
    }

    void dfs(List<List<String>> ladders, List<String> path, String crt,
            String end, Map<String, Integer> distance,
            Map<String, List<String>> map) {
        path.add(crt);
        if (crt.equals(end)) {
            ladders.add(new ArrayList<String>(path));
        } else {
            for (String next : map.get(crt)) {
                if (distance.containsKey(next) && distance.get(crt) == distance.get(next) + 1) { 
                    dfs(ladders, path, next, end, distance, map);
                }
            }           
        }
        path.remove(path.size() - 1);
    }

    void bfs(Map<String, List<String>> map, Map<String, Integer> distance,
            String start, String end, Set<String> dict) {
        Queue<String> q = new LinkedList<String>();
        q.offer(start);
        distance.put(start, 0);
        for (String s : dict) {
            map.put(s, new ArrayList<String>());
        }
        
        while (!q.isEmpty()) {
            String crt = q.poll();

            List<String> nextList = expand(crt, dict);
            for (String next : nextList) {
                map.get(next).add(crt);
                if (!distance.containsKey(next)) {
                    distance.put(next, distance.get(crt) + 1);
                    q.offer(next);
                }
            }
        }
    }

    List<String> expand(String crt, Set<String> dict) {
        List<String> expansion = new ArrayList<String>();

        for (int i = 0; i < crt.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != crt.charAt(i)) {
                    String expanded = crt.substring(0, i) + ch
                            + crt.substring(i + 1);
                    if (dict.contains(expanded)) {
                        expansion.add(expanded);
                    }
                }
            }
        }

        return expansion;
    }
}
