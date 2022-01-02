package auxiliary.prefixsum;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumEqualsK {
    int[] nums;
    int k;
    public int subarraySum(int[] nums, int k) {
        init(nums, k);

        return prefixSum();
    }

    private void init(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
    }

    /**
     * Main idea:
     * i = 1 到 N, 找以 i 為起點, j 為中點的所有可能 nums[i,...j],
     * j 在遍歷時順便累加 sum, 使得 當 sum == k 時, 不必再遍歷第三次
     */
    public int subarraySum() {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    ans ++;
                }
            }

        }
        return ans;
    }


   /**
    * Main idea:
    *
    * pre[i] = nums[i] + pre[i - 1],
    *
    * pre[j - 1] - pre [i] = sum (nums[i,...,j - 1]), for j - 1 > i,
    *
    * let sum (nums[i,...,j - 1]) = k,
    *
    * pre[j - 1] - pre [i] = k, then we have:
    *
    * pre [i] = pre [j - 1] - k
    */

    public int prefixSum() {
        Map <Integer, Integer> map = new HashMap<>(); // Map prefix sum -> times

        int ans = 0, pre = 0;
        map.put(0, 1);
        for (int j = 0; j < nums.length; j++) {
            pre += nums[j];     //  pre[j - 1]
            if (map.containsKey(pre - k)) {     // qurey pre [i]
                ans += map.get(pre - k);        // exists pre [i] such that pre [j - 1] - pre [i] = k
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return ans;
    }
}
