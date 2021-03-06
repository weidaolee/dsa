package sorting.nlogn.merge;

public class MergeSort {
    int [] arr;

    public MergeSort(int[] arr) {
        this.arr = arr;
	}

	public void sort() {
        sort(0, arr.length - 1);
	}

    private void sort(int L, int R) {
        if (L == R) {
            return;
        }
        int M = L + (int) ((R - L) / 2);
        sort(L, M);
        sort(M + 1, R);
        merge(L, M, R);
    }

    private void merge(int L, int M, int R) {
        int [] arr = new int [R - L + 1];
        int p0 = L;
        int p1 = M + 1;

        int i = 0;
        while (p0 <= M && p1 <= R) {
            if (this.arr[p0] <= this.arr[p1]) {
                arr[i ++] = this.arr[p0 ++];
            } else {
                arr[i ++] = this.arr[p1 ++];
            }
        }

        while (p0 <= M) {
            arr[i++] = this.arr[p0++];
        }

        while (p1 <= R) {
            arr[i++] = this.arr[p1++];
        }

        while (i > 0) {
            i --;
            this.arr[L + i] = arr[i];
        }
    }

	public static void main(String[] args) {
        // int [] arr = {0, 4, 7, 1, 90, 3, 2, 2};
        int [] arr = {0, 2, 2, 3, 4, 5, 6, 7, 0};
        MergeSort merge = new MergeSort(arr);
        merge.sort();
        for (int i : arr) {
            System.out.println(i);
        }
    }

}
