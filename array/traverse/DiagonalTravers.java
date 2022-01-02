package array.traverse;

public class DiagonalTravers {
    int [][] mat;
    int [] ans;
    int m;
    int n;
    int tail;
    public int[] findDiagonalOrder(int[][] mat) {
        init(mat);

        int Ly = 0; int Lx = 0;
        int Uy = 0; int Ux = 0;

        int level = 0;

        for (; tail < m * n; level ++) {

            colloectDiagonal(Ly, Lx, Uy, Ux, level);
            Uy = Ux != n - 1 ? Uy : Uy + 1;
            Ux = Ux == n - 1 ? Ux : Ux + 1;

            Lx = Ly != m - 1 ? Lx : Lx + 1;
            Ly = Ly == m - 1 ? Ly : Ly + 1;
        }

        return ans;
    }

    public void colloectDiagonal (int Ly, int Lx, int Uy, int Ux, int level) {
        int y;
        int x;
        switch (level % 2) {
        case 0:
            y = Ly;
            x = Lx;

            for (; y >= Uy && x <= Ux; y --, x ++) {
                ans[tail ++] = mat[y][x];
            }
            break;

        case 1:
            y = Uy;
            x = Ux;

            for (; y <= Ly && x >= Lx; y ++, x --) {
                ans[tail ++] = mat[y][x];
            }
            break;
        }

    }

    public void init(int[][]mat) {
        this.mat = mat;
        m = mat.length;
        n = mat[0].length;
        ans = new int[m * n];
        tail = 0;
    }
}
