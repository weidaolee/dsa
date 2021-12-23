package recursive.left2right;

import java.util.Collection;
import java.util.Stack;

public class ReverseStack {

    Stack <Integer> stack = new Stack <>();
    public ReverseStack (Collection <Integer> collection) {
        for (Integer i : collection) {
            stack.push(i);
        }
    }

    public void reverse() {
        if (stack.isEmpty()) {
            return;
        }
        int last = popLast();
        reverse();
        stack.push(last);

    }

    public int popLast() {
        int top = stack.pop();
        if (stack.isEmpty()) {
            return top;
        } else {
            int last = popLast();
            stack.push(top);
            return last;
        }
    }
}
