package recursive.classic;


public class SurroundedRegions {
    char[][] board;
    int m;
    int n;
    public void solve(char[][] board) {
        init(board);

        replaceBoarder('O', '$');

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] == 'O' ? 'X' : board[i][j];
            }
        }

        replaceBoarder('$', 'O');
    }

    public void init (char[][] board) {
        this.board = board;
        m = board.length;
        n = board[0].length;
    }

    public void replaceBoarder(char replace, char with) {
        for (int i = 0; i < m; i++) {
            infect(i, 0, replace, with);
            infect(i, n - 1, replace, with);
        }

        for (int j = 0; j < n; j++) {
            infect(0, j, replace, with);
            infect(m - 1, j, replace, with);
        }
    }

    public void infect (int i, int j, char replace, char with) {
        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] != replace) {
            return;
        }

        board[i][j] = with;
        infect(i - 1, j, replace, with);
        infect(i + 1, j, replace, with);
        infect(i, j - 1, replace, with);
        infect(i, j + 1, replace, with);
    }
}
