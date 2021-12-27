package parentheses;

import java.util.Map;
import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses {

    Stack <Character> stack;
    Map <Character, Character> map;

    public boolean isValid (String s) {
        if (s.length() % 2 == 1) {
            return false;
        }

        init();
        return isValid(s.toCharArray());
    }

    public boolean isValid (char[] chs) {
        if (!isLeftParenthesis(chs[0]) ) {
            return false;
        } else {
            stack.push(chs[0]);
        }

        int i;
        for (i = 1; i < chs.length; i++) {
            if (!isLeftParenthesis(chs[i])) {
                if (stack.isEmpty() || stack.peek() != map.get(chs[i])) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(chs[i]);
            }
        }
        return i < chs.length || !stack.isEmpty() ? false : true;
    }

    public boolean isLeftParenthesis (char c) {
        return c == '(' || c == '[' || c == '{' ? true : false;
    }

    public void init () {
        map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        stack = new Stack<>();
    }
}
