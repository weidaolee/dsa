package matrix;


public class FindNumberIn2DArray {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 && matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int y = 0;
        int x = n - 1;

        int Ly = m - 1;
        int Lx = 0;

        while (y <= Ly && x >= Lx) {
            int val = matrix[y][x];

            if (target < val)
                x --;
            else if (target > val)
                y ++;
            else
                return true;
        }
        return false;
    }
}
