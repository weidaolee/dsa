package recursive.dp;


public class JumpGameII {
    /**
     * Link:
     * https://leetcode-cn.com/problems/jump-game-ii/
     *
     */

    int[] nums;
    int len;

    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        init(nums);

        // return dp();
        return greedy();
    }

    private int jump(int i) {
        if (i >= len - 1) {
            return 0;
        }

        int minSteps = len;
        for (int k = nums[i]; k > 0; k --) {
            minSteps = Math.min(minSteps, 1 + jump(i + k));
        }
        return minSteps;
    }

    private int dp() {
        int[] dp = new int[len];
        dp[len - 1] = 0;

        for (int i = len - 2; i >= 0; i --) {
            if (nums[i] + i >= len - 1) {
                dp[i] = 1;
                continue;
            }

            dp[i] = len;
            for (int k = nums[i]; k > 0; k --) {
                dp[i] = Math.min(dp[i], 1 + dp[i + k]);
            }
        }

        return dp[0];
    }
    /**
     * Greedy:
     *   以 record 紀錄上一次來到的最遠位置
     *   i 來到 record 時,
     *     1. 更新目前最遠紀錄
     *     2. 更新一次 steps
     */
    private int greedy() {
        int farthest = 0;
        int record = 0;
        int steps = 0;

        for (int i = 0; i < len - 1 && farthest < len - 1; i++) {
            if (i + nums[i] > farthest) {
                farthest = i + nums[i];
            }

            if (i == record) {
                record = farthest;
                steps ++;
            }
        }
        return steps;
    }

    private void init(int[] nums) {
        this.nums = nums;
        len = nums.length;
    }
}
