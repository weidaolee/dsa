package recursive.dp;

public class PredictTheWinner {
    /**
     * Link:
     * https://leetcode-cn.com/problems/predict-the-winner/
     *
     */

    int[] nums;
    int n;
    public boolean predictTheWinner(int[] nums) {
        init(nums);
        setDP();

        // return firstMove(0, n - 1) > secondMove(0, n - 1);
        return dp();
    }

    private int firstMove(int i, int j) {
         if (i == j) {
             return nums[i];
         }

         // 先手的人在下一輪變後手
         int choseL = nums[i] + secondMove(i + 1, j);
         int choseR = nums[j] + secondMove(i, j - 1);

         return Math.max(choseL, choseR);
    }

    private int secondMove(int i, int j) {
        if (i == j) {
            return 0;
        }

        // 後手的人只被能先手決定
        return Math.min(firstMove(i + 1, j), firstMove(i, j - 1));
    }


    int firstMove[][];
    int secondMove[][];
    private void setDP() {
        firstMove = new int[n][n];
        secondMove = new int[n][n];

        for (int i = 0; i < n; i++) {
            firstMove[i][i] = nums[i];
            secondMove[i][i] = 0;
        }
    }

    private void diagonalTraverse(int top, int left, int down, int right) {
        for (int i = top, j = left; i <= down && j <= right; i ++, j ++) {
            firstMove[i][j] = Math.max(nums[i] + secondMove[i + 1][j],
                                       nums[j] + secondMove[i][j - 1]);

            secondMove[i][j] = Math.min(firstMove[i + 1][j],
                                        firstMove[i][j - 1]);
        }
    }

    private boolean dp () {
        int top = 0, left = 1;
        int down = n - 2, right = n - 1;

        for (; left <= n - 1 && down >= 0; left ++, down --){
            diagonalTraverse(top, left, down, right);
        }

        return firstMove[0][n - 1] > secondMove[0][n - 1];
    }

    private void init (int[] nums) {
        this.nums = nums;
        n = nums.length;
    }
}
