package recursive.dp;


public class HoseRoberII {
    /**
     * Link:
     * https://leetcode-cn.com/problems/house-robber-ii/
     *
     */

    int[] numns;
    int len;

    public int rob(int[] nums) {
        init(nums);
        // return Math.max(rob(0, len - 2), rob(1, len - 1));

        return dp ();
    }
    private void init (int[] nums) {
        this.numns = nums;
        len = nums.length;
    }

    private int rob (int i, int j) {
        if (i > j) {
            return 0;
        }

        int pickThis = numns[i] + rob(i + 2, j);
        int skipThis = rob(i + 1, j);

        return Math.max(pickThis, skipThis);
    }

    private int dp() {
        int [] pickFirst = new int [len + 2];
        int [] skipFirst = new int [len + 2];

        for (int i = len - 2; i >= 0; i --) {
            pickFirst[i] = Math.max(numns[i] + pickFirst[i + 2], pickFirst[i + 1]);
        }

        for (int i = len - 1; i >= 1; i --) {
            skipFirst[i] = Math.max(numns[i] + skipFirst[i + 2], skipFirst[i + 1]);
        }
        return Math.max(pickFirst[0], skipFirst[1]);
    }

    private int refineDP () {
        if (len == 1) {
            return numns[0];
        }

        int [] pickFirst = new int [3];
        int [] skipFirst = new int [3];

        for (int i = len - 2; i >= 0; i --) {
            pickFirst[mod3(i)] = Math.max(numns[i] + pickFirst[mod3(i + 2)],
                                          pickFirst[mod3(i + 1)]);
        }

        for (int i = len - 1; i >= 1; i --) {
            skipFirst[mod3(i)] = Math.max(numns[i] + skipFirst[mod3(i + 2)],
                                          skipFirst[mod3(i + 1)]);
        }

        return Math.max(pickFirst[0], skipFirst[1]);
    }

    private int mod3 (int i) {
        return i % 3;
    }
}
