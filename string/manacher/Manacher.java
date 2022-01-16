package string.manacher;


public class Manacher {
    /**
     * Link:
     * https://leetcode-cn.com/problems/longest-palindromic-substring
     *
     */

    String s;
    char[] arr;
    int maxLen;
    int begin;
    int end;
    int center;

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        init(s);

        longestPalindrome();

        StringBuilder ans = new StringBuilder();
        for (int i = begin; i <= end; i++) {
            if (arr[i] != '$') {
                ans.append(arr[i]);
            }
        }
        return ans.toString();
    }

    /**
     * Prepare:
     *   1. 先把 string 補成 [#, s0, #,...,#, sn, #], 以確保偶數的 palindrome 能捕捉到
     *   2. 以 int[] radius 紀錄以 i 為中心展開之最長 palindrome 半徑
     *   3. 以 R 紀錄之前最右 palindrome 的右邊界, C 紀錄其展開之中點
     *
     * Main idea:
     *   當來到 i 位置:
     *     case 1: (C...R) i
     *       說明 i 來到更遠的位置:
     *         1. 更新 C, R 更新到以 C 為中心的最遠位置
     *         2. 更新 radius[i]
     *
     *     case 2 - 1: (C...(l...i...r)...R)
     *       說明 i 落在 C...R 之間, 且 i 能撐開的最遠 palindrome 的 右邊界 r < R,
     *       此時 radius[i] = radius[i'], 因為 (R'...C) = (C...R), 對稱點 i'
     *       的最長半徑 radius[i'] 就是 radius[i] 的最長半徑。
     *
     *     case 2 - 2: (C...(l...i...r = R))
     *       說明 i 落在 C...R 之間, 且 i 能撐開的最遠 palindrome 的 右邊界 r = R,
     *       此時 radius[i] = R - i
     *
     *     case 2 - 3: (C...(l...i..R)..r)
     *       說明 i 落在 C...R 之間, 且 i 能撐開的最遠 palindrome 的 右邊界 r = R,
     *       至少 i ... R 這一段長度已知. 從 R + 1 位置開始找 r
     *
     */

    private void longestPalindrome () {
        int[] radius = new int[arr.length];
        int R = -1;
        int C = -1;

        maxLen = Integer.MIN_VALUE;
        begin = -1;
        end = 0;

        for (int i = 0 ; i < arr.length; i++) {
            int r = 0;
            if (i > R) {
                while (i > r && i + r < arr.length && arr[i - r] == arr[i + r]) {
                    r ++;
                    radius[i] ++;
                }
            } else {
                radius[i] = Math.min (radius[2 * C - i], R - i);
                r = radius[i];
                while (i > r && i + r < arr.length && arr[i - r] == arr[i + r]) {
                    r ++;
                    radius[i] ++;
                }
            }

            if (i + radius[i] > R) {
                R = i + radius[i];
                C = i;
            }

            if (maxLen < radius[i]) {
                maxLen = radius[i];
                center = i;
            }
        }

        maxLen --;
        begin = center - maxLen;
        end = center + maxLen;
    }

    private void init (String s) {
        arr = new char[s.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (i & 1) == 0 ? '$' : s.charAt(index ++);
        }
    }
}
