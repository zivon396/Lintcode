// 一棵树由根节点, 左子树和右子树构成. 对于目标 n, 根节点可以是 1, 2, ..., n 中的任意一个
// 假设根节点为k, 那么左子树的可能性就是 numTrees(k-1) 种, 右子树的可能性就是 numTrees(n-k) 种, 他们的乘积就根节点为k时整个树的可能性
public class Solution {
    /**
     * @param n: An integer
     * @return: An integer
     */
    public int numTrees(int n) {
        return sum(1, n);
    }

    private int sum(int left,int right){
        if (right <= left) return 1;
        int sum = 0;

        for (int i = left ; i <= right ; i++) {
            sum += sum(left, i - 1) * sum(i + 1, right);
        }

        return sum;
    }
}

// DP
public class Solution {
    /*
    The case for 3 elements example
    Count[3] = Count[0]*Count[2]  (1 as root)
                + Count[1]*Count[1]  (2 as root)
                + Count[2]*Count[0]  (3 as root)

    Therefore, we can get the equation:
    Count[i] = ∑ Count[0...k] * [ k+1....i]     0<=k<i-1  

    */
    public int numTrees(int n) {
        int[] count = new int[n + 2];
        count[0] = 1;
        count[1] = 1;
        
        for(int i = 2; i <= n; i++){
            for(int j = 0; j < i; j++){
                count[i] += count[j] * count[i - j - 1];
            }
        }

        return count[n];
    }
}
