// O(n) Two pointers
public class Solution {
    /**
     * @param A:     An integer array
     * @param start: An integer
     * @param end:   An integer
     * @return: the number of possible answer
     */
    public int subarraySumII(int[] A, int start, int end) {
        int n = A.length;
        if (n == 0) {
            return 0;
        }

        int[] sum = new int[n + 1];
        int i, l, r, res = 0;
        sum[0] = 0;
        for (i = 1; i <= n; ++i) {
            sum[i] = sum[i - 1] + A[i - 1];
        }

        l = r = 0;
        for (i = 1; i <= n; ++i) {
            while (l < i && sum[i] - sum[l] > end) {
                ++l;
            }

            while (r < i && sum[i] - sum[r] >= start) {
                ++r;
            }

            res += r - l;
        }

        return res;
    }
}

// O(nlogn) 二分法版本
public class Solution {
    /**
     * @param A: An integer array
     * @param start: An integer
     * @param end: An integer
     * @return: the number of possible answer
     */
    int find(int[] A, int len, int value) {
        int start = 0, end = len - 1;
        if (end < 0){
            return 0;
        }

        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (A[mid] < value){
                start = mid;
            } else {
                end = mid;
            }
        }

        if (A[start] >= value){
            return start;
        }
        if (A[end] >= value){
            return end;
        }

        return len;
    }

    public int subarraySumII(int[] A, int start, int end) {
        // Write your code here
        int len = A.length;
        for (int i = 1; i < len; ++i){
            A[i] += A[i - 1];
        }

        int cnt = 0;
        for (int i = 0; i < len; ++i) {
            if (A[i] >= start && A[i] <= end)
                cnt ++;
            int l = A[i] - end;
            int r = A[i] - start;
            cnt += find(A, i, r + 1) - find(A, i, l); 
        }

        return cnt;
    }
}
