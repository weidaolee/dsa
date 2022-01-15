package recursive.dp.stock;


public class BestTimeToBuyAndSellStockIV {
    /**
     * Link:
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
     *
     */

    int prices[];
    int k;
    int len;
    public int maxProfit(int k, int prices[]) {
        if (prices == null || prices.length == 0 || k == 0) {
            return 0;
        }

        init (k, prices);
        return refinedDP();
    }
    /**
     * Main idea:
     *
     *   Definition:
     *     dp[i][k][0] 定義為第 i 天, 處在第 k 次買賣行為, 不持有股票的收益
     *     dp[i][k][1] 定義為第 i 天, 處在第 k 次買賣行為, 要持有股票的收益
     *
     *     交易: 定義為一次買入行為, 或一次賣出行為
     *
     *   Initial:
     *
     *     dp[0][1][0] =          0, 在第 0 天發生 1 次交易, 不持有股票, 不可能
     *     dp[0][1][1] = -prices[0], 在第 0 天發生 1 次交易, 花費 prices[0] 的金額購買股票
     *
     *     dp[0][k][0] =          0, 在第 0 天發生 2 次交易, 不可能
     *     dp[0][k][1] =       -INF, 在第 0 天發生 2 次交易, 不可能
     *
     */

    private int refinedDP () {
        int [][][] dp = new int [2][k + 1][2];
        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0];

        for (int k = 2; k <= this.k ; k ++) {
            dp[0][k][1] = Integer.MIN_VALUE;
        }

        for (int i = 1; i < len; i++) {
            dp[mod2(i)][1][0] = Math.max(dp[mod2(i - 1)][1][0], dp[mod2(i  - 1)][1][1] + prices[i]);
            dp[mod2(i)][1][1] = Math.max(dp[mod2(i - 1)][1][1], -prices[i]);

            for (int k = 2; k <= this.k; k ++) {
                dp[mod2(i)][k][0] = Math.max(dp[mod2(i - 1)][k][0], dp[mod2(i - 1)][k][1] + prices[i]);
                dp[mod2(i)][k][1] = Math.max(dp[mod2(i - 1)][k][1], dp[mod2(i - 1)][k - 1][0] - prices[i]);
            }
        }

        int maxProfit = 0;
        for (int k = 1; k <= this.k; k ++) {
            maxProfit = Math.max(maxProfit, dp[mod2(len - 1)][k][0]);
        }

        return maxProfit;
    }

    private int mod2 (int i) {
        return i % 2;
    }

    private void init (int k, int[] prices) {
        this.prices = prices;
        this.k = k;
        len = prices.length;
    }
}
