package string.kmp;

public class KMP {

    public static int getSubstringIndex (String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] ch1 = s.toCharArray();
        char[] ch2 = m.toCharArray();

        int [] maxSameLengthTable = getMaxSameLengthTable(ch2);

        int L = 0;   // long string index
        int S = 0;   // short string index

        while (L < ch1.length && S < ch2.length) {
            if (ch1[L] == ch2[S]) {
                L ++;
                S ++;
                // 一路比對都相同, 我們有
                // ch1 [L - S...L] = ch2 [0...S]
            } else if (S > 0) {
                // ch1[L] 和 ch2[S] 不相同，
                // 理論上 ch1 要回到 ch1[L - S + 1], ch2 要回到 ch2[0] 繼續比。
                //
                // 但因為上一個 if, 我們沿路比較了 S 次, 前面 S - 1 次都相同,
                // 所以我們有：
                //
                // ch1[L - S ...L - 1] = ch2[0...S - 1]
                //
                // 我們透過 maxSameLengthTable T, T[S] = x
                //
                // 而 x <= S 的, 所以 L - 1 往前推 x 位, 跟 S - 1 往前推 x 位
                // 是相同的，所以我們有：
                //
                // (1) ch1[L - x ...L - 1] = ch2[S - x...S - 1]
                //
                // 根據 maxSameLengthTable 的定義 (記為 T)，
                // T[S] = x, 代表第 S 位置以前有 x 個和最前端相同 (不包含 S)，
                // 所以我們有：
                //
                // (2) ch2[0...x - 1] = ch2[S - x...S - 1],
                //
                // 結合 (1), (2), ch2 前端有 x 個和 ch1 的末端相同：
                //
                // ch1[L - x ... L - 1] = ch2[0...x - 1]
                //
                // 這相當於 ch1 和 ch2 已經經歷過 x 次比較都相同,
                //
                // 所以令 S = x, S 直接到 x 位置，跟 ch1[L] 比,
                S = maxSameLengthTable[S];
            } else {
                // S 已經來到 0 位置，代表從 ch1[L - S...L] 和 整個 ch2 不相同
                // L 往下一位和 ch2[0] 開始繼續比
                L ++;
            }
        }

        return S == ch2.length ? L - S : -1;
    }

   /**
    * maxSameLenghtTable = T []
    * T[i] = x, 滿足 s[0...x - 1] = s[i - x...i - 1]
    * 代表 第 i 個位置前面有 x 個和最前面是一樣的
    */
    public static int[] getMaxSameLengthTable (char[] chs) {
        if (chs.length == 1) {
            return new int[] { -1 };
        }

        int[] maxSameLengthTable =  new int[chs.length];
        maxSameLengthTable[0] = -1;
        maxSameLengthTable[1] = 0;

        int i = 2;
        int len = 0;

        while (i < chs.length) {
            // len 既是到 i - 1 位置以前的最長相同字串長度
            // 也是 chs 中的第 x 位置
            if (chs[i - 1] == chs[len]) {              // table[i] 要填 s[0...i-1] 的資訊
                maxSameLengthTable[i] = len + 1;
                i ++;
                len ++;
            } else if (len > 0) {
                len = maxSameLengthTable[len];
            } else {
                maxSameLengthTable[i] = 0;
                i ++;
            }
        }

        return maxSameLengthTable;
    }

    public static void main(String[] args) {
        System.out.println(KMP.getSubstringIndex("0123456789", "9"));
        System.out.println(KMP.getSubstringIndex("abcabcabcabc", "abcaa"));
    }
}
