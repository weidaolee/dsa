package recursive.dp.stock;


public class BestTimeToBuyAndSellStockWithFee {
    /**
     * Link:
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
     *
     */

    int prices[];
    int len;
    int fee;
    public int maxProfit(int prices[], int fee) {
        init (prices, fee);
        return dp();
    }
    /**
     * Main idea:
     *
     *   Definition:
     *     dp[i][0] 定義為第 i 天不持有股票的收益
     *     dp[i][1] 定義為第 i 天要持有股票的收益
     *
     *     fee: 根據手續費的定義, 在售出股票時才課手續費
     *
     *   Initial:
     *     dp[0][0] = 0
     *     dp[0][1] = -prices[0], 決定持有, 花費 prices[0] 的金額購買股票
     *
     *   transition:
     *     0 <- 0 昨天不持有, 今天不持有, 什麼都不做
     *     0 <- 1 昨天要持有, 今天不持有, 在 "昨天要持有" 的基礎上, 賣出股票, 獲得 prices[i] - fee 的收益
     *
     *     1 <- 0 昨天不持有, 今天要持有, 在 "昨天不持有" 的基礎上, 付出 prices[i] 的金額購買股票
     *     1 <- 1 昨天要持有, 今天也持有, 持有昨天已經持有的股票, 什麼都不做
     */

    private int dp() {
        int [][] dp = new int [len][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[len - 1][0];
    }


    private void init (int[] prices, int fee) {
        this.prices = prices;
        this.fee = fee;
        len = prices.length;

    }
}
