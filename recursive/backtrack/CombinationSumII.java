package recursive.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class CombinationSumII {
    /**
     * Link:
     * https://leetcode-cn.com/problems/combination-sum-ii/
     *
     */

    int[] candidates;
    int target;
    int len;
    boolean[] set;

    List <List<Integer>> ans;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        init(candidates, target);

        Arrays.sort(candidates);
        set = new boolean[candidates[len - 1] + 1];

        combinationSum(0, target, new LinkedList<>());
        return ans;
    }

    private void combinationSum (int i, int rest, List <Integer> cur) {
        if (rest == 0) {
            ans.add(new ArrayList<>(cur));
            return;
        }

        if (i == len || rest - candidates[i] < 0) {
            return;
        }

        // 考慮使用這個數字字
        // 一開始遇到新的數字, 直接納入候選答案
        if (!set[candidates[i]]) {
            cur.add(candidates[i]);
            combinationSum(i + 1, rest - candidates[i], cur);
            cur.remove(cur.size() - 1);
        }

        // 已經考慮過所有以 candidates[i] 開頭的候選答案,
        // 將 candidates[i] 加入 set, 當遇到 candidates [i] 在 set 中, 跳過
        set[candidates[i]] = true;
        combinationSum(i + 1, rest, cur);
        set[candidates[i]] = false;
        // 這個數字已經考慮過所有情況, 從 set 中移除 candidates [i]
    }


    private void init (int[] candidates, int target) {
        this.candidates = candidates;
        this.target = target;
        len = candidates.length;
        ans = new LinkedList<>();
    }
}
