package dequeue;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaxArray {

    public static class MaxDeque {
        int [] arr;

        int L;
        int R;
        Deque <Integer> deque;


        public MaxDeque (int [] arr) {
            this.arr = arr;

            L = -1;
            R = 0;
            deque = new LinkedList<>();
        }

        public void addFromRight() {
            if (R == arr.length) {
                return;
            }

            while (!deque.isEmpty() && deque.peekLast() <= arr[R]) {
                deque.pollLast();
            }
            deque.addLast(R++);
            System.out.println("R: " + R);
        }

        public void removeFromLeft () {
            if (L > R - 1) {
                return;
            }

            L ++;
            if (L == deque.peekFirst()) {
                deque.pollFirst();
            }
            System.out.println("L: " + L);
        }

        public int getMaxIndex () {
            if (deque.isEmpty()) {
                return -1;
            }
            return deque.peekFirst();
        }

        public int getLength () {
            return R - L;
        }

    }

    public static int [] getMaxWindow(int [] arr, int windowSize) {
        /**
         * deque 維護一個嚴格遞增序:
         * 注意，deque 中只放 index
         * 1. 調整右邊界：如果要放入 i ，把 deque 中所有 <= i 的 全部 poll out 再放入
         * 2. 調整左邊界：查看是否左邊界的 index == deque.peekFirst(), 如果不相等就不用理會
         */
        if (arr == null || windowSize < 1 || arr.length < windowSize) {
            return null;
        }

        Deque <Integer> deque = new LinkedList<>();
        int index = 0;
        int [] res = new int [arr.length - windowSize + 1];
		for (int R = 0; R < arr.length; R++) {
            int L = R - windowSize;
			while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[R]) {
				deque.pollLast();
			}
			deque.addLast(R);
			if (deque.peekFirst() == L) { // i - windowSize = left window index
				deque.pollFirst();
			}
			if (L + 1 >= 0) {
				res[index++] = arr[deque.peekFirst()];
			}
		}

        return res;
    }

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 4, 3, 5, 4, 3, 3, 6, 7 };
		int w = 3;
		printArray(getMaxWindow(arr, w));

	}
}
