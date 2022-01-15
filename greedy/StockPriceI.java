package greedy;


public class StockPriceI {
    /**
     * Link:
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
     *
     */

    int prices[];
    public int maxProfit(int prices[]) {
        init (prices);
        return decomposition();
    }

    /**
     * Main idea:
     *   max profit
     *   = max {price[j] - price[i]}
     *   = max {price[j]} - min {price[i]}
     *   = max {max profit,  max {price[j]} - prices[i]}}
     *   note: i < j
     */

    private int decomposition () {
        int len = prices.length;
        int maxPrice = prices[len - 1];
        int maxProfit = 0;

        for (int i = len - 2; i >= 0; i--) {
            if (prices[i] < maxPrice) {
                maxProfit = Math.max(maxProfit, maxPrice - prices[i]);
            } else {
                maxPrice = Math.max(maxPrice, prices[i]);
            }
        }
        return maxProfit;
    }

    private void init (int[] prices) {
        this.prices = prices;
    }
}
