package stack.monotonestack;


public class RemoveDuplicateLetters {
    /**
     * 如果不在 stack 內, 一定要 push 進 stack, 但要考慮是否 pop,
     * pop 的規則是:
     * 1. 如果 top 的 times = 0, 代表這是唯一剩下的 char, 所以必須被保留
     * 2. 如果 top 的 char < cur char, 代表擁有較小的 dictionary order, 必須被保留
     *
     * 討論情況:
     *
     * 只出現過 1 次的 char 是否會被改變順序 ?
     *   let u = the unique char,
     *   1. 因為 u 不在 stack 內, 一定會被 push, 且 u.times 會 = 0
     *   2. 當下一個 char c 到來, 因為 u.times = 0, 一定會被保留
     *   因為在第一次遇到時被 push, 在以後遇到時不會被 pop, 所以一定不會改變順序
     *
     * 有重複出現過的 char, 如何出現在正確的位置 ?
     *   let d = duplicated char,
     *   1. 因為 d 不在 stack 內, 一定會被 push, 且 d.times 會少 1
     *   2. 接下來遇到的 char c, 考慮 d.order <, =, or > c.order:
     *      case d.order < c.order:
     *          代表 d 的 dictionary order 小於 c 的 dictionary order
     *          d 應該被保留, 而 c 會被 push 進 stack
     *      case d.order > c.order:
     *          代表 d 不應該出現在這裡, 所以 pop d, 由於 d.times > 0,
     *          代表還有下一次機會遇到 d, 而這個位置無論如何都要被讓出來
     *      case d.order = c.order:
     *          代表 d = c, 但由於我們一開始只考慮 c 不在 stack 內的情況,
     *          所以這個狀況我們不會遇到。
     */

    public String removeDuplicateLetters (String s) {
        int [] times = new int [26];
        boolean [] isInStack = new boolean [26];
        char [] stack = new char [s.length()];
        int top = -1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            times[c - 'a'] ++;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!isInStack[c - 'a']) {
                while (top != -1 && times[stack[top] - 'a'] > 0 && stack[top] > c) {
                    isInStack[stack[top--] - 'a'] = false;
                }
                isInStack[c - 'a'] = true;
                stack[++top] = c;
            }
            times[c - 'a'] --;
        }

        return String.valueOf(stack).trim();
    }

    public static void main(String[] args) {
        RemoveDuplicateLetters o = new RemoveDuplicateLetters();
        System.out.println(o.removeDuplicateLetters("bcabc"));
        System.out.println(o.removeDuplicateLetters("cbacdcbc"));

    }
}
