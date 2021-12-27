package recursive.dp;

public class UniqueBstI {
    public int numTrees(int n) {
        return dp(n);
    }

    /**
     * 對於每一個 node, 輪流當 root:
     * 左有 0 ~ N - 1 個 node, 右有 N - 1 ~ 0 個 node
     * 又每個 node 的 val 不相同，因此該 root 的所有可能為 左邊所有的可能性 * 右邊所有的可能性
     * 累加每個 node 輪流當 root 的情況，即是所有的可能性
     */
    public int numBSTs(int n) {
        if (n < 0) {
            return 0;
        }

        if (n == 0) {
            return 1;
        }

        if (n == 1 || n == 2) {
            return n;
        }

        int res = 0;
        for (int leftNum = 0; leftNum <= n - 1; leftNum++) {
            int leftWays = numBSTs(leftNum);
            int rightWays = numBSTs(n - 1 - leftNum);
            res += leftWays * rightWays;
        }
        return res;
    }

    /**
     * 由 node 數量由低到高, 能組成的所有可能性，後面需要調用時可直接查 dp 表
     */

    public int dp(int n) {
        if (n < 2) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int numNode = 1; numNode <= n; numNode++) {
            for (int numLeft = 0; numLeft <= numNode - 1; numLeft++) {
                int numRight = numNode - 1 - numLeft;
                dp[numNode] += dp[numLeft] * dp[numRight];
            }
        }
        return dp[n];
    }

}
