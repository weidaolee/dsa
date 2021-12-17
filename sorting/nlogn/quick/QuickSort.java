package sorting.nlogn.quick;

public class QuickSort {
    int [] arr;

    public static void main(String[] args) {
        int [] arr = {4, 4, 5, 0, 1};

        new QuickSort(arr).porcess();
        for (int i : arr) {
            System.out.println(i);
        }
    }

	private void porcess() {
        if (arr == null || arr.length < 2) {
            return;
        }
        recursion(0, arr.length - 1);
	}

	private void recursion(int L, int R) {
        // 注意遞迴條件 L < R
        if (L < R) {
            int [] pivots = partition(L, R);
            recursion(L, pivots[0]);
            recursion(pivots[1], R);
        }
        // System.out.printf("L %d R %d %n", L, R);
	}

    private int [] partition(int L, int R) {
        int l = L - 1;
        int r = R;

        int i = L;
        while (i < r) {
            if (arr[i] < arr[R]) {
                swap(++l, i++);
            } else if (arr[i] == arr[R]) {
                i ++;
            } else {
                swap(--r, i);
            }
        }

        swap(r, R);


        /*
          排好之後，第 l + 1 位到第 r 位全部都是 pivots
          [x, x, x, l, l + 1, l + 2, l + 3, i = r, x, x, x ]

          l 會傳給後面當作 R, 又第 R 位是下一輪的 pivot, 所以 l 不 + 1
          r 會傳給後面當作 L, 且 L 會是下一輪的 i，但這一輪的 r 已經等於 i，即第 r 個是最後一個 pivot
          所以要傳 {l, r + 1}
         */

        return new int[] {l , r + 1};
    }

    private void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

	public QuickSort(int[] arr) {
		this.arr = arr;
	}
}
