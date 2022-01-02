package stack.design;

import java.util.Arrays;

public class SortedStack {

    ArrayStack  dataStack;
    ArrayStack  helpStack;
    public SortedStack() {
        dataStack = new ArrayStack();
        helpStack = new ArrayStack();
    }

    public void push(int val) {
        while(!dataStack.isEmpty() && dataStack.peek() < val) {
            helpStack.push(dataStack.pop());
        }

        dataStack.push(val);

        while(!helpStack.isEmpty()) {
            dataStack.push(helpStack.pop());
        }
    }

    public void pop() {
        if (!dataStack.isEmpty()) {
            dataStack.pop();
        }

    }

    public int peek() {
        return dataStack.isEmpty() ? -1 : dataStack.peek();
    }

    public boolean isEmpty() {
        return dataStack.isEmpty();
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
