package recursive.backtrack;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class IncreasingSubsequences {
    /**
     * Link:
     * https://leetcode-cn.com/problems/increasing-subsequences/
     *
     */

    int[] nums;
    boolean[] used;
    int len;
    List <List<Integer>> ans;
    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums.length < 2) {
            return new LinkedList<>();
        }
        init(nums);

        findSubsequences(0, Integer.MIN_VALUE, new LinkedList<>());
        return ans;
    }

    /**
     * Main idea:
     *   如果出現重複的 value, 有 4 種可能:
     *   1 選前，    選後
     *   2 選前，    不選後
     *   3 不選前，  選後
     *   4 不選前，  不選後
     *
     *   其中 2, 3 會導致出現重複的解，又因為在 backtrack 時，會先遇到情況 3，
     *   因此排除情況 2:
     *     backtrack 後只有在 lastVal != nums[i] 時, 才把解往 len 送
     *
     */

    private void findSubsequences (int i, int lastVal,  Deque <Integer> cur) {
        if (i == len) {
            if (cur.size() > 1) {
                ans.add(new ArrayList<>(cur));
            }
            return;
        }

        if (lastVal <= nums[i]) {
            cur.addLast(nums[i]);
            findSubsequences(i + 1, nums[i], cur);
            cur.removeLast();
        }

        // 相等的情況已經考慮過了
        // 只在 lastVal != nums[i] 才把解往 len 送
        if (lastVal != nums[i]) {
            findSubsequences(i + 1, lastVal, cur);
        }

    }

    private void init (int[] nums) {
        this.nums = nums;
        len = nums.length;
        ans = new LinkedList<>();
        used = new boolean [len];
    }
}
