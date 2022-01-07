package recursive.dp;


public class CoinChangeII {
   /**
    * Link:
    * https://leetcode-cn.com/problems/coin-change-2/
    *
    */
    int[] coins;
    int amount;
    int len;
    public int change(int amount, int[] coins) {
        init(amount, coins);

        // return change(0, amount);
        return refinedDP();
    }

    private int change(int i, int rest) {
        if (i == len) {
            return rest == 0 ? 1 : 0;
        }

        int numWays = 0;
        for (int k = 0; k * coins[i] <= rest; k ++) {
            numWays += change(i + 1, rest - k * coins[i]);
        }
        return numWays;
    }

    private int dp() {
        int[][] dp = new int[len + 1][amount + 1];

        dp[len][0] = 1;

        for (int i = len - 1; i >= 0; i --) {
            for (int rest = 0; rest <= amount; rest ++) {
                // for (int k = 0; k * coins[i] <= rest; k++) {
                //     dp[i][rest] += dp[i + 1][rest - k * coins[i]];
                // }
                if (coins[i] > rest) {
                    dp[i][rest] = dp[i + 1][rest];
                } else {
                    dp[i][rest] = dp[i + 1][rest] + dp[i][rest - coins[i]];
                }

            }
        }
        return dp[0][amount];
    }

    private int refinedDP () {
        int [] dp = new int[amount + 1];

        dp[0] = 1;
        // for (int i = 0; i <= len - 1; i --) {
        //     for (int rest = 0; rest <= amount; rest ++) {
        //         if (coins[i] <= rest) {
        //             dp[rest] += dp[rest - coins[i]];
        //         }
        //     }
        // }

        for (int c : coins) {
            for (int rest = c; rest <= amount; rest ++) {
                dp[rest] += dp[rest - c]; // 少一次判斷
            }
        }

        return dp[amount];
    }

    private void init(int amount, int[] coins) {
        this.amount = amount;
        this.coins = coins;
        len = coins.length;
    }
}
