package recursive.backtrack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class RestoreIPAddresses {
    /**
     * Link:
     * https://leetcode-cn.com/problems/restore-ip-addresses/submissions/
     *
     */

    List<String> ans;
    Deque<String> cur;
    int len;
    String s;

    public List<String> restoreIpAddresses(String s) {
        init(s);
        backtrack(0, 4);
        return ans;
    }

    private void backtrack(int begin, int restIP) {
        if (begin == len) {

            if (restIP == 0) {
                ans.add(String.join(".", cur));
            }
            return;
        }

        for (int k = begin; k <= begin + 2 && k < len; k++) {

            if (len - k + 1 > restIP * 3)
                continue;

            if (validSegment(begin, k)) {
                cur.addLast(s.substring(begin, k + 1));
                backtrack(k + 1, restIP - 1);
                cur.removeLast();
            }
        }
    }

    private boolean validSegment(int begin, int end) {
        if (end - begin + 1 > 1 && s.charAt(begin) == '0')
            return false;

        int number = end - begin + 1 <= 0 ? 0 : Integer.parseInt(s.substring(begin, end + 1));

        return 0 <= number && number <= 255;
    }

    private void init (String s) {
        this.s = s;
        len = s.length();
        ans = new LinkedList<>();
        cur = new ArrayDeque<>();
    }
}
