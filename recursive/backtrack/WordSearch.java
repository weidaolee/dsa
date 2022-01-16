package recursive.backtrack;


public class WordSearch {
    /**
     * Link:
     * https://leetcode-cn.com/problems/word-search/
     *
     */

    char[][] board;
    boolean [][] visited;
    int[][] searchSpace;
    String word;
    int m;
    int n;

    public boolean exist(char[][] board, String word) {
        init(board, word);

        int[] boardCounts = new int[128];
        int[] wordCounts = new int[128];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boardCounts[board[i][j]] ++;
            }
        }

        for (int i = 0; i < word.length(); i++) {
            wordCounts[word.charAt(i)] ++;
        }

        for (int i = 0; i < 128; i++) {
            if (boardCounts[i] < wordCounts[i]) {
                return false;
            }
        }


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j ++) {
                if (check(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean check (int i, int j, int k) {
        if (board[i][j] != word.charAt(k)) {
            return false;
        } else if (k == word.length() - 1) {
            return true;
        }

        visited[i][j] = true;
        for (int[] direction : searchSpace) {
            int nextI = i + direction[0];
            int nextJ = j + direction[1];

            if (nextI >= 0 && nextJ >= 0 && nextI < m && nextJ < n && !visited[nextI][nextJ]) {
                if (check(nextI, nextJ, k + 1)) {
                    return true;
                }
            }
        }

        visited[i][j] = false;
        return false;
    }

    private void init (char[][] board, String word) {
        this.board = board;
        this.word = word;
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];
        searchSpace = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    }
}
