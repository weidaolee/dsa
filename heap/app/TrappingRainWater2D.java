package heap.app;


public class TrappingRainWater2D {
    int[][] h;
    int m;
    int n;

    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null) {
            return 0;
        }
        init(heightMap);
        if (m < 3 || n < 3) {
            return 0;
        }

        return 0;
    }

    public void init (int[][] heightMap) {
        h = heightMap;
        m = h.length;
        n = h[0].length;
    }
}
