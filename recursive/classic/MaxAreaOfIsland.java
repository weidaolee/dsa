package recursive;


public class MaxAreaOfIsland {
    int[][] grid;
    int m;
    int n;

    public int maxAreaOfIsland (int[][] grid) {
        init(grid);
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, infect(i, j, 0));
                }
            }
        }
        return max;
    }


    public void init (int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
    }

    public int infect(int i, int j, int sum) {
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] != 1) {
            return sum;
        }

        sum += 1;
        grid[i][j] = 0;

        sum = infect(i - 1, j, sum);
        sum = infect(i + 1, j, sum);
        sum = infect(i, j - 1, sum);
        sum = infect(i, j + 1, sum);
        return sum;
    }
}
