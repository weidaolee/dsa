package recursive.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CombinationSumI {
    /**
     * Link:
     * https://leetcode-cn.com/problems/combination-sum/
     *
     */

    int[] candidates;
    int target;
    int len;
    List <List<Integer>> ans;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        init(candidates, target);
        combinationSum(0, target, new LinkedList<>());
        return ans;
    }

    public void combinationSum (int i, int rest, List <Integer> cur) {
        if (rest == 0) {

            // 如果直接 add 是 call-by-reference, 最後歸還硬幣時會把 ans 答案改到
            ans.add(new ArrayList<>(cur));
            return;
        }

        if (i == len) {
            return;
        }


        // 考慮不使用這個數字
        combinationSum(i + 1, rest, cur);

        // 考慮使用 K 次個這種數字
        int k;
        for (k = 1; k * candidates[i] <= rest; k ++) {
            cur.add(candidates[i]);
            combinationSum(i + 1, rest - k * candidates[i], cur);
        }

        // 考慮完後拿掉 K 次這種數字
        for (k = k * candidates[i] > rest ? k - 1 : k; k >= 1; k --) {
            cur.remove(cur.size() - 1);
        }
    }

    private void init (int[] candidates, int target) {
        this.candidates = candidates;
        this.target = target;
        len = candidates.length;
        ans = new LinkedList<>();
    }
}
