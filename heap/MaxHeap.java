package heap;


public class MaxHeap {
    int[] arr;
    int tail;

	public MaxHeap(int[] arr) {
		this.arr = arr;
		this.tail = -1;
	}

	public MaxHeap(int maxSize) {
		this.arr = new int[maxSize];
		this.tail = -1;
	}

    public void heapSort() {
        if (arr == null || arr.length < 2) {
            return;
        }

        tail = arr.length - 1;
        for (int i = arr.length - 1; i >= 0; i --) {
            heapInsert(i);
        }

        tail = arr.length - 1;
        while (tail >= 0) {
            swap(0, tail--);
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
        while(L <= tail) {
            // left child 跟 right child 最大的那個, 如果有的話
            largest = L + 1 <= tail && arr[L + 1] > arr[L] ? L + 1 : L;

            // 如果 largest == index, 就不用做了
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

    public static void main(String[] args) {
        int[] arr = new int[] {5,3,2,4,5,7,99,3,32,2,45,6,7};
        MaxHeap heap = new MaxHeap(arr);
        heap.heapSort();

        for (int i : heap.arr) {
            System.out.println(i);
        }
    }
}
