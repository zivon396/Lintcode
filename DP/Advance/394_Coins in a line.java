public class Solution {
    /**
     * @param n: An integer
     * @return: A boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        // write your code here
        if (n <= 0) {
            return false;
        }
        boolean dp[] = new boolean[2];
        dp[1] = true;
        
        for (int i = 2; i < n + 1; i++) {
            dp[i % 2] = !(dp[(i - 1) % 2] && dp[(i - 2) % 2]);
        }
        return dp[n % 2];
    }
}
