// 贪心算法: 只要相邻两天的价格是上升的, 就做一次交易. 这样虽不能保证交易次数最少, 但一定利润最多.
public class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            if (diff > 0) {
                profit += diff;
            }
        }
        return profit;
    }
}
