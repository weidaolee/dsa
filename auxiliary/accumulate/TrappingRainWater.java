package preprocess;


public class TrappingRainWater {
    /**
     * Main idea:
     * 來到第 i 位置,
     *   求 0 ~ i - 1 位置的最大值 Lmax,
     *   求 i + 1 ~ N - 1 位置的最大值 Rmax,
     *   第 i 位置的 bottleneck = min {Lmax, Rmax}, 因為會從矮的地方流走
     *   第 i 位置的 water = max (bottleneck - height[i], 0),
     *   因為如果第 i 位置的 height 高於 bottleneck, 當前累積的 water = 0
     */


    int[] height;
    int len;
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }

        setHeight(height);
        return best();
    }

    public void setHeight (int[] height) {
        this.height = height;
        len = height.length;
    }
    public int brute () {
        int water = 0;
        for (int i = 1; i <= len - 2; i++) {

            int Lmax = 0;
            for (int L = 0; L < i; L++) {
                Lmax = Math.max(Lmax, height[L]);
            }

            int Rmax = 0;
            for (int R = i + 1; R < len; R++) {
                Rmax = Math.max(Rmax, height[R]);
            }

            int bottleneck = Math.min(Lmax, Rmax);
            water += Math.max(bottleneck - height[i], 0);
        }
        return water;
    }

    public int considerPreprocessing() {
        int [] Lmax = new int[len];
        int [] Rmax = new int[len];

        Lmax[0] = 0;
        for (int i = 1; i < len; i++) {
            Lmax[i] = Math.max(Lmax[i - 1], height[i - 1]);
        }

        Rmax[len - 1] = 0;
        for (int i = len - 2; i >= 0; i --) {
            Rmax[i] = Math.max(Rmax[i + 1], height[i + 1]);
        }

        int water = 0;
        for (int i = 1; i <= len - 2; i++) {
            int bottlenect = Math.min(Lmax[i], Rmax[i]);
            water += Math.max(bottlenect - height[i], 0);
        }
        return water;
    }

    public int best() {
        int Lmax = height[0];
        int Rmax = height[len - 1];

        int L = 1;
        int R = len - 2;

        int water = 0;
        while (L <= R) {
            if (Lmax <= Rmax) {
                water += Math.max(Lmax - height[L], 0);
                Lmax = Math.max(Lmax, height[L ++]);
            } else {
                water += Math.max(Rmax - height[R], 0);
                Rmax = Math.max(Rmax, height[R --]);
            }
        }
        return water;
    }
}
