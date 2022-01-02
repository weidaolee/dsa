package string.parentheses;

public class LongestValidParentheses {
    char[] chs;
    public int longestValidParentheses(String s) {
        if (s.length() < 2) {
            return 0;
        }
        init(s);
        return dp();
    }
    /**
     * Define:
     *   dp[i] 代表以 i 結尾的最長合法字串:
     *
     *   if chs [i] 是 '(', 則 dp[i] 必為 0
     *   if chs [i] 是 ')',
     *      以 i - 1 結尾的最長合法子串的前一位 p, 若 chs[p] 是 '(',
     *      則 dp [i] 至少是 dp[i - 1] + 2 的長度,
     *      因為 dp[i - 1] + 2 之前可能也有合法字串,
     *      在這基礎上，考慮 p >= 0, p >= 1:
     *
     *      dp [i] = dp[i - 1] + 2;
     *      dp [i] += p > 0 ? dp[p - 1] : 0;
     *
     *      或:
     *
     *      dp [i] = dp[i - 1] + 2;
     *      dp [i] += i - dp[i] >= 0 ? dp[i - dp[i]] : 0;
     *
     */

    public int dp() {
        int max = 0;
        int [] dp = new int[chs.length];
        int p;
        for (int i = 1; i < dp.length; i++) {
            if (chs[i] == '(') {
                continue;
            }

            p = i - dp[i - 1] - 1;
            if (p >= 0 && chs[p] == '(') {
                dp[i] = dp[i - 1] + 2;
                dp[i] += p > 0 ? dp[p - 1] : 0;
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    public void init (String s) {
        chs = s.toCharArray();
    }
}
