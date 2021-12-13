package string.kmp;

public class KMP {

    public static int getSubStringIndex (String s, String m) {
        /**
         * s1 和 s2 一路比對到不相同，s1 原地不動，s2 去 next arr 找下一個可能不同的
         * 位置繼續比對，有兩種可能:
         *   1. 全都不同，沒得比 (next[i2] = -1)
         *   2. 有可能有相同，i2 移動到 next[i2]
         */

        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }

        char [] s1 = s.toCharArray();
        char [] s2 = m.toCharArray();

        int [] next = getNextArray(s2);

        int i1 = 0;
        int i2 = 0;

        while (i1 < s1.length && i2 < s2.length) {
            if (s1[i1] == s2[i2]) {
                i1 ++;
                i2 ++;
            } else if (next[i2] == -1) {
                i1 ++;
            } else {
                i2 = next[i2];
            }
        }
        return i2 == s2.length ? i1 - i2 : -1;
    }


    public static int[] getNextArray (char [] s) {
       /**
        * next arr 滿足其第 i 位以前，有幾個頭尾相同的位數:
        * e.g:
        * input: [ 1, 2, 3, 4, 5, 1, 2, 3, 5]
        * next : [-1, 0, 0, 0, 0, 0, 1, 2, ]
        */
        if (s.length == 1) {
            return new int [] { -1 };
        }

        int [] next = new int [s.length];
        next[0] = -1;
        next[1] = 0;

        int curNext = 0;
        int i = 2;

        while (i < s.length) {
            if (s[i - 1] == s[curNext]) {
                next[i ++] = ++ curNext;
            } else if (curNext > 0) {
                // 代表前面還有可能相同，可能可以往前推
                curNext = next[curNext];
            } else {
                // 代表沒有任何一個相同
                next[i ++] = 0;
            }
        }

        return next;
    }
}
