package recursive.dp;


public class MatrixMinPathSum {
    int[][] grid;
    int m;
    int n;

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        init(grid);

        return dp();
    }

    public int minPathSum(int i, int j) {
        if (i == m - 1 && j == n - 1) {
            return grid[i][j];
        }

        if (i > m - 1 || j > n - 1) {
            return Integer.MAX_VALUE;
        }

        return grid[i][j] + Math.min(minPathSum(i + 1, j), minPathSum(i, j + 1));
    }

    public int dp () {
        int [][] dp = new int[m][n];
        dp[m - 1][n - 1] = grid[m - 1][n - 1];

        for (int i = m - 2, j = n - 1; i >= 0; i --) {
            dp[i][j] = grid[i][j] + dp[i + 1][j];
        }


        for (int i = m - 1, j = n - 2; j >= 0; j --) {
            dp[i][j] = grid[i][j] + dp[i][j + 1];
        }

        for (int i = m - 2; i >= 0; i --) {
            for (int j = n - 2; j >= 0; j --) {
                dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
            }
        }

        return dp[0][0];
    }

    public int refineDP () {
        for (int i = m - 2, j = n - 1; i >= 0; i --) {
            grid[i][j] = grid[i][j] + grid[i + 1][j];
        }

        for (int i = m - 1, j = n - 2; j >= 0; j --) {
            grid[i][j] = grid[i][j] + grid[i][j + 1];
        }

        for (int i = m - 2; i >= 0; i --) {
            for (int j = n - 2; j >= 0; j --) {
                grid[i][j] = grid[i][j] + Math.min(grid[i + 1][j], grid[i][j + 1]);
            }
        }

        return grid[0][0];
    }


    public void init (int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
    }
}
