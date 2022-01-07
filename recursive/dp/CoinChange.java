package recursive.dp;

import java.util.Arrays;

public class CoinChange {
    /**
     * Link:
     * https://leetcode-cn.com/problems/coin-change/
     *
     */
    int[] coins;
    int amount;
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        init(coins, amount);
        return refineDP();
    }

    private void init (int[] coins, int amount) {
        this.coins = coins;
        this.amount = amount;
    }


    /**
     * Main idea:
     *
     *
     * 當 i 來到 coins.length 時:
     *    1. 如果 rest == 0, 說明之前存在一組解恰好能組合出 amount,
     *       當前僅需要 0 個硬幣, return 0。
     *
     *    2. 如果 rest != 0, 說明沒辦法組合出 amount,return -1
     *
     * 當 i 來到普遍情況時:
     *    1. 讓第 i 種硬幣從使用 0 可開始試, 直到用盡全部第 i 種硬幣都用上,
     *       即 rest 扣掉 k 個 第 i 種硬幣後會造成 rest < 0
     *
     *    2. 當第 i 種硬幣使用 0 個, 後面搞定 rest - 0 * coins[i]
     *       當第 i 種硬幣使用 1 個, 後面搞定 rest - 1 * coins[i]
     *       ...
     *       當第 i 種硬幣使用 k 個, 後面搞定 rest - k * coins[i]
     *
     *       如果後面搞定 rest - k * conins[i] 使用了 x 個硬幣,
     *       當前解為 x + k 個硬幣。
     *
     *    3. 以 minCoins 找出所有能組出 rest 的解中, 使用最少個硬幣的那一個解
     *       如果第一次得到後面的值 minCoins = after + k, 其他情況為:
     *       minCoins = min {minCoins, after + k}
     *
     */

    private int coinChange (int i, int rest) {
        if (i == coins.length) {
            return rest == 0 ? 0 : amount + 1;
        }

        int minCoins = coinChange(i + 1, rest);
        for (int k = 1; k * coins[i] <= rest; k++) {

            int after = coinChange(i + 1, rest - k * coins[i]);
            minCoins = Math.min(minCoins, after + k);
        }
        return minCoins;
    }

    private int refineDP () {
        int [] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;
        for (int i = coins.length - 1; i >= 0; i --) {
            for (int rest = 0; rest <= amount; rest ++) {
                if (coins[i] <= rest) {
                    //
                    dp[rest] = Math.min(dp[rest], dp[rest - coins[i]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    private int dp () {
        int [][] dp = new int [coins.length + 1] [amount + 1];

        for (int i = 0; i <= coins.length; i++) {
            Arrays.fill(dp[i], amount + 1);
            dp[i][0] = 0;
        }

        for (int i = coins.length - 1; i >= 0; i --) {
            for (int rest = 0; rest <= amount; rest ++) {
                if (coins[i] > rest) {
                    // 當前這種類型的硬幣面值比剩餘的 rest 大, 因此只依賴之後的硬幣能組合出的解
                    dp[i][rest] = dp[i + 1] [rest];
                }else {
                    // 理論上, 要考慮所有後面的硬幣 (i + 1) 的所有的組合中硬幣數最少的,
                    // 但是經觀察可得, 當 rest 有序增加, 第 i 種類型的 rest - coins[i] 的 rest,
                    // 會包含所有 i + 1 類型但 rest 為 rest - coins[i] 的最少組合數,
                    // 因此只需要考慮 dp[i + 1][rest] 和 dp[i][res - coins[i]] + 1 最少的那一種
                    dp[i][rest] = Math.min(dp[i + 1][rest], dp[i][rest - coins[i]] + 1);
                }
            }
        }
        return dp[0][amount] > amount ? -1 : dp[0][amount];
    }
}
