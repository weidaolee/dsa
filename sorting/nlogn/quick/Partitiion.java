package sorting.nlogn.quick;

/*
  i 往前走，比較 arr[i] 和 target
  如果 arr[i] 比較小的話，確認區擴展，然後把 arr[i] 放到確認區最後一位
  並把原來的值放到當前位置 i (它肯定比 target 大)
 */

public class Partitiion {
    int [] arr;


    public static void main(String[] args) {
        int [] arr = {3, 5, 6, 7, 4, 3, 5, 8};
        new Partitiion(arr).process(5);

        for (int i : arr) {
            System.out.println(i);
        }
    }

	public void process(int k) {
        int p = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= k) {
                swap(++p, i);
            }
        }
	}

    public void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


	public Partitiion(int[] arr) {
		this.arr = arr;
	}

}
