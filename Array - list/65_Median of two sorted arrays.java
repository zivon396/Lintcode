//version 1: binary sort exception
public class Solution {
    /*
     * @param A: An integer array
     * @param B: An integer array
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        // write your code here
        int size = A.length + B.length;
        
        if (size % 2 == 1){
            return 1.0 * findKth(A, B, size / 2 + 1);
        }
        
        return findKth(A, B, size / 2) / 2.0 + findKth(A, B, size / 2 + 1) / 2.0;
    }
    
    private int findKth (int[] A, int[] B, int k){
        if (A.length == 0){
            return B[k - 1];
        }
        if (B.length == 0){
            return A[k - 1];
        }
        
        int start = Math.min(A[0], B[0]);
        int end = Math.max(A[A.length - 1], B[B.length - 1]);
        
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (get_leq(A, mid) + get_leq(B, mid) >= k){
                end = mid;
            } else {
                start = mid;
            }
        }
        
        if (get_leq(A, start) + get_leq(B, start) == k){
            return start;
        }
        
        return end;
    }
    
    private int get_leq (int[] nums, int target){
        int start = 0, end = nums.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target){
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (nums[end] <= target){
            return end + 1;
        }
        if (nums[start] <= target){
            return start + 1;
        }
        return 0;
    }
}

//version 2: divide & conquer(每次移走1/2)
public class Solution {
    /*
     * @param A: An integer array
     * @param B: An integer array
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        // write your code here
        int n = A.length + B.length;
        
        if (n % 2 == 1){
            return 1.0 * findKth(A, 0, B, 0, n / 2 + 1);
        }
        
        return findKth(A, 0, B, 0, n / 2) / 2.0 + findKth(A, 0, B, 0, n / 2 + 1) / 2.0;
    }
    
    private int findKth (int[] A, int startA, int[] B, int startB, int k){
        if (startA >= A.length){
            return B[startB + k - 1];
        }
        if (startB >= B.length){
            return A[startA + k - 1];
        }
        
        if (k == 1){
            return Math.min(A[startA], B[startB]);
        }
        
        int halfKthA = startA + k / 2 - 1 < A.length ? A[startA + k / 2 - 1] : Integer.MAX_VALUE;
        int halfKthB = startB + k / 2 - 1 < B.length ? B[startB + k / 2 - 1] : Integer.MAX_VALUE;
        if (halfKthA < halfKthB){
            return findKth(A, startA + k / 2, B, startB, k - k / 2);
        } else {
            return findKth(A, startA, B, startB + k / 2, k - k / 2);
        }
    }
}
