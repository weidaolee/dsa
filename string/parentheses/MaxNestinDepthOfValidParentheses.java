package string.parentheses;

public class MaxNestinDepthOfValidParentheses {


    public int[] maxDepthAfterSplit(String seq) {
        int [] res = new int[seq.length()];
        char [] chs = seq.toCharArray();

        int depth = 0;
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == '(') {
                depth ++;
                res[i] = depth % 2;
            } else {
                res[i] = depth % 2;
                depth --;
            }
        }
        return res;
    }
}
