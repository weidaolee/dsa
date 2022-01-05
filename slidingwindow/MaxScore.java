package slidingwindow;


public class MaxScore {
    /**
     * Link:
     * https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards/
     *
     * Main idea:
     * 如果可以拿 k 次，說明中間會有 len - k 個不拿，
     * 維護一個 sliding window, 求 L ~ R 總和最小
     *
     * Implementation:
     * 1. R 從 0 開始走到 N - K - 1，沿路累加 curMin，累加完後更新 min
     * 2. L = 0, R = N - k, curMin 扣掉 L 的點數, 增加 R 的點數，更新 min, L ++, R ++
     * 結束後，return total - min;
     */

    int [] cardPoints;
    int k;
    int N;

    public int maxScore(int[] cardPoints, int k) {
        init(cardPoints, k);
        return maxScore();
    }

    public int maxScore () {
        int total = 0;
        for (int i : cardPoints) {
            total += i;
        }
        if (k >= N) {
            return total;
        }

        int min = total;
        int curMin = 0;

        int R;
        for (R = 0; R < N - k; R ++) {
            curMin += cardPoints[R];
        }

        min = Math.min(min, curMin);
        int L = 0;
        for (R = N - k; R < N; R ++, L ++) {
            curMin -= cardPoints[L];
            curMin += cardPoints[R];

            min = Math.min(min, curMin);
        }

        return total - min;
    }

    public void init (int[] cardPoints, int k) {
        this.cardPoints = cardPoints;
        this.k = k;
        N = cardPoints.length;
    }
}
