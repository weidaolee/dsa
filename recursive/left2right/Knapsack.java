package recursive.left2right;

public class Knapsack {
    int[] weights;
    int[] values;
    int bag;

    public int maxValue (int[] weights, int[] values, int bag) {
        init(weights, values, bag);
        int v = put(0, 0);
        return v;
    }

    public void init (int[] weights, int[] values, int bag) {
        this.weights = weights;
        this.values = values;
        this.bag = bag;
    }

    public int put(int i, int loading) {
        if (i == weights.length) {
            return 0;
        }

        if (loading + weights[i] > bag) {
            return 0;
        }

        int w = weights[i];
        int v = values[i];

        int skipThis = put(i + 1, loading);
        int pickThis = w + loading <= bag ? v + put(i + 1, loading + w) : skipThis;

        return Math.max(pickThis, skipThis);
    }

    public static void main(String[] args) {
		int[] weights = {2, 2, 4};
		int[] values = {3, 2, 4};
		int bag = 4;

        System.out.println(new Knapsack().maxValue(weights, values, bag));

    }
}
