package recursive.backtrack;

import java.util.LinkedList;
import java.util.List;

public class PalindromePermutations {
    String s;
    List <String> ans;

    int len;
    int odd;
    int [] pool;
    boolean canBePalindromized;

    public List <String> generatePalindromes(String s) {
        init(s);
        setPool();
        if (!canBePalindromized) {
            // return a empty list
            return ans;
        }

        char[] chs = getCharArray(pool, odd);
        generatePalindromes(0, chs);
        return ans;
    }

    public char [] getCharArray (int[] pool, int odd) {
        char[] chs = new char[len];
        if (len % 2 == 1) {
            // place the odd to the the center position
            chs[len / 2] = (char) (odd + 'a');
        }

        int i = 0;
        for (int abc = 0; abc < 26 && i < len / 2; ) {
            if (pool[abc] > 0) {
                chs[i ++] = (char) (abc + 'a');
                pool[abc] --;
            } else {
                abc ++;
            }
        }
        return chs;
    }

    public char[] reflect (char[] chs) {
        int len = chs.length;
        int i = len / 2 - 1;
        int j = len % 2 == 1 ? len / 2 + 1 : len / 2;

        for (; 0 <= i && j < len; i--, j++) {
            chs[j] = chs[i];
        }

        return chs;
    }

    public void generatePalindromes (int i, char[] chs) {
        if (i == len / 2) {
            chs = reflect(chs);
            ans.add(String.valueOf(chs));
            return;
        }

        boolean [] swapped = new boolean[26];
        for (int j = i ; j < chs.length / 2; j ++) {
            if (!swapped[chs[j] - 'a']) {
                swapped[chs[j] - 'a'] = true;
                swap(i, j, chs);
                generatePalindromes(i + 1, chs);
                swap(i, j, chs);
            }
        }
    }

    public void swap (int i, int j, char[] chs) {
        char c = chs[i];
        chs[i] = chs[j];
        chs[j] = c;
    }

    public void init (String s) {
        this.s = s;
        len = s.length();
        ans = new LinkedList<>();
    }

    public void setPool() {
        pool = new int [26];

        // Count the number of occurrences of letters
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            pool[c - 'a'] ++;
        }

        // Varify whethere the string can be palindromized
        int num = 0;
        odd = -1;
        for (int abc = 0; abc < 26; abc++) {
            if (pool[abc] % 2 == 1) {
                num ++;
                odd = abc;
            }
            // Only half numbers of each kind of letter is needed
            pool[abc] /= 2;
        }

        canBePalindromized = num <= 1 ? true : false;
        odd = num <= 1 ? odd : -1;
        pool = num <= 1 ? pool : null;
    }
}
