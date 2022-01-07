package recursive.dp;

public class PredictTheWinnerII {
    /**
     * Link:
     * https://leetcode-cn.com/problems/predict-the-winner/
     *
     */

    int[] nums;
    int n;
    public boolean PredictTheWinner(int[] nums) {
        init(nums);
        setDP();

        return dp();
    }

    public int differenceOfScore(int i, int j) {
        if (i == j) {
            return nums[i];
        }

        // 如果選擇第 i 個, 當前所選擇的點數 - 對手選擇的分數即為相對分數差
         int choseL = nums[i] - differenceOfScore(i + 1, j);
         int choseR = nums[j] - differenceOfScore(i, j - 1);

         return Math.max(choseL, choseR);
    }

    int dp[][];
    private void setDP() {
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }
    }

    private void diagonalTraverse(int top, int left, int down, int right) {
        for (int i = top, j = left; i <= down && j <= right; i ++, j ++) {
            dp[i][j] = Math.max(nums[i] - dp[i + 1][j],
                                nums[j] - dp[i][j - 1]);
        }
    }

    private boolean dp () {
        int top = 0, left = 1;
        int down = n - 2, right = n - 1;
        for (; left <= n - 1 && down >= 0; left ++, down --) {
            diagonalTraverse(top, left, down, right);
        }
        return dp[0][n - 1] >= 0;
    }

    private void init (int[] nums) {
        this.nums = nums;
        n = nums.length;
    }
}
