package sorting.square;

public class InsertionSort {
    int [] arr;
	public InsertionSort(int []arr) {
        this.arr = arr;
	}

	private void sort() {
        if (arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    swap(i, j);
                }
            }
        }
	}

    private void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
	}

	public static void main(String[] args) {
        int [] arr = {0, 4, 7, 1, 90, 3, 2, 2};
        InsertionSort insertion = new InsertionSort(arr);
        insertion.sort();
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
