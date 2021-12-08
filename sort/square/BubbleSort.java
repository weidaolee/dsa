package sort.square;

public class BubbleSort {
    int [] arr;

    public BubbleSort(int[] arr) {
        this.arr = arr;
	}

	private void sort() {
        if (arr.length < 2) {
            return ;
        }

        for (int i = 0; i < arr.length; i++) {
            boolean isOrdered = true;
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(j, j - 1);
                    isOrdered = false;
                }
                // System.out.printf("%d %d\n", i, j);
                if (isOrdered) { break; }
            }
            if (isOrdered) { break; }
        }
        return;
	}
	private void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
	}
	public static void main(String[] args) {
        // int [] arr = {0, 4, 7, 1, 90, 3, 2, 2};
        int [] arr = {0, 2, 2, 3, 4, 5, 6, 7, 0};
        BubbleSort bubble = new BubbleSort(arr);
        bubble.sort();
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
