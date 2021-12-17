package sorting.nlogn.merge;

public class DescendingPairs {
    int [] arr;

	public DescendingPairs(int[] arr) {
		this.arr = arr;
	}

    public int  process() {
        if (arr.length < 2) {
            return 0;
        }
        return recursion(0, arr.length - 1);
    }

    private int recursion(int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + (int) ((R - L) / 2);

		return recursion(L, mid) + recursion(mid + 1, R) + merge(L, mid , R);
	}

	private int merge(int L, int M, int R) {

		int res = 0;
        int p0 = L;
        int p1 = M + 1;

        int [] arr = new int [R - L + 1];
        int i = 0;
        while (p0 <= M && p1 <= R) {
            // System.out.println("====================");
            // System.out.printf("%d %d %d \n", L, M ,R);
            // System.out.println("L:");
            // for (int k = L; k <= M; k++) {
            //     System.out.println(this.arr[k]);
            // }
            // System.out.println("R:");
            // for (int k = M + 1; k <= R; k++) {
            //     System.out.println(this.arr[k]);
            // }
            if (this.arr[p0] < this.arr[p1]) {
                arr[i ++] = this.arr[p0 ++];
            } else {
                if (this.arr[p0] > this.arr[p1]) {
                    res += (M - L + 1);
                    for (int k = L; k <= M; k++) {
                        System.out.printf("%d, %d \n", this.arr[k], this.arr[p1]);
                    }
                }
                arr[i ++] = this.arr[p1 ++];
            }
        }


        while (p0 <= M) {
            arr[i ++] = this.arr[p0 ++];
        }

        while (p1 <= R) {
            arr[i ++] = this.arr[p1 ++];
        }

        while (i > 0) {
            i --;
            this.arr[L + i] = arr[i];
        }
        return res;
	}

	public static void main(String[] args) {
        int [] arr = {3, 2, 4, 5, 0, 1, 1};
        int res = new DescendingPairs(arr).process();

        System.out.println(res);
    }

}
