// TreeMap + DP
// dp[i][0] 既表示以 even jump 到达 i 时能否最终抵达 n - 1, 也表示以 i 为起点能否最终抵达 n - 1
// 倒着做 dp, 可以保证每次都只在 i 的后面找满足条件的元素
public class Solution {
    /**
     * @param A: An integer array A
     * @return: Return the number of good starting indexes
     */
    public int oddEvenJumps(int[] A) {
        // dp = array of n x 2
        // dp[i][0] can we jump to n - 1 when we arrive i by even jumps
        // dp[i][1] can we jump to n - 1 when we arrive i by odd jumps
        int n = A.length;
        boolean[][] dp = new boolean[n][2];
        
        for (int i = 0; i < n; i++) {
            dp[i][0] = false;
            dp[i][1] = false;
        }
        dp[n - 1][0] = dp[n - 1][1] = true;
        
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(A[n - 1], n - 1);
        
        int answer = 1;
        for (int i = n - 2; i >= 0; i--) {
            Map.Entry oddJump = treeMap.ceilingEntry(A[i]);
            Map.Entry evenJump = treeMap.floorEntry(A[i]);
            
            if (oddJump != null){
                dp[i][0] = dp[(int)oddJump.getValue()][1];
            }
            if (evenJump != null){
                dp[i][1] = dp[(int)evenJump.getValue()][0];
            }
            if (dp[i][0]){
                answer++;
            }
            
            treeMap.put(A[i], i);
        }

        return answer;
    }
}
