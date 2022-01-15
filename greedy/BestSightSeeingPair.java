package greedy;


public class BestSightSeeingPair {
    /**
     * Link:
     * https://leetcode-cn.com/problems/best-sightseeing-pair/
     *
     */

    int[] values;
    int len;
    public int maxScoreSightseeingPair(int[] values) {
        init(values);

        return decomposition();
    }

    private int maxScoreSightseeingPair() {
        int maxScore = 0;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len ; j++) {
                maxScore = Math.max(maxScore, values[i] + values[j] - (j - i));
            }
        }
        return maxScore;
    }

    /**
     * Main idea:
     *   max score
     *   = max {values[i] + values[j] - (j - i)}
     *   = max {values[i] + i + values[j] - j}
     *   = max {values[i] + i} + max {values[[j] - j]}
     */ 
    
    private int decomposition () {
        int maxScore = 0;
        int valueOfj = values[len - 1] - (len - 1);
        for (int i = len - 2; i >= 0; i --) {
            maxScore = Math.max(maxScore, values[i] + i + valueOfj);
            valueOfj = Math.max(valueOfj, values[i] - i);
        }
        return maxScore;
    }

    private void init (int[] values) {
        this.values = values;
        len = values.length;
    }
}
