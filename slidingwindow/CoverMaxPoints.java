package slidingwindow;


public class CoverMaxPoints {

    public int coverMaxPoints (int [] arr, int len) {
        if (len <= 1) {
            return len;
        }
        int max = 1;
        int num = 0;

        int L = 0;
        int R = 0;

        while (R + 1 < arr.length && max < len) {
            if (arr[R + 1] - arr[L] <= len) {
                R ++;
                num ++;
                max = Math.max(max, num);
            } else {
                L ++;
                R ++;
                num --;
                num = Math.max(num, 0);
            }
        }

        return max;
    }


    public static void main(String[] args) {
        int [] arr = { 0, 10, 20, 30, 31, 32, 33, 34, 35 };

        CoverMaxPoints o = new CoverMaxPoints();
        System.out.println(o.coverMaxPoints(arr, 4));
    }
}
