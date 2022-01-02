package recursive.classic;

import java.util.Stack;

public class SortedStack {

    Stack <Integer> stack;
    public SortedStack () {
        stack = new Stack<>();
    }

    public void push(int val) {
        if (stack.isEmpty() || stack.peek() >= val) {
            stack.push(val);
        } else {
            int tmp = stack.pop();
            push(val);
            stack.push(tmp);
        }
    }

    public void pop() {
        if (!isEmpty()) {
            stack.pop();
        }
    }

    public int peek() {
        return isEmpty() ? -1 : stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
