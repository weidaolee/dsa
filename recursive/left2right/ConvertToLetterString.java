package recursive.left2right;

import java.util.LinkedList;
import java.util.List;

public class ConvertToLetterString {
    char[] chs;

    List <String> res = new LinkedList<>();
    public void convertToLetterString () {
        // count = countValid(chs, 0);

        convertToLetterString(chs, 0, "");
    }

    int count;
    public void countValid() {
        countValid(chs, 0);
    }

    public void convertToLetterString (char[] chs, int i, String curRes) {
        if (i == chs.length) {
            // i 來到 chs 最末端，直接 add
            res.add(curRes);
            return;
        }

        String s;
        if (chs[i] == '0') {
            //如果 '0' 出現在 chr [0...N - 1], 為不合法的 string, 直接 return
            return;


        } else if (chs[i] == '1') {
            // 如果 '1' 出現，chs[i] 可以組成 a, 也可以跟 chs[i, i + 1] 組成 jklmnopqrs
            s = parseToLetter(chs[i]);
            convertToLetterString(chs, i + 1, curRes + s); // 組成 b, 交給後面的 process
            if (i + 1 < chs.length) {
                s = parseToLetter(chs[i], chs[i + 1]);
                convertToLetterString(chs, i + 2, curRes + s); // 組成 jklmnopqrs, 交給後面的 process
            }

        } else if (chs[i] == '2') {
            // 如果 '2' 出現, chs [i] 可以組成 b, 也可以跟 chs[i, i + 1] 組成 tuvwxyz
            s = parseToLetter(chs[i]);
            convertToLetterString(chs, i + 1, curRes + s); // 組成 b, 交給後面的 process
            if (i + 1 < chs.length && '0' <= chs[i + 1] && chs[i + 1] <= '6') {
                s = parseToLetter(chs[i], chs[i + 1]);
                convertToLetterString(chs, i + 2, curRes + s); // 組成 tuvwxyz, 交給後面的 process
            }

        } else {
            s = parseToLetter(chs[i]);
            convertToLetterString(chs, i + 1, curRes + s);
        }
    }

    public String parseToLetter(char c) {
        int n = Integer.valueOf(Character.toString(c)) + 96;
        return Character.toString((char) n);
    }

    public String parseToLetter(char c1, char c2) {
        int n = Integer.valueOf(Character.toString(c1)) * 10;
        n += Integer.valueOf(Character.toString(c2));
        n += 96;
        return Character.toString((char) n);
    }

    public void countValid (char[] chs, int i) {
        /**
         * 只計算合法的字串
         *
         */
        if (i == chs.length) {
            // 來到端，count ++
            count ++;
        }
        else if (chs[i] == '0') {
            // 不合法的字串，不做任何事

        } else if (chs[i] == '1') {
            countValid(chs, i + 1);
            if (i + 1 < chs.length) {
                countValid(chs, i + 2);
            }

        } else if (chs[i] == '2') {
            countValid(chs, i + 1);
            if (i + 1 < chs.length && ('0' <= chs[i + 1] && chs[i + 1] <= '6')) {
                countValid(chs, i + 2);
            }
        } else {
            // chs [i] 介於 3 - 9
            countValid(chs, i + 1);
        }
    }

    public static void main(String[] args) {
        ConvertToLetterString ans = new ConvertToLetterString(2026);
        ans.convertToLetterString();
        for (String s : ans.res) {
            System.out.println(s);
        }

        ans.countValid();
        System.out.println(ans.count);
    }

	public ConvertToLetterString(int num) {
        this.chs = Integer.toString(num).toCharArray();
	}
}
