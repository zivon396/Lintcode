// Dijkestra 算法


// 使用 Queue, 并不是严格的 Dijkestra
// 并没有每次找到最小的并且加入到 visited 里, 但也能行得通
// 此算法保证了不会进入死循环 (无限加入 Queue), 但同一个节点有可能重复入 Queue
// 时间复杂度 O(n^2 + m)  m => times 的长度
// 空间复杂度 O(n^2)
public class Solution {
    /**
     * @param times: a 2D array
     * @param N: an integer
     * @param K: an integer
     * @return: how long will it take for all nodes to receive the signal
     */
    public int networkDelayTime(int[][] times, int N, int K) {
        // Write your code here
        int[][] table = new int[N + 1][N + 1];
        for (int[] elem: table){
            Arrays.fill(elem, Integer.MAX_VALUE);
        }
        for (int[] elem: times){
            table[elem[0]][elem[1]] = elem[2];
        }
        
        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;
        queue.offer(K);
        
        while (!queue.isEmpty()){
            int curt = queue.poll();
            for (int i = 1; i <= N; i++){
                if (table[curt][i] < Integer.MAX_VALUE){
                    // 注意小于才加到 queue 里, 而且此时必须加到 queue 里, 因为会影响 i 的后续距离计算
                    if (table[curt][i] + dist[curt] < dist[i]){
                        dist[i] = table[curt][i] + dist[curt];
                        queue.offer(i);
                    }
                }
            }
        };
        
        int max = 0;
        for (int i = 1; i <= N; i++){
            if (dist[i] == Integer.MAX_VALUE){
                return -1;
            } else {
                max = Math.max(max, dist[i]);
            }
        }
        
        return max;
    }
}
// One test data: [[1,2,2],[2,1,2],[1,3,5],[3,1,5],[2,3,2],[3,2,2],[2,4,6],[4,2,6],[4,7,7],[7,4,7],[3,5,4],[5,3,4],[3,6,1],[6,3,1],[5,7,3],[7,5,3],[6,7,8],[7,6,8]]


// 严格 Dijkestra 算法
// 不使用 Queue, 双循环 (本质上 used[] 代替了 Queue 的作用)
// 时间复杂度 O(n^2 + m)  m => times 的长度
// 空间复杂度 O(n^2)
public class Solution {
    /**
     * @param times: a 2D array
     * @param n: an integer
     * @param k: an integer
     * @return: how long will it take for all nodes to receive the signal
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // Write your code here
        final int INF = Integer.MAX_VALUE / 2;
        int[] dist = new int[n + 1];
        int[][] edges = new int[n + 1][n + 1];
        Arrays.fill(dist, INF);
        dist[k] = 0;
        for (int i = 1; i <= n; i++){
            Arrays.fill(edges[i], INF);
        }
        for (int[] time: times){
            edges[time[0]][time[1]] = time[2];
        }

        boolean[] used = new boolean[n + 1];
        // 总共 n 次, 每次找到最小的 dist, 用对应的节点来更新 dist[]
        for (int i = 1; i <= n; ++i) {
            int next = -1;
            for (int neighbor = 1; neighbor <= n; ++neighbor) {
                if (!used[neighbor] && (next == -1 || dist[neighbor] < dist[next])) {
                    next = neighbor;
                }
            }
            used[next] = true;
            for (int neighbor = 1; neighbor <= n; ++neighbor) {
                dist[neighbor] = Math.min(dist[neighbor], dist[next] + edges[next][neighbor]);
            }
        }
        dist[0] = 0;

        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == INF ? -1 : ans;
    }
}
