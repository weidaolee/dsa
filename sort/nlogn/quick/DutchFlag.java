package sort.nlogn.quick;

public class DutchFlag {
    int [] arr;


    public static void main(String[] args) {
        int [] arr = {3, 5, 6, 7, 4, 3, 5, 8};
        new DutchFlag(arr).process(5);

        for (int i : arr) {
            System.out.println(i);
        }
    }

	public void process(int pivot) {
        int l = -1;
        int r = arr.length;

        int i = 0;
        while (i < r) {
            if (arr[i] < pivot) {
                /*
                  現在確認第 i 位比 target 小的，
                  將 < 區的下一位 (還沒確定過的數的第一位) 與第 i 位交換
                  i 前進一位
                 */
                swap(++l, i++);
            } else if (arr[i] == pivot) {
                /*
                  現在確認第 0 ~ i-1 位不是比 target 小，就是和 target 相等的，
                  而第 i 位也是和 target 相等，所以 i 前進一位
                 */
                i ++;
            } else {
                /*
                  現在確認第 i 位比 target 大，
                  將第 i 位放到 > 區的前一位（還沒確定過的數的最後一位），
                  i 不動，因為此時第 i 位尚未確認過
                 */
                swap(i, --r);
            }

            /* 過程執行直到 i 走到 r （最後一個不確定的也被確定了）*/
        }
	}

    public void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


	public DutchFlag(int[] arr) {
		this.arr = arr;
	}
}
