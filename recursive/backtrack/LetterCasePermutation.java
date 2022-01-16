package recursive.backtrack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LetterCasePermutation {
    /**
     * Link:
     * https://leetcode-cn.com/problems/letter-case-permutation/
     *
     */

    String s;
    int len;
    List <String> ans;
    public List<String> letterCasePermutation(String s) {
        init(s);
        letterCasePermutation(0, new StringBuilder());
        return ans;
    }

    private void letterCasePermutation (int i, StringBuilder cur) {
        if (i == len) {
            ans.add(cur.toString());
            return;
        }
        char c = s.charAt(i);
        if (Character.isDigit(c)) {
            cur.append(c);
            letterCasePermutation(i + 1, cur);
            cur.deleteCharAt(cur.length() - 1);
        } else {
            cur.append(Character.toLowerCase(c));
            letterCasePermutation(i + 1, cur);
            cur.deleteCharAt(cur.length() - 1);

            cur.append(Character.toUpperCase(c));
            letterCasePermutation(i + 1, cur);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    private void init (String s) {
        this.s = s;
        len = s.length();
        ans = new LinkedList<>();
    }
}
