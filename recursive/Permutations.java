package recursive;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

public class Permutations {
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }

        notUniqueVersion(0, nums);
        return res;
    }

    public void notUniqueVersion(int i, int[] nums) {
        if (i == nums.length) {
            // 在 nums 末端, 直接加到 res
            List<Integer> list = new LinkedList<>();
            for (Integer n : nums) {
                list.add(n);
            }
            res.add(list);
        }

        for (int j = i; j < nums.length; j ++) {
            // 不在 nums 末端，i 和 (i + 1, i + 2,..., N) 輪流當頭
            swap(i, j, nums);
            notUniqueVersion(i + 1, nums);
            swap(i, j, nums);
        }
    }

    public void uniqueVersion(int i, int[] nums) {
        if (i == nums.length) {
            // 在 nums 末端, 直接加到 res
            List<Integer> list = new LinkedList<>();
            for (Integer n : nums) {
                list.add(n);
            }
            res.add(list);
        }

        Set <Integer> set = new HashSet <>();
        for (int j = i; j < nums.length; j ++) {
            // 不在 nums 末端，i 和 (i + 1, i + 2,..., N) 輪流當頭
            if (!set.contains(nums[j])) {
                set.add(nums[j]);
                swap(i, j, nums);
                notUniqueVersion(i + 1, nums);
                swap(i, j, nums);
            }
        }
    }

    public void swap (int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
