package recursive.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Combinations {
    /**
     * Link:
     * https://leetcode-cn.com/problems/combinations/
     *
     */

    int n;
    int k;
    List<List<Integer>> ans;
    public List<List<Integer>> combine(int n, int k) {
        init(n, k);

        backtrack(1, new LinkedList<>());
        return ans;
    }

    private void backtrack (int i, List <Integer> cur) {
        // 無法構造出 長度為 k 的組合
        if (cur.size() + (n - i + 1) < k) {
            return;
        }

        if (cur.size() == k) {
            ans.add(new ArrayList<>(cur));
            return;
        }

        cur.add(i);
        backtrack(i + 1, cur);
        cur.remove(cur.size() - 1);

        backtrack(i + 1, cur);
    }

    private void init (int n, int k) {
        this.n = n;
        this.k = k;
        ans = new LinkedList<>();
    }
}
