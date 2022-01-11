package auxiliary.prefix;


public class MaxLenOfSubArrayWithPositiveProduct {
    /**
     * Link:
     * https://leetcode-cn.com/problems/maximum-length-of-subarray-with-positive-product/
     *
     */

    /**
     * Main idea:
     *
     * 考慮 A 不包含 0, 當前來到 i 位置:
     *   如果 prefix < 0, 考慮排除掉離 i 最遠的負數值 j, 考慮 a[j + 1:i] 的長度
     *   如果 prefix > 0, 考慮 a[0:i] 的長度
     *
     * 考慮 A 包含 0, 當前來到 i 位置:
     *   以 zero index = -1, 紀錄 0 出現的位置,
     *      neg index = -1,  紀錄 A[i] < 0 的第一個位置,
     *   當來到 A[i] = 0:
     *      zero index = i;
     *      neg index = i;
     *      prefix = 1;
     *   當來到 A[i] > 0:
     *      maxLen = max {maxLen, i - zero index}
     *   當來到 A[i] < 0:
     *      如果 neg index == zero index, 說明 A[i] 為第一個遇到的負數值,
     *      更新 neg index = i, 此時 maxLen 不會變長, 所以可以結束這回合,
     *      如果 neg index != zero index, 說明 neg index 紀錄了離 i 最遠的,
     *      使 prefix 為 < 0 的值, maxLen = max {maxLen, i - neg index}
     */

    public int getMaxLen(int[] nums) {
        int maxLen = 0;
        int zeroIdx = -1;
        int negIdx = -1;
        int product = 1;

        for (int i = 0; i < nums.length; i++) {
            // prevent from prefix overflow
            // consider the case: A = [2147483647, 2147483647, 2147483647,...]
            if (nums[i] > 0) {
                product *= 1;
            } else if (nums[i] < 0) {
                product *= -1;
            } else {
                product = 0;
            }

            if (product == 0) {
                zeroIdx = i;
                negIdx = i;
                product = 1;
            } else if (product > 0) {
                maxLen = Math.max(maxLen, i - zeroIdx);
            } else {
                if (negIdx == zeroIdx) {
                    negIdx = i;
                } else {
                    maxLen = Math.max(maxLen, i - negIdx);
                }
            }
        }
        return maxLen;
    }
}
