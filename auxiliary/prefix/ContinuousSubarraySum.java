package auxiliary.prefix;

import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {
    int[] nums;
    int k;
    public boolean checkSubarraySum(int[] nums, int k) {
        init(nums, k);
        return prefixSum();
    }

    private void init(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
    }

    private boolean prefixSum() {
        int pre = 0;
        Map <Integer, Integer> map = new HashMap<>();

        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            int rest = pre % k;
            if (map.containsKey(rest) && i > map.get(rest) + 1) {
                return true;
            } else if (!map.containsKey(rest)) {
                map.put(rest, i);
            }
        }
        return false;
    }
}
