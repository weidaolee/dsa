package array.subarray;


public class MaxSumSubArray {
    /**
     * Main idea:
     *   解的充分條件, 假設 a [i..j] 為最大且最長的 subarray:
     *     1. a[i - 1], a[j + 1] is less then 0
     *     2. 包含 i - 1 的 sum, 必小於 0, 包含 j + 1 的 sum, 必小於 0
     *     3. 介於 i - j 之間的 sum, 必 >= 0
     *     4. 如果 maxSum <= 0, subarray len 必等於 1, 且 a[i] 為 a[0...N-1] 中最大值
     *
     * Implementation:
     *   以 maxSum 跟蹤最新的 curSum,
     *   curSum 一路累加, 當 curSum < 0, curSum 保持在 0
     *
     */
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;

        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            maxSum = Math.max(maxSum, curSum);
            curSum = curSum < 0 ? 0 : curSum;
        }
        return maxSum;
    }
}
