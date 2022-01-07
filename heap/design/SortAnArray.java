package heap.design;


public class SortAnArray {
    /**
     * Link:
     * https://leetcode-cn.com/problems/sort-an-array/
     *
     */

    public int[] sortArray(int[] nums) {
        MaxHeap heap = new MaxHeap(nums);
        heap.heapSort();
        return heap.arr;
    }
    private static class MaxHeap {
        int[] arr;
        int tail;

        public MaxHeap(int[] arr) {
            this.arr = arr;
            this.tail = -1;
        }

        public void heapSort() {
            tail = arr.length - 1;
            for (int i = tail; i >= 0; i --) {
                heapify(i);
            }

            while (tail >= 0) {
                swap(0, tail --);
                heapify(0);
            }
        }

        public void heapInsert(int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void heapify(int index) {
            int L = 2 * index + 1;
            int largest;
            while (L <= tail) {
                largest = L + 1 <= tail && arr[L + 1] > arr[L] ? L + 1 : L;
                largest = arr[largest] > arr[index] ? largest : index;
                if (largest == index) {
                    break;
                }

                swap(index, largest);
                index = largest;
                L = 2 * index + 1;
            }
        }

        public void swap (int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}
