package recursive.dp;


public class JumpGameI {
    /**
     * Link:
     * https://leetcode-cn.com/problems/jump-game/
     *
     */

    int[] nums;
    int len;
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }

        init(nums);

        // return canJump(0);
        // return dp();
        return greedy();
    }

    /**
     * Recursive:
     *   Base cases:
     *     能跳出去的位置超過 len - 1
     *
     *   Normal cases:
     *     當前位置只能跳出 <= k 步, 考慮 k 步內所有可能的解
     */

    private boolean canJump (int i) {
        if (i + nums[i] >= len - 1) {
            return true;
        }

        boolean canJump = false;
        for (int k = nums[i]; k > 0 && canJump == false; k --) {
            canJump = canJump(i + k);
        }
        return canJump;
    }

    private boolean dp() {
        boolean [] dp = new boolean [len];
        dp[len - 1] = true;

        for (int i = len - 2; i >= 0; i --) {
            if (i +  nums[i] >= len - 1) {
                dp[i] = true;
                continue;
            }

            dp[i] = false;
            for (int k = nums[i]; k > 0 && !dp[i]; k --) {
                dp[i] = dp[i + k];
            }
        }
        return dp[0];

    }

    /**
     * Greedy:
     * 來到 i 位置, 考慮最遠可以跳到多遠 ?
     *
     * 1. 以 farthest 紀錄過去能跳到最遠的位置
     * 2. 來到 i 位置, 如果 i + numns 能比 farthest 更遠, 更新 farthest
     * 3. 如果 farthest < i, 代表連 i 位置都無法抵達, 跳出 loop, return false
     */

    private boolean greedy() {
        int fareast = 0;
        for (int i = 0; i < len - 1 && fareast < len - 1 && fareast >= i; i++) {
            if (i + nums[i] > fareast) {
                fareast = i + nums[i];
            }
        }
        return fareast >= len - 1;
    }

    private void init(int[] nums) {
        this.nums = nums;
        len = nums.length;
    }
}
