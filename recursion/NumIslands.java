package recursion;

public class NumIslands {
    /**
     * 從 grid [0][0] 第一格開使遍歷，如果 grid [i][j] = 1 的話，
     * 則進入遞迴感染程序 infect. 感染完成後 count + 1.
     *
     * 在遞迴感染程序中，因為確認 grid [i][j] 是 1，
     * 所以直接改成 2，然後對它的上下左右調用 infect
     *
     * 遞迴跳脫條件：
     * 1. i, j 越界
     * 2. grid [i][j] != 1
     *
     * 注意，count 這種只會在 grid[i,j] = 1 第一次調用 infect 時需要 + 1，
     * 所以 + 1 的邏輯應寫在 infect 之外。
     *
     * time: O (M N)
     * space: O (min (M, N))
     */

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int countIslands = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j ++) {
                // 只有在需要 infect 時才進行 infect
                if (grid[i][j] == '1') {
                    infect(grid, i, j, n, m);
                    // infect 完後，島嶼數量 + 1
                    countIslands ++;
                }
            }
        }
        return countIslands;
    }


    public void infect (char[][] grid, int i, int j, int n, int m) {
        if (i < 0 || j < 0 || i >= n || j >= m || grid [i][j] != '1') {
            return;
        }

        grid[i][j] = '2';

        infect(grid, i - 1, j, n, m);
        infect(grid, i + 1, j , n, m);
        infect(grid, i, j - 1 , n, m);
        infect(grid, i, j + 1 , n, m);
    }
}
