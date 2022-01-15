package greedy;


public class StockPriceII {
    /**
     * Link:
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
     *
     */

    /**
     * Main idea:
     *   一次只能持有 1 單位, 定義從 i 持有到 j 的最佳利潤為:
     *       Profit[i:j] = prices[j] - prices[i] - Profit[i + 1: j - 1],
     *
     *       max Profit[i:j]
     *
     *       = max {prices[j]} - prices[i] - min {Profit[i + 1 : j - 1]},
     *
     *       如果 Profit[i + 1 : j - 1] < 0, 代表存在 Profit[i+i:k] 使得 Profit[i:j] 不是最佳的
     *
     *       因此 Profit[i:j] 滿足最短遞增序列, max profit 為所有最短遞增序列總和
     *
     */

    public int maxProfit(int[] prices) {
        int len = prices.length;
        int maxPorfit = 0;
        int maxPrice = prices[len - 1];
        for (int i = len - 2; i >= 0; i --) {
            if (prices[i] < maxPrice) {
                maxPorfit += maxPrice - prices[i];
                maxPrice = prices[i];
            } else {
                maxPrice = Math.max(maxPrice, prices[i]);
            }
        }
        return maxPorfit;
    }
}
