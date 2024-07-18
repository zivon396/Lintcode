public class Solution {
    /**
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: An integer
     */
    public int ladderLength(String start, String end, Set<String> dict) {
        // write your code here
        dict.add(end);
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);

        int count = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++){
                String curt = queue.poll();
                for (String next: findNextWord(curt, dict)){
                    if (next.equals(end)){
                        return count + 1;
                    }
                    if (!visited.contains(next)){
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }
        }

        return -1;
    }

    private ArrayList<String> findNextWord (String word, Set<String> dict){
        ArrayList<String> res = new ArrayList<>();
        for (String s: dict){
            if (onlyOneCharDiff(word, s)){
                res.add(s);
            }
        }

        return res;
    }

    private boolean onlyOneCharDiff (String s1, String s2){
        if (s1.length() != s2.length()){
            return false;
        }

        int count = 0;
        for (int i = 0; i < s1.length(); i++){
            if (s1.charAt(i) != s2.charAt(i)){
                count++;
            }
        }

        return count == 1;
    }
}
