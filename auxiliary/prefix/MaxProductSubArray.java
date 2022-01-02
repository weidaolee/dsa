package auxiliary.prefix;


public class MaxProductSubArray {
    /**
     * Main idea:
     *
     * 先考慮 A [0..N-1] 不包含 0:
     * 1. 假設 A[0..N-1] 有偶數個負數, max product = P[N-1]
     * 2. 假設 A[0..N-1] 有奇數個負數,
     *    s[i:j] 為所有 P[i:j] > 0 的 subarray,
     *    s[i:j] 所包含負數個數必為偶數個,
     *    最佳解 s' 必為偶數個
     *
     *    考慮所有 s 中, P[i:j] 最大的
     *
     * Implementation:
     *   使用 P[max negative] 紀錄最大的負數 prefix negative product
     *   來到 i 位置, P[i] = product of A[0...i]
     *
     *   if P[i] > 0:
     *       max product = max {max product, P[i]}
     *
     *   if P[i] < 0,
     *       max product = max{max product, P[i] / P[max negative]}
     *
     *       P[max negative]
     *       = P[max negative] < 0 ?
     *         max {P[max negative] , P[i]} : min {P[max negative], P[i]}
     *
     *   if P[i] = 0,
     *       reset max product, P[i], P[max negative]
     *       max product = max{max product, 0}
     *       P[i] = 1
     *       P[max negative] = 1, 下一輪 P[i] / P[max negative] = P[i] / 1 = P[i]
     */

    public int maxProduct(int[] nums) {
        int maxProduct = Integer.MIN_VALUE;
        int curProduct = 1;
        int negProduct = 1;
        for (int i = 0; i < nums.length; i++) {
            curProduct *= nums[i];
            if (curProduct > 0) { // P[i] > 0

                maxProduct = Math.max(maxProduct, curProduct);

            } else if (curProduct < 0){ // P[i] < 0

                // P[i] / P[max negative] or P[i] / 1
                maxProduct = Math.max(maxProduct, curProduct / negProduct);

                // update P[max negative]
                negProduct = negProduct < 0 ?
                    Math.max(negProduct, curProduct) : Math.min(negProduct, curProduct);
            } else {

                // max product = max {-INF, 0} = 0
                // max product = max {max product, 0 } = max product
                maxProduct = Math.max(maxProduct, 0);
                curProduct = 1;
                negProduct = 1;
            }
        }
        return maxProduct;
    }
}
