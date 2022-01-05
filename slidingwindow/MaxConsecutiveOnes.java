package slidingwindow;

public class MaxConsecutiveOnes {
    /**
     * Link:
     * https://leetcode-cn.com/problems/max-consecutive-ones-ii/
     *
     * Main idea:
     *
     * R 如果壓中 0, 如果窗口內 0 的數量為 0:
     *   1. 紀錄 0 的位置
     *   2. 窗口內 0 的數量 + 1
     * R 如果壓中 0, 如果窗口內 0 的數量為 1:
     *   1. L 先跳到 之前紀錄的 0 的下一個位置
     *   2. 紀錄當前 0 的位置
     *   Note: 這樣窗口內依然維持 1 個 0
     *
     * R 離開判斷後，紀錄 maxLen, R ++
     */

    public int findMaxConsecutiveOnes(int[] nums) {
        int L;
        int R;
        int maxLen = 0;
        int zeroPos = -1;       // 可以為任意數，但沒初始化 compiler 在 L = zeroPos + 1 會 failed
        boolean hasZero = false;

        L = 0;
        for (R = 0; R < nums.length; R ++) {
            if (nums[R] == 0) {
                if (!hasZero) {
                    zeroPos = R;
                    hasZero = true;
                } else {
                    maxLen = Math.max(maxLen, R - L);
                    L = zeroPos + 1;
                    zeroPos = R;
                }
            }
        }
        return Math.max(maxLen, R - L);
    }
}
