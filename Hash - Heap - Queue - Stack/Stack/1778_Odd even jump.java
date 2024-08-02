// Monotonic Stack + DP
// 时间复杂度 O(nlog(n)) -> 排序, 空间复杂度 O(n)
class Pair {
    int index, value;
    Pair(int index, int value) {
        this.index = index;
        this.value = value;
    }
}

public class Solution {
    public int oddEvenJumps(int[] A) {
        // change A into value index pair.
        Pair[] pairs = new Pair[A.length];
        for (int index = 0; index < A.length; index++) {
            pairs[index] = new Pair(index, A[index]);
        }
        
        sortPairs(pairs, -1);
        int[] oddJump = getJump(pairs);
        
        sortPairs(pairs, 1);
        int[] evenJump = getJump(pairs);
        
        // dp = array of n x 2
        // dp[i][0] can we jump to n - 1 when we arrive i by even jumps
        // dp[i][1] can we jump to n - 1 when we arrive i by odd jumps
        int n = A.length;
        boolean[][] dp = new boolean[n][2];
        for (int i = 0; i < n; i++) {
            dp[i][0] = false;
            dp[i][1] = false;
        }
        
        dp[n - 1][0] = dp[n - 1][1] = true;
        
        int answer = 1;
        for (int i = n - 2; i >= 0; i--) {
            dp[i][0] = oddJump[i] != -1 ? dp[oddJump[i]][1] : false;
            dp[i][1] = evenJump[i] != -1 ? dp[evenJump[i]][0] : false;
            if (dp[i][0] == true) {
                answer++;
            }
        }

        return answer;
    }
    
    // Odd: 15 14 13 12 10
    //       5  4  2  3  1
    // Even: 10 12 13 14 15
    //        1  3  2  4  5
    private int[] getJump(Pair[] pairs) {
        int[] jump = new int[pairs.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < pairs.length; i++) {
            int index = pairs[i].index;
            int value = pairs[i].value;
            monoDescPush(stack, index);
            if (stack.size() > 1) {
                jump[index] = stack.get(stack.size() - 2);
            } else {
                jump[index] = -1;
            }
        }
        return jump;
    }
    
    private void sortPairs(Pair[] pairs, int order) {
        Arrays.sort(pairs, new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                if (a.value == b.value) {
                    // for the same value, sort index in desc order.
                    return b.index - a.index;
                }
                return order * (a.value - b.value);
            }
        });
    }
    
    private void monoDescPush(Stack<Integer> stack, int value) {
        // 按 index 单调递减入栈
        while (!stack.isEmpty() && stack.peek() <= value) {
            stack.pop();
        }
        stack.add(value);
    }
}


// TreeMap + DP
// dp[i][0] 既表示以 even jump 到达 i 时能否最终抵达 n - 1, 也表示以 i 为起点能否最终抵达 n - 1
// 倒着做 dp, 可以保证每次都只在 i 的后面找满足条件的元素
// 时间复杂度 < O(nlog(n)) -> n 次 查询 + 写入, 空间复杂度 O(n)
public class Solution {
    /**
     * @param A: An integer array A
     * @return: Return the number of good starting indexes
     */
    public int oddEvenJumps(int[] A) {
        // dp = array of n x 2
        // dp[i][0] can we jump to n - 1 when we arrive i by even jumps
        // dp[i][1] can we jump to n - 1 when we arrive i by odd jumps
        int n = A.length;
        boolean[][] dp = new boolean[n][2];
        
        for (int i = 0; i < n; i++) {
            dp[i][0] = false;
            dp[i][1] = false;
        }
        dp[n - 1][0] = dp[n - 1][1] = true;
        
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(A[n - 1], n - 1);
        
        int answer = 1;
        for (int i = n - 2; i >= 0; i--) {
            Map.Entry oddJump = treeMap.ceilingEntry(A[i]);
            Map.Entry evenJump = treeMap.floorEntry(A[i]);
            
            if (oddJump != null){
                dp[i][0] = dp[(int)oddJump.getValue()][1];
            }
            if (evenJump != null){
                dp[i][1] = dp[(int)evenJump.getValue()][0];
            }
            if (dp[i][0]){
                answer++;
            }
            
            treeMap.put(A[i], i);
        }

        return answer;
    }
}
