package slidingwindow;


public class MaxAverageSubArray {
    /**
     * Link:
     * https://leetcode-cn.com/problems/maximum-average-subarray-i/
     *
     */
    public double findMaxAverage(int[] nums, int k) {
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;
        int L = 0, R = 0;
        while (R < nums.length) {
            curSum += nums[R];
            if (R - L + 1 == k) {
                maxSum = Math.max(maxSum, curSum);
                curSum -= nums[L ++];
            }
            R ++;
        }
        return (double) (maxSum / k);
    }
}
