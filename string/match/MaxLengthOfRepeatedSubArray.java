package string.match;


public class MaxLengthOfRepeatedSubArray {
    /**
     * Link:
     * https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/
     * Main idea:
     *
     * * * * * * * * * * * * * * * * * * *
     * First stage:
     *                        0
     * A                     |*|*|*|*|
     * B       |*|*|*|*|*|*|*|*|
     *                        7
     *
     *                    0
     * A                 |*|*|*|*|
     * B       |*|*|*|*|*|*|*|*|
     *                    5
     * * * * * * * * * * * * * * * * * * *
     * Second stage:
     *                  0
     * A               |*|*|*|*|
     * B       |*|*|*|*|*|*|*|*|
     *                  4
     *
     *          0
     * A       |*|*|*|*|
     * B       |*|*|*|*|*|*|*|*|
     *          0
     * * * * * * * * * * * * * * * * * * *
     * Third stage:
     *
     *          1
     * A     |*|*|*|*|
     * B       |*|*|*|*|*|*|*|*|
     *          0
     *
     *          3
     * A |*|*|*|*|
     * B       |*|*|*|*|*|*|*|*|
     *          1
     * * * * * * * * * * * * * * * * * * *
     */

    int[] A;
    int[] B;

    public int findLength(int[] nums1, int[] nums2) {
        init(nums1, nums2);
        return findMaxLen();

    }

    private void init (int[] nums1, int[] nums2) {
        /**
         * A 為較短的
         * B 為較長的
         */

        this.A = nums1.length < nums2.length ? nums1 : nums2;
        this.B = A == nums1 ? nums2 : nums1;
    }

    private int findMaxLen () {
        int maxLen = 0;

        /**
         *                        0
         * A                     |*|*|*|*|
         * B       |*|*|*|*|*|*|*|*|
         *                        7
         *
         *                    0
         * A                 |*|*|*|*|
         * B       |*|*|*|*|*|*|*|*|
         *                    5
         * A 從 0 開始,
         * B 從 最後一位開始,
         * 逐漸擴張 window size, B 的比較起點 j 隨 window size 後退
         */

        for (int size = 1; size < A.length; size ++) {
            maxLen = Math.max(maxLen, findMaxLen(0, B.length - size, size));
        }

        /**
         *                  0
         * A               |*|*|*|*|
         * B       |*|*|*|*|*|*|*|*|
         *                  4
         *
         *          0
         * A       |*|*|*|*|
         * B       |*|*|*|*|*|*|*|*|
         *          0
         *
         * A 從 0 開始,
         * B 從 B 的長度減去 A 的長度開始,
         * 固定住 window size, 向後移動 j 的位置
         *
         */

        for (int j = B.length - A.length ; j >= 0 ; j --) {
            maxLen = Math.max(maxLen, findMaxLen(0, j, A.length));
        }

        /**
         *          1
         * A     |*|*|*|*|
         * B       |*|*|*|*|*|*|*|*|
         *          0
         *
         *          3
         * A |*|*|*|*|
         * B       |*|*|*|*|*|*|*|*|
         *          1
         *
         * A 從 1 開始,
         * B 從 0 開始,
         * window size 逐漸減小, i 隨 window size 減小而前進
         */

        for (int i = 1; i < A.length ; i ++) {
            maxLen = Math.max(maxLen, findMaxLen(i, 0, A.length - i));
        }


        return maxLen;
    }

    private int findMaxLen (int i, int j, int windowSize) {
        /**
         * A 從 i 位置開始,
         * B 從 j 位置開始,
         * 比較 window size 次
         */

        int curLen = 0;
        int maxLen = 0;

        for (int k = 0; k < windowSize; k ++) {
            if (A[i + k] == B[j + k]) {
                curLen ++;
            } else if (curLen > 0) {
                maxLen = Math.max(maxLen, curLen);
                curLen = 0;
            }
        }

        maxLen = Math.max(maxLen, curLen);
        return maxLen;
    }

}
