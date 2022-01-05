package auxiliary.accumulate;

public class LargestBoederSquare {
    /**
     * Link:
     * https://leetcode-cn.com/problems/largest-1-bordered-square/
     *
     * Main idea:
     * 先分析複雜度:
     *   1. 矩陣大小 N X N, 最多可以組成多少個矩形 ?
     *      矩陣中任取一點 a, 任取一點 b, 可以組成 1 子矩陣,
     *      a 有 N ^ 2 種選擇
     *      b 有 (N - 1) ^ 2 種選擇
     *      可組成的矩形數為 O (N ^ 4)
     *
     *      驗證是否為邊框為 1 的矩形, 要統計邊框的 1 數量，O (N ^ 5)
     *
     *   2. 矩陣大小 N X N, 最多可以組成多少個正方形 ?
     *      矩陣任取一點 a, b 只能選 N - a 的點，
     *      可組成的正方形數為 O (N ^ 3)
     *
     *      驗證是否為邊框為 1 的矩形, 要統計邊框的 1 數量, O (N ^ 4)
     *
     *   3. 先統計好邊寬, 可達到 O (N ^ 3)
     *
     * Prepare:
     * 準備 int [][] right, int [][] down
     *   right [row][col] 表示 grid 中右邊的 連續 1 個數
     *   down [row][col] 表示 grid 中下面的 連續 1 個數
     *   注意，當 累加數 和 連續數 都可以獲得答案時，考慮兩者中能包含更多資訊的。
     *
     * Implementation:
     * 來到某一點 p, 取 right[row][col] 和 down[row][col] 中短的 做為當前邊長 curLen 開始試：
     *   1. 如果 curLen <= maxLen 直接跳過不做
     *   2. let a = p[row + curLen - 1, col], 可以幫我們確定下面的長是否為連續 1
     *      let b = p[row, col + curLen - 1], 可以幫我們確定右邊的高是否為連續 1
     */

    public int largest1BorderedSquare(int[][] grid) {
        setBorderMap(grid);
        return getLargestBorderSize();
    }

    int [][] grid;
    int [][] right;
    int [][] down;

    int m;
    int n;

    public void setBorderMap (int[][] grid) {
        this.grid = grid;
        right = new int[grid.length + 1][grid[0].length + 1];
        down = new int[grid.length + 1][grid[0].length + 1];

        m = grid.length - 1;
        n = grid[0].length - 1;

        right[m][n] = grid[m][n];
        down[m][n] = grid[m][n];

        for (int row = m - 1; row >= 0; row --) {
            right[row][n] = grid[row][n];
            down[row][n] = grid[row][n] == 1 ? 1 + down[row + 1][n] : 0;
        }

        for (int col = n - 1; col >= 0; col --) {
            down[m][col] = grid[m][col];
            right[m][col] = grid[m][col] == 1 ? 1 + right[m][col + 1] : 0;
        }

        for (int row = m - 1; row >= 0; row --) {
            for (int col = n - 1; col >= 0; col --) {
                right[row][col] = grid[row][col] == 1 ? 1 + right[row][col + 1] : 0;
                down[row][col] = grid[row][col] == 1 ? 1 + down[row + 1][col] : 0;
            }
        }
    }

    public int getLargestBorderSize () {
        int maxLen = 0;
        int curLen = 0;
        int [] a = new int[2];
        int [] b = new int[2];

        for (int row = 0; row <= m; row ++) {
            for (int col = 0; col <= n; col ++) {
                curLen = Math.min(down[row][col], right[row][col]);
                if (curLen <= maxLen) {
                    continue;
                }

                for (; curLen > maxLen; curLen --) {
                    a[0] = row + curLen - 1;
                    a[1] = col;

                    b[0] = row;
                    b[1] = col + curLen - 1;

                    if (hasOneBorder(a, b, curLen)) {
                        maxLen = curLen;
                        break;
                    }
                }
            }
        }
        return maxLen * maxLen;
    }

    public boolean hasOneBorder (int[] a, int[] b, int curLen) {
        if (right[a[0]][a[1]] < curLen) {
            return false;
        }

        if (down[b[0]][b[1]] < curLen) {
            return false;
        }

        return true;
    }
}
