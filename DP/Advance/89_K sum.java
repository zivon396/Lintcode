// f[i][j][k] => 前 i 个数选 j 个数组成和为 k 的方案总数
public class Solution {
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     */
    public int  kSum(int A[], int k, int target) {
        int n = A.length;
        int[][][] f = new int[n + 1][k + 1][target + 1];
        for (int i = 0; i < n + 1; i++) {
            f[i][0][0] = 1;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k && j <= i; j++) {
                for (int t = 1; t <= target; t++) {
                    f[i][j][t] = 0;
                    if (t >= A[i - 1]) {
                        f[i][j][t] = f[i - 1][j - 1][t - A[i - 1]];
                    }
                    f[i][j][t] += f[i - 1][j][t];
                }
            }
        }

        return f[n][k][target];
    }
}


// 打印所有方案版本
public class Solution {
    /**
     * @param A: An integer array
     * @param k: A positive integer (k <= length(A))
     * @param target: An integer
     * @return: An integer
     */
     
    int[] res;
    int tot;
    int[] A;
    int K;
    int[][][] f;
    
    void printAnswer(int i, int j, int k) {
        if (j == 0) {
            for (int h = 0; h < K; ++h) {
                System.out.print(res[h]);
                if (h == K - 1) {
                    System.out.println("=" + tot);
                }
                else {
                    System.out.print("+");
                }
            }
            
            return;
        }
        
        if (f[i - 1][j][k] > 0) {
            printAnswer(i - 1, j, k);
        }
        
        if (j > 0 && k >= A[i - 1] && f[i - 1][j - 1][k - A[i - 1]] > 0) {
            res[j - 1] = A[i - 1];
            printAnswer(i - 1, j - 1, k - A[i - 1]);
        }
    }
    
    public int kSum(int[] AA, int KK, int T) {
        K = KK;
        A = AA;
        int n = A.length;
        res = new int[K];
        tot = T;
        f = new int[n + 1][K + 1][T + 1];
        int i, j, k;
        for (j = 0; j <= K; ++j) {
            for (k = 0; k <= T; ++k) {
                f[0][j][k] = 0;
            }
        }
        
        f[0][0][0] = 1;
        for (i = 1; i <= n; ++i) {
            for (j = 0; j <= K; ++j) {
                for (k = 0; k <= T; ++k) {
                    // not using A[i - 1]
                    f[i][j][k] = f[i - 1][j][k];
                    
                    // using A[i - 1]
                    if (j > 0 && k >= A[i - 1]) {
                        f[i][j][k] += f[i - 1][j - 1][k - A[i - 1]];
                    }
                }
            }
        }
        
        printAnswer(n, K, T);
        
        return f[n][K][T];
    }
}
