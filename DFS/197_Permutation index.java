// 脑筋急转弯
public class Solution {
    /**
     * @param A: An array of integers
     * @return: A long integer
     */
    public long permutationIndex(int[] A) {
        int n = A.length;
        long factorial = 1;
        long sum = 1;
        for (int i = n - 1; i >= 0; i--) {
            int smaller = 0;
            // 求出剩余可选择元素中小于自身的元素个数
            for (int j = i + 1; j < n; j++) {
                if(A[j] < A[i]){
                    smaller++;
                } 
            }
            sum += smaller * factorial;
            factorial *= (n - i);
        }
        return sum;
    }
}
