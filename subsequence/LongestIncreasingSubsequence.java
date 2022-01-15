package subsequence;


public class LongestIncreasingSubsequence {
    /**
     * Link:
     * https://leetcode-cn.com/problems/longest-increasing-subsequence/
     *
     */

    int[] nums;
    int len;
    public int lengthOfLIS(int[] nums) {
        init(nums);

        return refinedDP();
    }

    /**
     * Main idea:
     *     dp[i] 定義為, 以 nums[i] 結尾, 最長的 increasing subsequence length
     *
     * Implementations:
     *     用 longestLessThen function 來找 從 dp[0] - dp[i - 1] 中,
     *     滿足 nums[preIndex] < nums[curIndex] 且 有最大的 dp 值的
     *
     */

    private int dp() {
        int[] dp = new int[len];
        dp[0] = 1;

        int maxLen = 1;
        for (int i = 1; i < len; i++) {
            dp[i] = 1 + longestLessThen(i, dp);
            maxLen = Math.max(maxLen, dp[i]);
            System.out.printf("dp[%d] = %d%n", i, dp[i]);
        }
        return maxLen;
    }

    private int longestLessThen(int index, int[] dp) {
        int maxLen = 0;
        for (int i = 0; i < index; i++) {
            if (nums[i] < nums[index]) {
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        return maxLen;
    }

    /**
     * Main idea:
     *     以一個 array minEnds 紀錄長度為 i + 1 的最小的結尾,
     *     當遍歷到 i 時:
     *         如果 A[i] >  minEnds 的當前邊界的 value,
     *             直接擴充 minEnds 的 boundary, 且紀錄 minEnds 新的 boundary 為 A[i]
     *         如果 A[i] <= minEnds 的當前邊界的 value,
     *             在 0 到 boundary 範圍上, 二分查找比 A[i] 大的最小的 index
     *             然後更新 minLen[index] = A[i]
     *
     *     整個遍歷結束後, 我們得到 minEnds 含意為:
     *         長度為 i + 1 的 increasing subsequence 中, 結尾最小的 value 為 minEnds[i],
     *         此時 boundary + 1 為最長的 increasing subsequence
     */

    private int refinedDP() {
        int[] minEnds = new int [len];
        int boundary = 0;
        minEnds[0] = nums[0];

        for (int i = 1; i < len; i++) {
            if (nums[i] > minEnds[boundary]) {
                minEnds[++ boundary] = nums[i];
            } else {
                // find index k, such that nums[k] >= nums[i] but with min value
                int L = 0;
                int R = boundary;
                int M;
                while (L < R) {
                    M = L + (R - L) / 2;
                    if (nums[i] >= minEnds[M]) {
                        L = M + 1;
                    } else {
                        R = M;
                    }
                }
                minEnds[L] = nums[i];
            }
        }
        return boundary + 1;
    }

    private void init (int[] nums) {
        this.nums = nums;
        len = nums.length;
    }
}
