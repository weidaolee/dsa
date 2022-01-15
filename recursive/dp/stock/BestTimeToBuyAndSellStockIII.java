package recursive.dp.stock;


public class BestTimeToBuyAndSellStockIII {
    /**
     * Link:
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
     *
     */

    int prices[];
    int len;
    public int maxProfit(int prices[]) {
        init (prices);
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
     *     k = 1, 2
     *
     *     dp[0][1][0] =          0, 在第 0 天發生 1 次交易, 不持有股票, 不可能
     *     dp[0][1][1] = -prices[0], 在第 0 天發生 1 次交易, 花費 prices[0] 的金額購買股票
     *
     *     dp[0][2][0] =       -INF, 在第 0 天發生 2 次交易, 不可能
     *     dp[0][2][1] =       -INF, 在第 0 天發生 2 次交易, 不可能
     *
     */
    private int dp() {
        int [][][] dp = new int [len][3][2];
        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0];

        dp[0][2][0] = Integer.MIN_VALUE;
        dp[0][2][1] = Integer.MIN_VALUE;

        for (int i = 1; i < len; i++) {
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i  - 1][1][1] + prices[i]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], -prices[i]);

            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i]);
            dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i]);
        }
 
        return Math.max(dp[len - 1][1][0], dp[len - 1][2][0]);
    }

    private int refinedDP () {
        int [][][] dp = new int [2][3][2];
        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0];

        dp[0][2][0] = Integer.MIN_VALUE;
        dp[0][2][1] = Integer.MIN_VALUE;

        for (int i = 1; i < len; i++) {
            dp[mod2(i)][1][0] = Math.max(dp[mod2(i - 1)][1][0], dp[mod2(i  - 1)][1][1] + prices[i]);
            dp[mod2(i)][1][1] = Math.max(dp[mod2(i - 1)][1][1], -prices[i]);

            dp[mod2(i)][2][0] = Math.max(dp[mod2(i - 1)][2][0], dp[mod2(i - 1)][2][1] + prices[i]);
            dp[mod2(i)][2][1] = Math.max(dp[mod2(i - 1)][2][1], dp[mod2(i - 1)][1][0] - prices[i]);
        }

        return Math.max(dp[mod2(len - 1)][1][0], dp[mod2(len - 1)][2][0]);
    }

    private int mod2 (int i) {
        return i % 2;
    }

    private void init (int[] prices) {
        this.prices = prices;
        len = prices.length;
    }
}
