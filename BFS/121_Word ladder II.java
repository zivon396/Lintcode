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
