// 把是否重复的判断放在 isValid 里, 可以省去 set.
public class Solution {
    /**
     * @param n: The number of queens.
     * @return: The total number of distinct solutions.
     */
     
    private int count = 0;
    
    public int totalNQueens(int n) {
        // write your code here
        List<Integer> per = new ArrayList<>();
        
        helper(n, per);
        
        return count;
    }
    
    private void helper (int n, List<Integer> per){
        if (per.size() == n){
            count++;
            return;
        }
        
        for (int i = 1; i <= n; i++){
            if (!isValid(per, i)){
                continue;
            }
            
            per.add(i);
            helper(n, per);
            per.remove(per.size() - 1);
        }
    }
    
    private boolean isValid (List<Integer> per, int i){
        int row = per.size();
        for (int rowIndex = 0; rowIndex < row; rowIndex++){
            if (i == per.get(rowIndex)){
                return false;
            }
            if (per.get(rowIndex) + rowIndex == i + row){
                return false;
            }
            if (per.get(rowIndex) - rowIndex == i - row){
                return false;
            }
        }
        return true;
    }
}
