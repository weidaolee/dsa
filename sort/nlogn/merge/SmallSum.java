package sort.nlogn.merge;

public class SmallSum {
    int [] arr;

	public SmallSum(int[] arr) {
		this.arr = arr;
	}

    public int process() {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return recursion(0, arr.length - 1);
    }

    private int recursion(int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + (int) ((R -L) / 2);
        return  recursion(L, mid) + recursion(mid + 1, R) + merge(L, mid, R);
    }

    private int merge(int L, int M, int R) {
        int [] arr = new int [R - L + 1];
        int res = 0;

        int p0 = L;
        int p1 = M + 1;

        int i = 0;
        while (p0 <= M && p1 <= R) {
            if (this.arr[p0] < this.arr[p1]) {
                res += this.arr[p0] * (R - p1 + 1);
                arr[i ++] = this.arr[p0 ++];
            } else { // this.arr[p0] >= this.arr[p1]
                arr[i ++] = this.arr[p1 ++];
            }
        }

        while (p0 <= M) {
            arr [i ++] = this.arr[p0 ++];
        }

        while (p1 <= R) {
            arr [i ++] = this.arr[p1 ++];
        }

        while (i > 0) {
            i --;
            this.arr[L + i] = arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int [] arr = {1, 3, 4, 2, 5};
        int res = new SmallSum(arr).process();
        System.out.println(res);
    }

}
