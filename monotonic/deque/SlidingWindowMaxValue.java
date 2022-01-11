package monotonic.deque;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaxValue {
    /**
     * Link:
     * https://leetcode-cn.com/problems/sliding-window-maximum/
     *
     */

    /**
     * Main idea:
     *
     * 定義: window <L, R> = A[L + 1, L + 2, ..., R]
     * 因此, A[L] 不包含在 window 內
     *
     */

    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque <Integer> deque = new LinkedList<>(); //  Deque <index>
        int[] ans = new int[nums.length - k + 1];
        int R, L;
        for (R = 0; R < nums.length; R ++) {
            L = R - k;
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[R]) {
                deque.pollLast();
            }

            deque.addLast(R);

            if (deque.peekFirst() == L) { // A[L] 本來就不屬於 window 的範圍, A[L + 1:R + 1] 才是
                deque.pollFirst();
            }

            if (L >= -1) {
                ans[L + 1] = nums[deque.peekFirst()];
            }
        }
        return ans;
    }

}
