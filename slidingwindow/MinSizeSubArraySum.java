package slidingwindow;

public class MinSizeSubArraySum {
    /**
     * Link:
     * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
     *
     */
    public int minSubArrayLen(int target, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        int L = 0, R = 0;
        int sum = 0;
        while (R < nums.length && minLen > 1) {
            sum += nums[R];
            while (sum >= target) {
                minLen = Math.min(minLen, R - L + 1);
                sum -= nums[L ++];
            }
            R ++;
        }
        return minLen > nums.length ? 0 : minLen;
    }
}
