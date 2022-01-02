package recursive;

import java.util.Stack;

public class ImplementQueueUsingStack {
    Stack <Integer> stack = new Stack<>();


    public void push(int x) {
        if (empty()) {
            stack.push(x);
            return;
        }

        int top = pop();
        push(x);
        stack.push(top);
    }

    public int pop() {
        return stack.pop();
    }

    public int peek() {
        return stack.peek();
    }

    public boolean empty() {
        return stack.isEmpty();
    }

}
