package recursive.dp.stock;


public class BestTimeToBuyAndSellStockWithCooldown {
    /**
     * Link:
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
     *
     */

    int prices[];
    int len;
    public int maxProfit(int prices[]) {
        init (prices);
        return dp();
    }

    /**
     * Main idea:
     *
     *   Definition:
     *     dp[i][0] 定義為第 i 天不持有股票的收益
     *     dp[i][1] 定義為第 i 天要持有股票的收益
     *     dp[i][2] 定義為第 i 天進入冷凍期
     *
     *     冷凍期: 根據題意, 冷凍期定義為 "售出股票的隔天"
     *
     *   Initial:
     *     dp[0][0] = 0
     *     dp[0][1] = -prices[0], 決定持有, 花費 prices[0] 的金額購買股票
     *     dp[0][2] = 0
     *
     *   transition:
     *     0 <- 0 昨天不持有, 今天不持有, 可以轉移, 什麼都不做
     *     0 <- 1 昨天要持有, 今天不持有, 可以轉移, 在 "昨天持有" 的基礎上, 賣出股票, 獲得 prices[i] 的收益
     *     0 <- 2 昨天被冷凍, 今天不持有, 無法轉移, 昨天沒有買入過股票, 今天無法執行賣出
     *
     *     1 <- 0 昨天不持有, 今天要持有, 無法轉移, 今天為冷凍期
     *     1 <- 1 昨天要持有, 今天要持有, 可以轉移, 持有昨天已經持有的股票, 什麼都不做
     *     1 <- 2 昨天被冷凍, 今天要持有, 可以轉移, 在 "昨天被冷凍" 的基礎上,付出 prices[i] 的金額購入股票
     *
     *     2 <- 0 昨天不持有, 今天被冷凍, 可以轉移, 昨天售出股票, 今天被冷凍, 維持昨天不持有股票的收益
     *     2 <- 1 昨天要持有, 今天被冷凍, 無法轉移, 沒有定義
     *     2 <- 2 昨天被冷凍, 今天被冷凍, 無法轉移, 沒有定義
     */

    private int dp() {
        int [][] dp = new int [len][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;

        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] - prices[i]);
            dp[i][2] = dp[i - 1][0];
        }
        return Math.max(dp[len - 1][0], dp[len - 1][2]);
    }


    private void init (int[] prices) {
        this.prices = prices;
        len = prices.length;
    }
}
