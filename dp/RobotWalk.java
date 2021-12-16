package dp;

public class RobotWalk {

    public static int brute (int cur, int rest, int E, int N) {
        /**
         * base case:
         * cur = E 時，找到一個解， cur != E 時，什麼都沒發生
         *
         * special cases:
         * 1. cur = 1 時，只能往前走
         * 2. cur = N 時，只能往後走
         *
         * normal case:
         * 往後走的解 + 往前走的解
         *
         * 調用過程分析:
         * case: s = 2, K = 4, E = 4, N = 5
         * f (2, 4) -> f (1, 3), f (3, 3)
         * f (1, 3) -> f (2, 2)
         * f (3, 3) -> f (2, 2), f (4, 2)
         * f (2, 2) -> f (1, 1), f (3, 1)
         * f (1, 1) -> f (2, 0)
         * f (3, 1) -> f (2, 0), f (4, 0)
         * f (4, 2) -> f (3, 1), f (5, 1)
         * f (3, 1) -> f (2, 0), f (4, 0)
         * f (5, 1) -> f (4, 0)
         * f (2, 2) -> f (1, 1), f (3, 1)
         * f (1, 1) -> f (2, 0)
         * f (3, 1) -> f (2, 0), f (4, 0)
         *
         * time : O(2 ^ K), 高度為 K 的 binary tree
         * space: ?
         *
         * 1. 可以發現大量的重複計算
         * 2. 可以發現，cur 和 rest 是確定的時候 brute (cur. rest) 一定相等，
         *    返回值只和輸入參數有關，和誰調用無關 (無後效性) 。
         */

        if (rest == 0) {
            return cur == E ? 1 : 0;
        }

        if (cur == 1) {
            return brute(2, rest - 1, E, N);
        }

        if (cur == N) {
            return brute(N - 1, rest - 1, E, N);
        }

        return brute(cur - 1, rest - 1, E, N) + brute(cur + 1, rest - 1, E, N);
    }

    public static int memorized(int S, int K, int E, int N) {
        int [][] dp = new int [N + 1][K + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }

        return memory(S, K, E, N, dp);
    }

    public static int memory(int cur, int rest, int E, int N, int[][] dp) {
        /**
         * time: O (K * N)
         */
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }

        if (rest == 0) {
            dp[cur][rest] = cur == E ? 1 : 0;
            return dp[cur][rest];
        }

        if (cur == 1) {
            dp[cur][rest] = memory(cur + 1, rest - 1, E, N, dp);
            return dp[cur][rest];
        }

        if (cur == N) {
            dp[cur][rest] = memory(cur - 1, rest - 1, E, N, dp);
            return dp[cur][rest];
        }

        dp[cur][rest] = memory(cur - 1, rest - 1, E, N, dp) + memory(cur + 1, rest - 1, E, N, dp);
        return dp[cur][rest];
    }

    public static int table (int S, int K, int E, int N) {
        /**
         * 根據記憶化搜索的解，求出嚴格表結構解：
         * 1. 展開 memory table, 從 base case 開始填
         * 2. 觀察每一格之間的依賴關係，照依賴關係填
         *
         * base case:
         * rest = 0, cur = 1 ~ N 時，只有 cur = E 時是 1，其他是 0
         *
         * special cases:
         * cur = 1, rest > 0 時，依賴 dp[cur + 1, rest - 1] 的位置
         * cur = N, rest > 0 時，依賴 dp[cur - 1, rest - 1] 的位置
         *
         * normal case:
         * 依賴 dp[cur - 1, rest - 1] + dp[cur + 1, rest -1]
         */

        int [][] dp = new int [N + 1] [K + 1];

        // base case
        dp[E][0] = 1;

        // 注意，是以 rest = 1, 2, ... N 往上填，這要寫在外層
        for (int rest = 1; rest <= K; rest ++) {
            for (int cur = 1; cur <= N; cur ++) {
                if (cur == 1) {
                    dp[cur][rest] = dp[cur + 1][rest - 1];
                } else if (cur == N) {
                    dp[cur][rest] = dp[cur - 1][rest - 1];
                } else {
                    dp[cur][rest] = dp[cur + 1][rest - 1] + dp[cur - 1][rest - 1];
                }
            }
        }
        return dp[S][K];
    }

    public static void main(String[] args) {
        System.out.println(brute(2, 4, 4, 5));
        System.out.println(memorized(2, 4, 4, 5));
        System.out.println(table(2, 4, 4, 5));
    }
}
