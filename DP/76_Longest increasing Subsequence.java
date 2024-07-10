public class Solution {
    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;

        // state
        int[] dp = new int[n];
        
        // initialization
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        
        // function
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        // answer
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

// 找出最小的同时, 打印出具体方案:
public class Solution {
    /**
     * @param nums: An integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } 
        int n = nums.length;
        
        // state: 
        // dp[i] 表示从左到右跳到i的最长sequence 的长度
        // prev[i] 代表 dp[i] 的最优值是从哪个 dp[j] 算过来的
        int[] prev = new int[n];
        int[] dp = new int[n];
        
        // initialization: dp[0..n-1] = 1
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            prev[i] = -1;
        }
        
        // function dp[i] = max{dp[j] + 1},  j < i and nums[j] < nums[i]
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
        }
        
        // answer: max(dp[0..n-1])
        int longest = 0, last = -1;
        for (int i = 0; i < n; i++) {
            if (dp[i] > longest) {
                longest = dp[i];
                last = i;
            }
        }
        
        // print solution
        ArrayList<Integer> path = new ArrayList();
        while (last != -1) {
            path.add(nums[last]);
            last = prev[last];
        }
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + "-");
        }
        
        return longest;
    }
}

// O(nlogn) Binary Search
public class Solution {
    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        int[] minLast = new int[nums.length + 1];
        minLast[0] = Integer.MIN_VALUE;
        for (int i = 1; i <= nums.length; i++) {
            minLast[i] = Integer.MAX_VALUE;
        }
        
        for (int i = 0; i < nums.length; i++) {
            // find the first number in minLast >= nums[i]
            int index = binarySearch(minLast, nums[i]);
            minLast[index] = nums[i];
        }
        
        for (int i = nums.length; i >= 1; i--) {
            if (minLast[i] != Integer.MAX_VALUE) {
                return i;
            }
        }
        
        return 0;
    }
    
    // find the first number >= num
    private int binarySearch(int[] minLast, int num) {
        int start = 0, end = minLast.length - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (minLast[mid] < num) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (minLast[start] >= num){
            return start;
        }
        
        return end;
    }
}
