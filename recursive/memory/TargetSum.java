package recursive.memory;

import java.util.HashMap;
import java.util.Map;

public class TargetSum {
    /**
     * Link:
     * https://leetcode-cn.com/problems/target-sum/
     *
     */

    int[] nums;
    int target;
    int len;
    Map <String, Integer> memory;
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        if (Math.abs(target) > Math.abs(sum)) {
            return 0;
        }

        init(nums, target);

        return memory();
    }

    private int targetSumWays (int i, int sum) {
        if (i == len) {
            return sum == target ? 1 : 0;
        }
        return targetSumWays(i + 1, sum + nums[i]) + targetSumWays(i + 1, sum - nums[i]);
    }

    private int memory () {
        memory = new HashMap<>();
        return memory(0, 0);
    }

    private int memory (int i, int sum) {
        /**
         * reference:
         * https://leetcode-cn.com/problems/target-sum/solution/gong-shui-san-xie-yi-ti-si-jie-dfs-ji-yi-et5b/
         *
         */

        String key = i + "_" + sum;
        if (memory.containsKey(key)) {
            return memory.get(key);
        }
        int ways;
        if (i == len) {
            ways = sum == target ? 1 : 0;
            memory.put(key, ways);
            return ways;
        } else {
            ways = memory(i + 1, sum - nums[i]) + memory(i + 1, sum + nums[i]);
            memory.put(key, ways);
            return ways;
        }
    }

    private void init (int[] nums, int target) {
        this.nums = nums;
        len = nums.length;
        this.target = target;
    }
}
