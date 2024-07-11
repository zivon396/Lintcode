// 纯 DP, 会超时
public class Solution {
    /**
     * @param envelopes: a number of envelopes with widths and heights
     * @return: the maximum number of envelopes
     */
    private Comparator<int[]> arrayCom = new Comparator<int[]>(){
        public int compare (int[] left, int[] right){
            if (left[0] == right[0]){
                return right[1] - left[1];
            }

            return left[0] - right[0];
        }
    };

    public int maxEnvelopes(int[][] envelopes) {
        // write your code here
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0){
            return 0;
        }
        int n = envelopes.length, m = envelopes[0].length;
        Arrays.sort(envelopes, arrayCom);
        int[] dp = new int[n];
        
        dp[0] = 1;
        for (int i = 1; i < n; i++){
            for (int j = 0; j < i; j++){
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++){
            max = Math.max(dp[i], max);
        }

        return max;
    }
}

// DP + Binary Search
// 先将信封在第一个维度上排序, 然后在第二个维度上做 Longest increasing subsequence
// 注意: comparator 的第二个维度必须反着排, 因为第一个维度相同的话是不能嵌套的
