package string.manacher;

public class Manacher {
    /**
     * 1. 先把 string 補成 char [] = [#, s0, # ,s1, #, s2, #,...#], 這樣保證能找到偶數的 palindrome.
     * 2. 以 R 紀錄之前的最長 palindrome 的右邊界, C 紀錄其展開之中點, initial: R = C = -1
     * 3. 以 int [] radius 紀錄以 radius [i] 展開之最常 palindrome 半徑
     * ===================================================================
     * for i = 0, i < arr.length:
     *     case 1: (C .... R) i
     *         暴力擴，更新 R, C, radius [i]
     *     case 2-1: (C ... (l...i...r) ... R)
     *         i < R, 且 i 能張開的最長 palindrome 被 C - R 包住
     *         假設以 C 為對稱點, radius [i] 會等於 radius [i']
     *
     *         =========================================================
     *         proof: radius [i] = radius [i']
     *         在 (R' ... C ... R) 中，r < R,
     *         則 (R' ...(r... i'...r')... C ... (r'... i ...r) ... R)
     *         因為以 C 為對稱點，所以 (r... i'...r') = (r'... i ...r)
     *         又 (r'... i ...r) 已經最長，所以 (r' - 1) != (r + 1)
     *         ========================================================= 
     *     case 2-2: (C ......(l...i...r=R))
     *         i < R, 且 i 能張開的最長 palindrome 被 C - R 包住
     *         radius [i] 會等於 R - i 
     *
     *         =========================================================
     *         proof: radius [i] = R - i
     *         在 (R' ... C ... R) 中，r = R
     *         根據定義，R 為前一 palindrome 之最右邊界，如果 r > R
     *         則 (R' ... C ... R) 應為 (R + 1' ... C ... R + 1)
     *         所以 r <= R, 又如果 r < R, 則屬於 case 2-1
     *         =========================================================
     *     case 2-3: (C ...(r'.......i..R)...r )...)
     *         i < R, r > R
     *         至少知道，前面 i ... R 這一段的長度是已知，從 R + 1 的位置開始找 r
     */


    public static int maxLongestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s.length();
        }

        char [] arr = getManacherArray(s);
        int [] radius = new int [arr.length];

        int c = -1;   // center
        int r = -1;   // right
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            int j = 0;
            if (i > r) {
                while(i > j && i + j < arr.length && arr[i - j] == arr[i + j]) {
                    j ++;
                    radius[i] ++;
                }
            } else {
                radius[i] = Math.min(radius[2 * c - i], r - i);
                j = radius[i];
                while(i > j && i + j < arr.length && arr[i - j] == arr[i + j]) {
                    j ++;
                    radius[i] ++;
                }
            }
            if (i + radius[i] > r) {
                r = i + radius[i];
                c = i;
            }

            max = Math.max(max, radius[i]);
        }
        return max - 1;
    }


    public static char[] getManacherArray (String s) {
        char [] str = s.toCharArray();
        char [] arr = new char [str.length * 2 + 1];

        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            // arr[i] = i % 2 == 0 ? '#' : str[index ++];
            arr[i] = (i & 1) == 0 ? '#' : str[index ++];
        }

        return arr;
    }

    public static void main(String[] args) {
        int len = maxLongestPalindrome("");
        System.out.println(len);
    }
}
