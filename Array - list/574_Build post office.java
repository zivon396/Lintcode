// 前缀和算法 => 分别计算出 房子数量/x坐标/y坐标 的前缀和, 再枚举每个邮局的点, 分别计算 4 个方向上的 cost
// 时间复杂度 O(nm)
public class Solution {
    /**
     * @param grid: a 2D grid
     * @return: An integer
     */
    public int get(int[][] prefix_sumx, int[][] prefix_sumy, int [][] prefix_cnt, int lx, int ly, int rx, int ry, int targetx, int targety) {
        int num = prefix_cnt[rx][ry] - prefix_cnt[lx - 1][ry] - prefix_cnt[rx][ly - 1] + prefix_cnt[lx - 1][ly - 1];
        int sumx = prefix_sumx[rx][ry] - prefix_sumx[lx - 1][ry] - prefix_sumx[rx][ly - 1] + prefix_sumx[lx - 1][ly - 1];
        int sumy = prefix_sumy[rx][ry] - prefix_sumy[lx - 1][ry] - prefix_sumy[rx][ly - 1] + prefix_sumy[lx - 1][ly - 1];
        return Math.abs(num * targetx - sumx) + Math.abs(num * targety - sumy);
    }

    public int shortestDistance(int[][] grid) {
        // write your code here
        int n = grid.length;
        int m = grid[0].length;
        int [][] prefix_sumx = new int [n + 1][m + 1];
        int [][] prefix_sumy = new int [n + 1][m + 1];
        int [][] prefix_cnt = new int [n + 1][m + 1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int x = i + 1, y = j + 1; //下标从1开始用
                prefix_cnt[x][y] = grid[i][j]; //grid为1 表示为房子，
                prefix_sumx[x][y] = grid[i][j] * x;
                prefix_sumy[x][y] = grid[i][j] * y; //grid为1 表示为房子
                prefix_cnt[x][y] += prefix_cnt[x - 1][y] + prefix_cnt[x][y - 1] - prefix_cnt[x - 1][y - 1]; //二维前缀和计算
                prefix_sumx[x][y] += prefix_sumx[x - 1][y] + prefix_sumx[x][y - 1] - prefix_sumx[x - 1][y - 1]; //二维前缀和
                prefix_sumy[x][y] += prefix_sumy[x - 1][y] + prefix_sumy[x][y - 1] - prefix_sumy[x - 1][y - 1]; //二维前缀和
            }
        }

        int mx = 1010101010;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 0) { //为空地，可以放邮局
                    int x = i + 1, y = j + 1; //下标从1开始用
                    int ans = 0; //当前这个点的花费

                    ans += get(prefix_sumx, prefix_sumy, prefix_cnt, 1, 1, x, y, x, y);
                    if(ans > mx) break; //剪枝条件

                    if(y < m) {
                        //计算右上角的答案
                        ans += get(prefix_sumx, prefix_sumy, prefix_cnt, 1, y + 1, x, m, x, y);
                    }
                    if(ans > mx) break;

                    if(x < n) {
                        //计算左下角的答案
                        ans += get(prefix_sumx, prefix_sumy, prefix_cnt, x + 1, 1, n, y, x, y);
                    }
                    if(ans > mx)break;

                    if(x < n && y < m) {
                        //计算右下角的答案
                        ans += get(prefix_sumx, prefix_sumy, prefix_cnt, x + 1, y + 1, n, m, x, y);
                    }
                    mx = Math.min(mx, ans);
                }
            }
        }

        return mx == 1010101010 ? -1 : mx;
    }
}
