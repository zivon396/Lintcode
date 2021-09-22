// 注意 isValid 的条件
public class Solution {
    /*
     * @param n: The number of queens
     * @return: All distinct solutions
     */
    public List<List<String>> solveNQueens(int n) {
        // write your code here
        List<List<String>> res = new ArrayList<>();
        if (n < 1){
            res.add(new ArrayList<>());
            return res;
        }
        
        List<Integer> per = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        helper(n, per, visited, res);
        
        return res;
    }
    
    private void helper (int n,
                         List<Integer> per,
                         Set<Integer> visited,
                         List<List<String>> res){
        if (per.size() == n){
            List<String> rst = construct(per);
            res.add(new ArrayList(rst));
            return;
        }
        
        for (int i = 1; i <= n; i++){
            if (visited.contains(i)){
                continue;
            }
            if (isValid(per, i)){
                per.add(i);
                visited.add(i);
                helper(n, per, visited, res);
                visited.remove(i);
                per.remove(per.size() - 1);
            }
        }
    }
    
    private boolean isValid (List<Integer> per, int i){
        int row = per.size();
        for (int rowIndex = 0; rowIndex < row; rowIndex++){
            if (rowIndex + per.get(rowIndex) == row + i){
                return false;
            }
            if (rowIndex - per.get(rowIndex) == row - i){
                return false;
            }
        }
        return true;
    }
    
    private List<String> construct (List<Integer> per){
        List<String> res = new ArrayList<>();
        for (int i = 0; i < per.size(); i++){
            String queen = "";
            for (int j = 0; j < per.size(); j++){
                if (j != per.get(i) - 1){
                    queen += ".";
                } else {
                    queen += "Q";
                }
            }
            res.add(new String(queen));
        }
        
        return res;
    }
}
