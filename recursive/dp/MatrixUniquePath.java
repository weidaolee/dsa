package recursive.dp;


public class MatrixUniquePath {
    int m;
    int n;
    public int uniquePaths(int m, int n) {
        init(m, n);
        return dp();
    }

    public void init (int m, int n) {
        this.m = m;
        this.n = n;
    }

    public int move (int i, int j) {
        if (i == m && j == n) {
            return 1;
        }

        if (i == m) {
            return move(i, j + 1);
        }

        if (j == n) {
            return move(i + 1, j);
        }

        return move(i + 1, j) + move(i, j + 1);
    }

    public int dp () {
        int [][] dp = new int[m + 1][n + 1];
        dp[m][n] = 1;

        for (int i = m - 1, j = n; i >= 1; i --) {
            dp[i][j] = dp[i + 1][j];
        }

        for (int i = m, j = n - 1; j >= 1; j --) {
            dp[i][j] = dp[i][j + 1];
        }

        for (int i = m - 1; i >= 1; i --) {
            for (int j = n - 1; j >= 1; j --) {
                dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
            }
        }
        return dp[1][1];
    }
}
