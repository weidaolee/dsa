package string.parentheses;


public class ValidParenthesisString {
    int len;
    char [] chs;

    int [] leftStack;
    int [] starStack;
    int topLeft;
    int topStar;
    public boolean checkValidString(String s) {
        init(s);
        return twoStack();
    }

    public boolean twoStack () {
        for (int i = 0; i < len; i++) {
            switch (chs[i]) {
            case '(':
                leftStack[++ topLeft] = i;
                break;
            case '*':
                starStack[++ topStar] = i;
                break;
            case ')':
                if (topLeft != -1) {
                    topLeft --;
                } else if (topStar != -1) {
                    topStar --;
                } else {
                    return false;
                }
                break;
            }
        }

        if (topLeft > topStar) {
            return false;
        }

        while (topLeft != -1) {
            int i = leftStack[topLeft --];
            int j = starStack[topStar --];
            if (i > j) {
                return false;
            }
        }
        return true;
    }

    public void init (String s) {
        chs = s.toCharArray();
        len = chs.length;
        leftStack = new int [len];
        starStack = new int [len];
        topLeft = -1;
        topStar = -1;
    }
}
