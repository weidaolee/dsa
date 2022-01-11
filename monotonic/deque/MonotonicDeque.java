package monotonic.deque;

import java.util.Deque;
import java.util.LinkedList;

public class MonotonicDeque {
    /**
     * Main idea:
     *
     * 定義: window <L, R> = A[L + 1, L + 2, ..., R]
     *
     * 維護一個 monotonic deque,
     *
     * addLast:
     *   當 R 增加時, 說明 window 擴大,
     *   把 deque 內所有比 arr[R] 小的 index poll 掉,
     *   然後 R 進 deque, 以保持 deque 在 window 涵蓋到 R 時,
     *   最大的 index 一定在 deque 的 head
     *
     * pollFirst:
     *   當 L 增加時, 說明 window 縮減,
     *   因為在 addLast 我們 poll 掉不夠大的 index,
     *   所以當 window 的 L 是那個不夠大的 index, 已經被
     *   R 在 add 時被 poll 掉了, 因此當需要移動 L 時, 除非
     *   deque 的 first 是 L 自己, 都不需要額外操作, 當 L
     *   是 deque 的第一個 index 時, 也說明這是 window 內目前 arr 最大
     *   value 的 index, 而且馬上就要出 window
     *
     */

    public Deque <Integer> deque;
    public int [] arr;
    public int L, R;

    public MonotonicDeque(int[]arr) {
        deque = new LinkedList<>();
        this.arr = arr;
        L = -1; R = 0;
    }
    public void addLast(){
        if (R == arr.length) {
            return;
        }

        while (!deque.isEmpty() && deque.peekLast() <= arr[R]) {
            deque.pollLast();
        }
        deque.addLast(R ++);
    }

    public void pollFirst() {
        if (L == R) {
            return;
        }

        if (++ L == deque.peekFirst()) {
            deque.pollFirst();
        }
    }

    public int getMaxIndex() {
        return deque.isEmpty() ? -1 : deque.peekFirst();
    }
    public int size() {
        return R - L;
    }
}
