package slidingwindow;

public class SubarrayProductLessThanK {
    /**
     * Link:
     * https://leetcode-cn.com/problems/subarray-product-less-than-k/
     *
     */

    int[] nums;
    int len;
    int k;
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k < 2) {
            return 0;
        }
        init(nums, k);
        return window();
    }

    private void init(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
        len = nums.length;
    }

    private int brute () {
        int ans = 0;

        int pre = 1;
        for (int i = 0; i < len - 1; i++) {
            int cur = pre;
            for (int j = i; j < len && cur < k; j++) {
                cur *= nums[j];
                ans ++;
            }
        }
        return ans;
    }

    private int window () {
        int ans = 0;
        int L = 0, R = 0;
        int product = 1;
        while (R < len) {
            product *= nums[R];
            while (product >= k && L < len) {
                product /= nums[L ++];
            }
            ans += L <= R ? R - L + 1 : 0;
            R ++;
        }
        return ans;
    }
}
