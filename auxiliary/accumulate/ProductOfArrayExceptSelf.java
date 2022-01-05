package auxiliary.accumulate;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {
    /**
     * https://leetcode-cn.com/problems/product-of-array-except-self/
     */

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int [] Lproduct = Arrays.copyOf(nums, len);
        int [] Rproduct = Arrays.copyOf(nums, len);

        for (int i = 1; i < len; i++) {
            Lproduct[i] *= Lproduct[i - 1];
        }

        for (int i = len - 2; i >= 0; i--) {
            Rproduct[i] *= Rproduct[i + 1];
        }

        nums[0] = 1 * Rproduct[1];
        nums[len - 1] = Lproduct[len - 2] * 1;

        for (int i = 1; i < len - 1; i++) {
            nums[i] = Lproduct[i - 1] * Rproduct[i + 1];
        }
        return nums;
    }
}
