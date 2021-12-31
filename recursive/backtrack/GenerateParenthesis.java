package recursive.backtrack;

import java.util.List;
import java.util.ArrayList;

public class GenerateParenthesis {
    List <String> res;
    int n;
    public List<String> generateParenthesis(int n) {
        setGenerator(n);
        backtrack(new StringBuilder(), 0, 0);
        return res;
    }

    public void setGenerator (int n) {
        res = new ArrayList<>();
        this.n = n;
    }

    /**
     * n = 3 的有效序列:
     *    1. 左 '(' 有 3 個
     *    2. 右 ')' 有 3 個
     * 合法的添加規則:
     *    1. 左沒達到 n:
     *       1.1 append '(', 送進 backtrack
     *       1.2 出 backtrack 後要 s 還原
     *    2. 左比右多:
     *       2.1 append ')', 送進 backtrack
     *       2.2 出 backtrack 後要 s 還原
     *
     *    3. 中止情況:
     *       因為添加過程都是平衡的 string, 所以只須確認 numL 和 numR 都達到 n
     */

    public void backtrack(StringBuilder s, int numL, int numR) {
        if (numL == n && numR == n) {
            res.add(s.toString());
            return;
        }

        if (numL < n) {
            s.append('(');
            backtrack(s, numL + 1, numR);
            s.deleteCharAt(s.length() - 1);
        }

        if (numL > numR) {
            s.append(')');
            backtrack(s, numL, numR + 1);
            s.deleteCharAt(s.length() - 1);
        }
    }
}
