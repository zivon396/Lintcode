// 注意先把 String 转换为 chars[] 方便操作 -> 直接用 String 也可以
// 直接用数组 boolean[] visited, 比用 set 往里加 index 好
public class Solution {
    /**
     * @param str: A string
     * @return: all permutations
     */
    public List<String> stringPermutation2(String str) {
        // write your code here
        List<String> res = new ArrayList<>();
        if (str == null || str.length() == 0){
            res.add(new String(""));
            return res;
        }
        
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        
        String pers = "";
        boolean[] visited = new boolean[str.length()];
        helper(chars, pers, visited, res);
        
        return res;
    }
    
    private void helper (char[] chars,
                         String pers,
                         boolean[] visited,
                         List<String> res){
        if (pers.length() == chars.length){
            res.add(new String(pers));
            return;
        }
        
        for (int i = 0; i < chars.length; i++){
            if (i > 0 && !visited[i - 1] && chars[i] == chars[i - 1]){
                continue;
            }
            if (visited[i] == true){
                continue;
            }
            pers += Character.toString(chars[i]);
            visited[i] = true;
            helper(chars, pers, visited, res);
            visited[i] = false;
            pers = pers.substring(0, pers.length() - 1);
        }
    }
}
