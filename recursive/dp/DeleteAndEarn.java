package recursive.dp;

public class DeleteAndEarn {
    /**
     * Link:
     * https://leetcode-cn.com/problems/delete-and-earnmn/
     *
     */

    int[] nums;
    int[] map;
    public int deleteAndEarn(int[] nums) {
        init(nums);

        return refinedDP();
    }

    private int deleteAndEarn(int i) {
        if (i >= map.length) {
            return 0;
        }
        int pickThis = map[i] + deleteAndEarn(i + 2);
        int skipThis = deleteAndEarn(i + 1);
        return Math.max(pickThis, skipThis);
    }

    private int dp() {
        int [] dp = new int[map.length + 2];
        for (int i = map.length - 1; i >= 0; i --) {
            dp[i] = Math.max(map[i] + dp[i + 2], dp[i + 1]);
        }

        return dp[0];
    }

    private int refinedDP() {
        int [] dp = new int[3];
        for (int i = map.length - 1; i >= 0; i --) {
            dp[mod3(i)] = Math.max(map[i] + dp[mod3(i + 2)], dp[mod3(i + 1)]);
        }
        return dp[0];
    }

    private int mod3(int i) {
        return i % 3;
    }

    private void init (int[] nums) {
        this.nums = nums;


        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }

        map = new int[max + 1];
        for (int i = 0; i < nums.length; i++) {
            map[nums[i]] += nums[i];
        }
    }
}
