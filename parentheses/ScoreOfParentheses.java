package parentheses;


public class ScoreOfParentheses {
    /**
     * Assignment:
     * ()  -> 1 分
     * (A) -> 2 * A 的得分
     * AB  -> A 得分 + B 得分
     * 求任意合法 string 的得分
     *
     * Implementation:
     * 準備 1 stack:
     * 1. 遇到 (, push 0
     * 2. 遇到 ), score = pop ()
     *    如果 score = 0, 此時完成 (), score = 1
     *    如果 score = x, 此時完成 (A), score = 2 * score
     * 3. 完成第 2 步後
     *    3.1 如果 stack 為空，且 i 還沒來到 len,
     *        說明前面的序列形成 A, 後面的序列將形成 B, 以 sum 加合 AB:
     *           sum += score
     *    3.2 如果 stack 不為空，後面的序列將形成 (AB), 把 score 加給 stack.top:
     *           stack.push (stack,pop() + score)
     */

    public int scoreOfParentheses (String s) {
        return scoreOfParentheses(s.toCharArray());
    }

    public int scoreOfParentheses (char [] chs) {
        int score;
        int sum = 0;
        int top = -1;
        int[] stack = new int[chs.length];

        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == '(') {
                stack[++ top] = 0;
            } else {
                score = stack[top --];
                score = score == 0 ? 1 : 2 * score;

                if (top == -1) {
                    sum += score;
                } else {
                    stack[top] += score;
                }
            }
        }
        return sum;
    }
}
