package auxiliary.prefix;

import java.util.Deque;
import java.util.LinkedList;

public class ShortestSubArrayWithSumAtleastK {
    /**
     * Link:
     * https://leetcode-cn.com/problems/shortest-subarray-with-sum-at-least-k/
     *
     * Main idea:
     *
     *   找到最佳解的流程可以轉化為:
     *   1. 找到一個滿足 sum >= k 的解, 並且讓它盡量短
     *   2. 比較所有解的長度, 得到最短解
     *
     *   example:
     *      0    1    2    3     4
     *   [-25,  80,   0,  30 , -30]
     *
     *   假設 a[0:2] 為 1 滿足 sum >= k 的解,
     *   a[0:2] 不為最短解, 因為 a[0], a[2] <= 0
     *   拋棄 a[0], a[2], 可成為更短的解。
     *
     *   所以我們得到一個性質：
     *
     *   如果 a[i:j] 為 1 個可能解, A[i], A[j] 必 > 0
     *
     *   推廣:
     *   使 a[i:j] 盡量短:
     *   1. a[i:j] 的頭尾必不包含 sum <= 0 的序列:
     *
     *      a[i:j] 為 1 可能解, i <= k <= j,
     *      P[i:k] 必 > 0, 且 P[k:j] 必 > 0,
     *
     *      a[i:j] 的頭尾必不包含 sum <= 0 的序列, 因為
     *      丟棄 a[i:k] 和 a[k:j] 可以使 a 為更短的解。
     *
     *   2. a[i:j] 其自身必不包含更短的解:
     *      確認 a[i:j] 頭尾必不包含 sum <= 0 的序列後,
     *      要保證任意 i < i' < j' < j, 不能構成 P[i':j'] >= k
     *
     *
     *   prefix sum property:
     *   1. 如何使 a[i:j] 不以 sum <= 0 的序列作為開頭跟結尾？
     *      當 i < j, P[j] <= P[i - 1], 說明 P[i:j] <= 0,
     *      則 A[i:j] 為 sum <= 0 的序列
     *
     *      proof:
     *        P[i:j] = P[j] - P[i - 1]
     *
     *        if P[i:j] <= 0
     *
     *        P[j] - P[i - 1] <= 0
     *        P[j] <= P[i - 1]
     *
     *    2. 如何使 a[i:j] 盡量短 ?
     *       a. 滿足 sum >= k 的序列 :
     *          P[i:j] >= k
     *          P[j] - P[i - 1] >= k
     *          P[j] - k >= P[i - 1]
     *
     *       b, 盡量短:
     *          在遍歷過程中, i 是嚴格遞增的,
     *          所以最新的滿足 P[j] - k >= P[i - 1] 的 i,j
     *          才有可能構成構更短的 a[i:j] 序列
     *
     *          假設存在 2 個 sum >= k 的解為 a[i1:j] 和 a[i2:j]
     *          i1 < i2, 所以 i1 可以被拋棄, 往後所有以 i1 起點的解,
     *          都不是最短解。
     *
     * Implementation:
     *
     *   當遍歷過程中, 來到 j 位置:
     *     1. 使解以 i 開頭, j 結尾時不包含 sum <= 0 的序列:
     *        當存在 i, 使得 P[j] 比前面的 P[i - 1] 小, 說明 P[i:j]
     *        sum <= 0 的序列。
     *
     *        因此我們在把 j 位置納入可能解時, 須保證前面沒有任何一個 P[i - 1]
     *        比 P[j] 大, 因此需要維護一個 monotonic 的結構。
     *
     *     2. 找到滿足 sum >= k 的解中, 最短的解：
     *        根據上一步, 我們所有的候選 i 所構成的 a[i:j], 其自身都是盡量短的。
     *
     *        當來到 j 位置, 發現存在 i 使得 P[j] - k >= P[i - 1], 更新 minLen, 然後拋棄 i,
     *        直到當以 j 結尾時, 所有的候選 i 都被確認過, 而由於我們維護的是一個 monotonic 的結構,
     *        當存在一個 i, 使得 P[j] - k < P[i - 1] 時, 後面的 i 都不必再嘗試, 因為此時的 i 代表
     *        達到 a[i:j] 要達到 k 值, 必須包含 i。
     */


    int[] nums;
    int k;

    public int shortestSubarray(int[] nums, int k) {
        init(nums, k);

        return deque();
    }

    private void init (int[] nums, int k) {
        this.nums = nums;
        this.k = k;
    }

    private int deque() {
        int minLen = Integer.MAX_VALUE;
        long [] P = new long [nums.length + 1];

        for (int i = 0; i < P.length; i++) {
            P[i + 1] = P[i] + nums[i];
        }
        Deque <Integer> deque = new LinkedList<>();

        for (int i = 0; i < P.length; i++) {

            // 丟棄 sum <= 0 的序列
            while (!deque.isEmpty() && P[i] <= P[deque.peekLast()]) {
                deque.removeLast();
            }

            // 更新 minLen, 且丟棄更長的解
            while (!deque.isEmpty() && P[i] - k >= P[deque.peekFirst()] ) {
                minLen = Math.min(minLen, i - deque.removeFirst());
            }
            deque.addLast(i);
        }

        return minLen > nums.length ? -1 : minLen;
    }
}
