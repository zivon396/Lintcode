// DP, 会超时
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

// Binary Search
// 先将信封在第一个维度上排序, 然后在第二个维度上做 Longest increasing subsequence
// 注意: comparator 的第二个维度必须反着排, 因为第一个维度相同的话是不能嵌套的
// 时间复杂度 nlog(n)
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
        int n = envelopes.length;
        Arrays.sort(envelopes, arrayCom);
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        for (int i = 0; i < n; i++){
            int[] envelope = envelopes[i];
            int index = binarySearch(dp, envelope[1]);
            dp[index] = envelope[1];
        }

        for (int i = n - 1; i >= 0; i--) {
            if (dp[i] != Integer.MAX_VALUE) {
                return i + 1;
            }
        }

        return 0;
    }

    private int binarySearch (int[] dp, int target){
        int start = 0, end = dp.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (dp[mid] >= target){
                end = mid;
            }
            else {
                start = mid;
            }
        }

        if (dp[start] >= target){
            return start;
        }

        return end;
    }
}

// Binary Search version 2:
// 直接利用 Arrays.binarySearch()
public class Solution {
    /**
     * @param envelopes a number of envelopes with widths and heights
     * @return the maximum number of envelopes
     */
    public int maxEnvelopes(int[][] envelopes) {
        // Write your code here
        if(envelopes == null || envelopes.length == 0 
            || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                if(arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
            } 
        });
        int dp[] = new int[envelopes.length];
        int len = 0;
        for(int[] envelope : envelopes){
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
                if(index < 0)
                    index = -index - 1;
            dp[index] = envelope[1];
            if (index == len)
                len++;
        }
        return len;
    }
}
