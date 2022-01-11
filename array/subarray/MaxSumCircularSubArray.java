package array.subarray;


public class MaxSumCircularSubArray {
    /**
     * Link:
     * https://leetcode-cn.com/problems/maximum-sum-circular-subarray/
     *
     */

    /**
     * Main idea:
     * case 1: max sum sub-array 沒身首異處
     * case 2: max sum sub-array 身首異處
     *
     * 討論 case 2:
     *   如果 [O O O X X X X O O O], 其中 [X X X X] 為不夠成 max sum sub-array
     *   的 sub-array, 代表不加入 [X X X X] 可以使 max sum 達到最大, 等價於
     *   [X X X X] 為 min sum sub-array
     *
     */

    public int maxSubarraySumCircular(int[] nums) {
        int posSum = 0;
        int negSum = 0;

        int maxSum = Integer.MIN_VALUE;
        int minSum = Integer.MAX_VALUE;

        int total = 0;

        for (int i = 0; i < nums.length; i++) {
            total += nums[i];

            posSum += nums[i];
            negSum += nums[i];

            maxSum = Math.max(posSum, maxSum);
            minSum = Math.min(negSum, minSum);

            posSum = posSum < 0 ? 0 : posSum;
            negSum = negSum > 0 ? 0 : negSum;
        }


        // if all values are negative, pick atleast one largest negative value as sub array
        if (maxSum < 0 && total - minSum == 0) {
            return maxSum;
        }

        return Math.max(maxSum, total - minSum);
    }
}
