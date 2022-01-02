package auxiliary.accumulate;

public class ColorLeftRight {
    public int minColorTimes(char[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int len = arr.length;

        int [] rightGreen = new int [len + 1];
        int [] leftRed = new int [len + 1];

        rightGreen[0] = arr[0] == 'G' ? 1 : 0;
        for (int i = 1; i < len; i ++) {
            rightGreen[i] = arr[i] == 'G' ? rightGreen[i - 1] + 1 : rightGreen[i - 1];
        }

        leftRed[len - 1] = arr[len - 1] == 'R' ? 1 : 0;
        for (int i = len - 2; i >= 0; i --) {
            leftRed[i] = arr[i] == 'R' ? leftRed[i + 1] + 1 : leftRed[i + 1];
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            int allRed = rightGreen[len - 1];
            int allGreen = leftRed[0];
            int mix = rightGreen[i] + leftRed[i + 1];
            min = Math.min(mix, Math.min(allRed, allGreen));
        }

        return min;
    }
    public static void main(String[] args) {
        char[] arr = "GGRRGRRR".toCharArray();

        ColorLeftRight o = new ColorLeftRight();
        System.out.println(o.minColorTimes(arr));
    }
}
