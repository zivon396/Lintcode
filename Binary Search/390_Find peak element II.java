// 每次迭代找到行/列最中间的位置, 并找到当前十字中的最大值, 留下四分之一
// 每次用 O(n + m) 的时间把问题转化为 O((n + m) / 2) 的问题, 时间复杂度 O(n + m)
class Solution {
    /**
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> find(int x1, int x2, int y1, int y2,
                              int[][] A) {
        
        int mid1 = x1 + (x2 - x1) / 2;
        int mid2 = y1 + (y2 - y1) / 2;
        
        int x = mid1, y = mid2;
        int max = A[mid1][mid2];
        for (int i = y1; i <= y2; ++i)
            if (A[mid1][i] > max) {
                max = A[mid1][i];
                x = mid1;
                y = i;
            }

        for (int i = x1; i <= x2; ++i)
            if (A[i][mid2] > max) {
                max = A[i][mid2];
                x = i;
                y = mid2;
            }
        
        boolean isPeak = true;
        if (A[x - 1][y] > A[x][y]) {
            isPeak = false;
            x -= 1;
        } else if (A[x + 1][y] > A[x][y]) {
            isPeak = false;
            x += 1;
        } else if (A[x][y - 1] > A[x][y]) {
            isPeak = false;
            y -= 1;
        } else if (A[x][y + 1] > A[x][y]) {
            isPeak = false;
            y += 1;
        }
           
        if (isPeak) {
            return new ArrayList<Integer>(Arrays.asList(x, y));
        }
        
        if (x >= x1 && x < mid1 && y >= y1 && y < mid2) {
            return find(x1, mid1 - 1, y1, mid2 - 1, A);
        }
        
        if (x >= x1 && x < mid1 && y > mid2 && y <= y2) {
            return find(x1, mid1 - 1, mid2 + 1, y2, A);
        }
        
        if (x > mid1 && x <= x2 && y >= y1 && y < mid2) {
            return find(mid1 + 1, x2, y1, mid2 - 1, A);
        }
        
        if (x >= mid1 && x <= x2 && y > mid2 && y <= y2) {
            return find(mid1 + 1, x2, mid2 + 1, y2, A);
        }
        
        return new ArrayList<Integer>(Arrays.asList(-1, -1));
        
    }
    public List<Integer> findPeakII(int[][] A) {
        // write your code here
        int n = A.length;
        int m = A[0].length;
        return find(1, n - 2, 1, m - 2, A);
    }
}
