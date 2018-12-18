public class Solution {
    /*
     * @param s: a string
     * @param dict: a set of n substrings
     * @return: the minimum length
     */
    public int minLength(String s, Set<String> dict) {
        // write your code here
        if (s == null || s.length() == 0){
            return 0;
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        Set<String> set = new HashSet<>();
        set.add(s);
        
        int minLen = s.length();
        while (!queue.isEmpty()){
            String str = queue.poll();
            for (String sub: dict){
                int size = sub.length();
                int index = str.indexOf(sub);
                while (index != -1){
                    String next = str.substring(0, index) + str.substring(index + size, str.length());
                    if (!set.contains(next)){
                        queue.offer(next);
                        set.add(next);
                        minLen = Math.min(minLen, next.length());
                    }
                    index = str.indexOf(sub, index + 1);
                }
            }
        }
        
        return minLen;
    }
}