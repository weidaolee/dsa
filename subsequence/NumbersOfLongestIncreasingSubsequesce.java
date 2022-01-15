package subsequence;

public class NumbersOfLongestIncreasingSubsequesce {
    /**
     * Link:
     * https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/
     *
     */
    int[] nums;
    int len;
    public int findNumberOfLIS(int[] nums) {
        init(nums);
        return dp();
    }

    private int dp() {
        int[] dp = new int[len];
        int[] count = new int[len];

        int maxLen = 0;
        int total = 0;
        for (int cur = 0; cur < len; cur++) {
            setDPValueAndNumOfSeqs(cur, dp, count);
            if (dp[cur] > maxLen) {
                maxLen = dp[cur];
                total = count[cur];
            } else if (dp[cur] == maxLen) {
                total += count[cur];
            }
        }
        return total;
    }

    private void setDPValueAndNumOfSeqs (int cur, int[] dp, int[] count) {
        dp[cur] = 1;
        count[cur] = 1;

        for (int pre = 0; pre < cur; pre ++) {
            if (nums[pre] < nums[cur]) {
                if (dp[pre] + 1 > dp[cur]) {
                    dp[cur] = dp[pre] + 1;
                    count[cur] = count[pre];
                } else if (dp[pre] + 1 == dp[cur]) {
                    count[cur] += count[pre];
                }
            }
        }
    }

    private void init (int[] nums) {
        this.nums = nums;
        len = nums.length;
    }
}
