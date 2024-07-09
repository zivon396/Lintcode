public class Solution {
    /**
     * @param nums: an array with positive and negative numbers
     * @param k: an integer
     * @return: the maximum average
     */
    private boolean canFind(int[] A, int K, double avg) {
        int i, k;
        double rightSum = 0, leftSum = 0, minLeftSum = 0;
        for (i = 0; i < K; ++i) {
            rightSum += A[i] - avg;
        }
        
        for (i = K; i <= A.length; ++i) {
            if (rightSum - minLeftSum >= 0) {
                return true;
            }
            
            if (i < A.length) {
                rightSum += A[i] - avg;
                leftSum += A[i - K] - avg;
                minLeftSum = Math.min(minLeftSum, leftSum);
            }
        }
        
        return false;
    } 
     
    public double maxAverage(int[] A, int K) {
        int i;
        double start, stop, mid;
        start = stop = A[0];
        // 如果 A.length 很大的话, 这里不遍历更好, 否则时间复杂度要加上 O(n)
        for (i = 0; i < A.length; ++i) {
            start = Math.min(A[i], start);
            stop = Math.max(A[i], stop);
        }
        
        while (start + 1e-5 < stop) {
            mid = (start + stop) / 2;
            if (canFind(A, K, mid)) {
                start = mid;
            }
            else {
                stop = mid;
            }
        }

        if (canFind(A, K, stop)){
            return stop;
        }
        
        return start;
    }
}
