package auxiliary.prefix;

import java.util.HashMap;
import java.util.Map;

public class LongestSubArrayEqualsK {
    /**
     * Link:
     * https://leetcode-cn.com/problems/continuous-subarray-sum/
     *
     * Main idea:
     * 更新當前 map 的邏輯:
     *    要將當前 preSum 放進 map 中, 有兩種可能:
     *    a. 以前曾經出現過當前 preSum 的值
     *    b. 以前沒出現過當前 preSum 的值
     *
     *    preSum 具備無後效性, 如果當 P[i1] = P[i2] = P[j] - k,
     *    代表 A[i1:j] 和 A[i2:j] 滿足 S[i1:j] = S[i2:j] = k,
     *    但 i1 < i2 < j, 所以如果要求最長滿足 sum = k 的解
     *    一旦 preSum 被放進 map, 永不必修改, 因此只在 map 不包含
     *    preSum 時, 才需要 put, map 只會被新增和查詢, 會被修改。
     *
     */

    public int maxSubArrayLen(int[] nums, int k) {
        Map <Integer, Integer> map = new HashMap<>(nums.length); // map prefix sum -> index
        int maxLen = 0;
        int preSum = 0;

        // P[i] = A[i] + P[i - i]
        // P[0] = A[0] + P[-1]
        // P[-1] 位置必須有定義, 它包含了 A[0] 的資訊
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            if (map.containsKey(preSum - k)) {

                // P[j:i] = P[i] - P[j - 1] = k
                // P[i] - k = P[j - 1]
                // 代表以 P[i] - k 查到的 index 值, 是 j - 1,
                // A[j:i] 的長度是 i - map.get(P[i] - k) = i - (j - 1) = i - j + 1
                maxLen = Math.max(maxLen, i - map.get(preSum - k));
            }
            map.putIfAbsent(preSum, i);
        }
        // 最後一輪要檢查是否 preSum = k, 如果相等代表 A[0..N] 整個和都 為 k,
        // A.length 為解
        return preSum == k ? nums.length : maxLen ;
    }
}
