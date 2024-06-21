public class Solution {
    /*
     * @param s: a string
     * @param dict: a set of n substrings
     * @return: the minimum length
     */
    // Set 里存 str, str 的 neighbor 就是去掉一个 sub 之后的 next.
    // 要再加一层 for 循环来遍历所有 sub
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
                    index = str.indexOf(sub, index + 1); // 注意这里必须是 + 1 不能是 + size
                }
            }
        }
        
        return minLen; // 必须有这个 minLen 来实时记录最小值, 不能直接 return str.length() -> 因为 degree 最大不代表字符最短!
    }
}
