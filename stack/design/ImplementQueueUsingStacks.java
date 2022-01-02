package stack.design;

import java.util.Arrays;

public class ImplementQueueUsingStacks {


    ArrayStack addStack = new ArrayStack();
    ArrayStack pollStack = new ArrayStack();

    
    public void push(int x) {
        addStack.push(x);
    }

    public int pop() {
        if (empty()) {
            return -1;
        }

        if (!pollStack.isEmpty()) {
            return pollStack.pop();
        }

        while (!addStack.isEmpty()) {
            pollStack.push(addStack.pop());
        }

        return pollStack.pop();
    }

    public int peek() {
        if (empty()) {
            return -1;
        }

        if (!pollStack.isEmpty()) {
            return pollStack.peek();
        }

        while (!addStack.isEmpty()) {
            pollStack.push(addStack.pop());
        }

        return pollStack.peek();
    }

    public boolean empty() {
        return addStack.isEmpty() && pollStack.isEmpty();
    }

    private static class ArrayStack {
        public int top;
        public int capacity;
        public int[] data;


        public ArrayStack () {
            top = -1;
            capacity = 16;
            data = new int[capacity];
        }

        public void push (int val) {
            if (top + 1 == capacity) {
                capacity *= 2;
                data = Arrays.copyOf(data, capacity);
            }
            data[++ top] = val;
        }


        public int peek () {
            if (top == -1) {
                // throw new EmptyStackException ();
            }
            return  data[top];
        }


        public int pop () {
            if (top == -1) {
                // throw new EmptyStackException ();
            }
            return  data[top --];
        }

        public boolean isEmpty () {
            return top == -1 ? true : false;
        }
    }
}
