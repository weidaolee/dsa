package sort.square;

public class SelectionSort {
	 int[] arr;

	public SelectionSort(int [] arr) {
        this.arr = arr;
	}

    public void sort () {
        if (arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            if (i != min) {
                swap(i, min);
            }
        }
        return;
    }


    public void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args) {
        // int [] arr = {3, 5, 1, 10, 9, 9, 0, 10, 10};
        int [] arr = {2, 1};
        SelectionSort selection = new SelectionSort(arr);
        selection.sort();
        for (int i  : selection.arr) {
            System.out.println(i);
        }
    }
}
