package recursive.backtrack;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;


public class CombinationSumIII {
    /**
     * Link:
     * https://leetcode-cn.com/problems/combination-sum-iii/
     *
     */

    int k;
    int n;
    List <List<Integer>> ans;
    public List<List<Integer>> combinationSum3(int k, int n) {
        if (n > (9 + 10 - k) * k / 2) {
            return new LinkedList<>();
        }

        init(k, n);
        combinationSum(1, n, new LinkedList<>());
        return ans;
    }

    private void combinationSum (int i, int rest, List <Integer> cur) {
        // 先捕捉答案, 否則會漏掉
        if (cur.size() == k && rest == 0) {
            ans.add(new ArrayList<>(cur));
            return;
        }

        if (i > 9 || cur.size() > k || rest - i < 0) {
            return;
        }

        cur.add(i);
        combinationSum(i + 1, rest - i, cur);
        cur.remove(cur.size() - 1);

        combinationSum(i + 1, rest, cur);
    }

    private void init (int k, int n) {
        this.k = k;
        this.n = n;
        ans = new LinkedList<>();
    }
}
