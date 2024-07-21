// 纯 DFS 超时
public class Solution {
    /**
     * @param words: a set of words without duplicates
     * @return: all word squares
     *          we will sort your return value in output
     */
    public List<List<String>> wordSquares(String[] words) {
        // write your code here
        List<List<String>> res = new ArrayList<>();
        int size = words[0].length();

        List<String> per = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        dfs(words, size, per, visited, res);

        return res;
    }

    private void dfs (String[] words,
                      int size,
                      List<String> per,
                      Set<String> visited,
                      List<List<String>> res){
        if (per.size() == size){
            if (isSquare(per)){
                res.add(new ArrayList<>(per));
            }
            return;
        }

        for (int i = 0; i < words.length; i++){
            per.add(words[i]);
            visited.add(words[i]);
            dfs(words, size, per, visited, res);
            visited.remove(words[i]);
            per.remove(per.size() - 1);
        }
    }

    private boolean isSquare (List<String> per){
        for (int i = 0; i < per.size(); i++){
            for (int j = i; j < per.size(); j++){
                if (per.get(i).charAt(j) != per.get(j).charAt(i)){
                    return false;
                }
            }
        }

        return true;
    }
}
