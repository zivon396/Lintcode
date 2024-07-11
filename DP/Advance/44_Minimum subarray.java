// 同 41
public class Solution {
    /*
     * @param nums: a list of integers
     * @return: A integer indicate the sum of minimum subarray
     */
    public int minSubArray(List<Integer> nums) {
        // write your code here
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        
        int ans = Integer.MAX_VALUE, maxSum = 0, sum = 0;
        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i);
            ans = Math.min(ans, sum - maxSum);
            maxSum = Math.max(maxSum, sum);
        }
        
        return ans;
    }
}

// globalMin + localMin:
public class Solution {
    /*
     * @param nums: a list of integers
     * @return: A integer indicate the sum of minimum subarray
     */
    public int minSubArray(List<Integer> nums) {
        // write your code here
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        int n = nums.size();
        int []localMin = new int[n]; 
        int []golbalMin = new int[n];
        localMin[0] = golbalMin[0] = nums.get(0);
        for(int i = 1; i < n; i++){
            localMin[i] = Math.min(localMin[i - 1] + nums.get(i), nums.get(i)); //比较判断是否从一个新的元素开始找SubArray
            golbalMin[i] = Math.min(golbalMin[i - 1], localMin[i]);// 前 i 位元素可以构成的最小subArray之和
        }
        
        return golbalMin[n - 1];
    }
}
