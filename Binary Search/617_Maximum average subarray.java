// 二分出 average 之后, 把数组中的每个数都减去 average, 然后的任务就是去求这个数组中, 是否有长度 >= k 的 subarray, 它的和超过 0
// 后面一步用类似 41 的方法
// 时间复杂度 N + N * log((Max - Min)/0.00001)
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
        // 这里写成 41 的格式也是可以的
        // for (i = K - 1; i < A.length; ++i) {
        //     rightSum += A[i] - avg;
        //     if (rightSum - minLeftSum >= 0) {
        //         return true;
        //     }
        //     leftSum += A[i - K + 1] - avg;
        //     minLeftSum = Math.min(minLeftSum, leftSum);
        // }
        
        return false;
    } 
     
    public double maxAverage(int[] A, int K) {
        int i;
        double start, stop, mid;
        start = stop = A[0];
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
