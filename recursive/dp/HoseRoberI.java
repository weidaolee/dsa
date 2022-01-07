package recursive.dp;


public class HoseRoberI {
    /**
     * Link:
     * https://leetcode-cn.com/problems/house-robber/
     *
     */

    int[] numns;
    int len;

    public int rob(int[] nums) {
        init(nums);
        return dp();
    }
    private void init (int[] nums) {
        this.numns = nums;
        len = nums.length;
    }

    private int rob (int i) {
        if (i >= len) {
            return 0;
        }

        int pickThis = numns[i] + rob(i + 2);
        int skipThis = rob(i + 1);

        return Math.max(pickThis, skipThis);
    }

    private int dp () {
        int [] dp = new int[len + 2];
        for (int i = len - 1; i >= 0; i --) {
            dp[i] = Math.max(numns[i] + dp[i + 2], dp[i + 1]);
        }
        return dp[0];
    }

    private int refineDP () {
        int [] dp = new int[3];
        for (int i = len - 1; i >= 0; i --) {
            dp[mod3(i)] = Math.max(numns[i] + dp[mod3(i + 2)], dp[mod3(i + 1)]);
        }
        return dp[0];
    }

    private int mod3 (int i) {
        return i % 3;
    }
}
