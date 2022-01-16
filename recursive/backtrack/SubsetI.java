package recursive.backtrack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SubsetI {
    /**
     * Link:
     * https://leetcode-cn.com/problems/subsets/
     *
     */

    int[] nums;
    int len;
    List <List <Integer>> ans;
    public List<List<Integer>> subsets(int[] nums) {
        init(nums);
        backtrack(0, new LinkedList<>());

        return ans;
    }

    private void backtrack (int i, Deque <Integer> cur) {
        if (i == len) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        backtrack(i + 1, cur);

        cur.addLast(nums[i]);
        backtrack(i + 1, cur);
        cur.removeLast();

    }

    private void init (int[] nums) {
        this.nums = nums;
        len = nums.length;
        ans = new LinkedList<>();
    }
}
