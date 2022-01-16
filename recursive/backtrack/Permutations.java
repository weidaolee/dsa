package recursive.backtrack;

import java.util.LinkedList;
import java.util.List;

public class Permutations {
    /**
     * Link:
     * https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
     *
     */

    char[] chs;
    int len;
    List <String> res;
    public String[] permutation(String s) {
        init(s);
        permutation(0, chs);

        return (String[]) res.toArray(new String[res.size()]) ;
    }

    private void permutation (int i, char[] chs) {
        if (i == len) {
            res.add(String.valueOf(chs));
            return;
        }

        boolean [] set = new boolean[128];
        for (int next = i; next < len; next++) {
            if (!set[chs[next]]) {
                set[chs[next]] = true;
                swap(i, next);
                permutation(i + 1, chs);
                swap(i, next);
            }
        }
    }

    private void swap (int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

    private void init (String s) {
        chs = s.toCharArray();
        len = chs.length;
        res = new LinkedList<>();
    }
}
