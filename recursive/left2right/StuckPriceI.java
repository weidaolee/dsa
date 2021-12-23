package recursive.left2right;

public class StuckPriceI {
    int[] prices;
    public int maxProfit(int[] prices) {
        init(prices);
        return memory(0);
    }
    public void init (int[] prices) {
        this.prices = prices;
    }

    public int buy (int i) {
        if (i == prices.length) {
            return 0;
        }

        maxPrice = 0;
        for (int j = i + 1; j < prices.length; j++) {
            maxPrice = Math.max(prices[j], maxPrice);
        }

        int buyToday =  maxPrice - prices[i];
        int buyOtherDay = memory(i + 1);
        return Math.max(buyToday, buyOtherDay);
    }


    int maxPrice = 0;
    public int memory(int i) {
        if (i == prices.length) {
            return 0;
        }

        int buyOtherDay = memory(i + 1);
        if (i + 1 < prices.length) {
            maxPrice = Math.max(maxPrice, prices[i + 1]);
        }

        int buyToday = maxPrice - prices[i];
        return Math.max(buyToday, buyOtherDay);
    }

    public int table() {
        if (prices.length < 2) {
            return 0;
        }

        int dp[][] = new int [prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(-prices[i], dp[i - 1][1]);
        }

        return dp[prices.length - 1][0];
    }

    public int refineTable() {
        if (prices.length < 2) {
            return 0;
        }

        int dp[][] = new int [prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], dp[(i - 1) % 2][1] + prices[i]);
            dp[i % 2][1] = Math.max(-prices[i], dp[(i - 1) % 2][1]);
        }
        return dp[(prices.length - 1) % 2][0];
    }

    public int keepMinPrice() {
        if (prices.length < 2) {
            return 0;
        }
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }

        return maxProfit;
    }
}
