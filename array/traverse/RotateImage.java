package array.traverse;


public class RotateImage {
    int[][] matrix;
    int n;

    public void rotate(int[][] matrix) {
        init(matrix);
        int y1 = 0;
        int x1 = 0;
        int y2 = n - 1;
        int x2 = n - 1;

        for (; y1 < y2 && x1 < x2 ; y1 ++, x1 ++, y2 --, x2 --) {
            rotatBorder(y1, x1, y2, x2);
        }
    }

    public void rotatBorder (int y1, int x1, int y2, int x2) {
        int times = x2 - x1;
        int tmp;
        for (int t = 0; t != times; t ++) {
            tmp = matrix[y1][x1 + t];
            matrix[y1][x1 + t] = matrix[y2 - t][x1];
            matrix[y2 - t][x1] = matrix[y2][x2 - t];
            matrix[y2][x2 - t] = matrix[y1 + t][x2];
            matrix[y1 + t][x2] = tmp;
        }
    }

    public void init (int[][] matrix) {
        this.matrix = matrix;
        n = matrix.length;
    }
}
