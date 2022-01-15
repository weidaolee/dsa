package others;


public class ArithmeticSlices {
    /**
     * Link:
     * https://leetcode-cn.com/problems/arithmetic-slices/
     *
     */

    /**
     * Main idea:
     *    以 preDiff 紀錄前一位的 diff
     *
     *    以 curDiff 紀錄當前的 diff
     *    以 curLen 紀錄當前等差數列長度
     *
     *    Intial:
     *       preDiff = A[1] - A[0]
     *       curDiff = A[2] - A[1]
     *       curLen = preDiff == curDiff ? 2 : 1;
     *
     *    for i in loop:
     *        if preDiff != curDiff:
     *            if curLen > 2:
     *                ans 統計答案
     *            reset curLen to 1
     *
     *        curLen ++;
     *        preDiff = curDiff;
     */

    public int numberOfArithmeticSlices(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }

        int ans = 0;
        int preDiff = nums[1] - nums[0];
        int curDiff = nums[2] - nums[1];
        int curLen = preDiff == curDiff ? 2 : 1;
        for (int i = 2; i < nums.length; i++) {
            curDiff = nums[i] - nums[i - 1];
            if (preDiff != curDiff) {
                if (curLen > 2) {
                    ans += (curLen - 2) * (curLen - 1) / 2;
                }
                curLen = 1;
            }

            curLen ++;
            preDiff = curDiff;
        }

        // 統計最末尾等差數列
        if (curLen > 2) {
            ans += (curLen - 2) * (curLen - 1) / 2;
        }

        return ans;
    }
}
