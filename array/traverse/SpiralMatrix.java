package array.traverse;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    /**
     * Link:
     * https://leetcode-cn.com/problems/spiral-matrix/
     *
     */
    int[][] matrix;
    int m;
    int n;
    List<Integer> res;
    public List<Integer> spiralOrder (int[][] metrix) {
        init(metrix);

        int y1 = 0;
        int x1 = 0;

        int y2 = m - 1;
        int x2 = n - 1;
        for (; res.size() < m * n; y1 ++, x1 ++, y2 --, x2 --) {
            collectBorder(y1, x1, y2, x2);
        }
        return res;
    }

    public void init (int[][] matrix) {
        this.matrix = matrix;
        m = matrix.length;
        n = matrix[0].length;
        res = new ArrayList<>();
    }

    public void collectBorder (int y1, int x1, int y2, int x2) {
        if (y1 == y2) {
            for (int x = x1; x <= x2; x++) {
                res.add(matrix[y1][x]);
            }
        } else if (x1 == x2) {
            for (int y = y1; y <= y2; y++) {
                res.add(matrix[y][x2]);
            }
        } else {
            for (int x = x1; x < x2; x++) {
                res.add(matrix[y1][x]);
            }

            for (int y = y1; y < y2; y++) {
                res.add(matrix[y][x2]);
            }

            for (int x = x2; x > x1; x --) {
                res.add(matrix[y2][x]);
            }

            for (int y = y2; y > y1; y --) {
                res.add(matrix[y][x1]);
            }
        }
    }
}
