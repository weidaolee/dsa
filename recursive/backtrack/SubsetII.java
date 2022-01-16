package recursive.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SubsetII {
    /**
     * Link:
     * https://leetcode-cn.com/problems/subsets/
     *
     */

    int[] nums;
    int len;
    List <List <Integer>> ans;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        init(nums);
        backtrack(0, new LinkedList<>(), Integer.MIN_VALUE);

        return ans;
    }

    private void backtrack (int i, Deque <Integer> cur, int last) {
        if (i == len) {
            ans.add(new ArrayList<>(cur));
            return;
        }

        cur.addLast(nums[i]);
        backtrack(i + 1, cur, nums[i]);
        cur.removeLast();

        if (last != nums[i]) {
            backtrack(i + 1, cur, last);
        }
    }

    private void init (int[] nums) {
        this.nums = nums;
        len = nums.length;
        ans = new LinkedList<>();
        Arrays.sort(nums);
    }
}
