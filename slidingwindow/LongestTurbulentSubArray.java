package slidingwindow;


public class LongestTurbulentSubArray {
   /**
    * Link:
    * https://leetcode-cn.com/problems/longest-turbulent-subarray/
    *
    * Main idea:
    * 1. 如果 A[R] == A[R + 1], window size 必須為 1
    * 2. 如果 A[R - 1] < A[R] < A[R + 1] || A[R - 1] > A[R] > A[R + 1], window size 必須為 2
    * 3. 其他情況 window size 必大於 2
    */


    int[] arr;
    int len;
    public int maxTurbulenceSize(int[] arr) {
        if (arr.length < 2) {
            return arr.length;
        }

        init(arr);

        int len = arr.length;
        int maxLen = 1;
        int L = 0, R = 0;

        while (R < len - 1) {
            if (L < R) {
                if (isTurbulent(R)) {
                    R ++;
                    maxLen = Math.max(maxLen, R - L + 1);
                } else {
                    L = R;
                }
            } else {
                if (arr[R] == arr[R + 1]) {
                    L ++;
                    R ++;
                } else {
                    R ++;
                    maxLen = Math.max(maxLen, R - L + 1);
                }
            }
        }
        return maxLen;
    }

    private boolean isTurbulent (int i) {
        return (arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) || (arr[i - 1] > arr[i] && arr[i] < arr[i + 1]);
    }
    private void init (int [] arr) {
        this.arr = arr;
    }

}
