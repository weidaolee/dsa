package slidingwindow;


public class LengthOfLongestSubstringTwoDistinct {
    /**
     * Link:
     * https://leetcode-cn.com/problems/longest-substring-with-at-most-two-distinct-characters/
     *
     */

    String s;
    int len;
    public int lengthOfLongestSubstringTwoDistinct (String s) {
        init(s);
        if (len < 3) {
            return len;
        }
        return window();
    }

    private int window () {
        int maxLen = 0;
        int[] times = new int[256];
        int diffChars = 0;
        int L = 0, R = 0;

        while (R < len) {

            // 更新 diffChars
            if (times[s.charAt(R)] == 0) {
                diffChars ++;
            }

            // 更新 R 當前 char 的 次數
            times[s.charAt(R)] ++;

            if (diffChars < 3) {
                // diffChars < 3, 代表 maxLen 要被更新
                maxLen = Math.max(maxLen, R - L + 1);
            } else {

                // diffChars == 3, 更新 L 的位置, 沿路移動並更新 times 中的次數
                while (L < R) {
                    times[s.charAt(L)] --;
                    if (times[s.charAt(L)] == 0) {
                        break;
                    }
                    L ++;
                    // L 可以前進的條件, 是 L 原來指向的 char 在 times > 0
                    // 且 L 的下一個位置還是指向原 char

                }
                // 此次更新 L 代表的是 L 來到新的值
                L ++;
                diffChars --;
            }
            R ++;
        }
        return maxLen;
    }

    private void init (String s) {
        this.s = s;
        len = s.length();
    }
}
