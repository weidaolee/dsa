package stack.design;

import java.util.Arrays;
import java.util.Stack;

class MinStack {
    int top;
    int capacity;
    int[] dataStack;
    Stack <MinData>  miniStack;
    public MinStack() {
        top = -1;
        capacity = 16;
        dataStack = new int[capacity];
        miniStack = new Stack<>();
    }


    public void push(int val) {
        if (top + 1 == capacity) {
            capacity = 2 * capacity;
            dataStack = Arrays.copyOf(dataStack, capacity);
        }


        dataStack[++ top] = val;
        if (miniStack.isEmpty() || val < getMin()) {
            miniStack.push(new MinData(val, 1));
        } else {
            miniStack.peek().times ++;
        }

    }

    public void pop() {
        top --;

        if (miniStack.peek().times == 1) {
            miniStack.pop();
        } else {
            miniStack.peek().times --;
        }
    }

    public int top() {
        return dataStack[top];
    }

    public int getMin() {
        return miniStack.peek().val;
    }

    private static class MinData {
        int val;
        int times;

		public MinData(int val, int times) {
			this.val = val;
			this.times = times;
		}
    }
}
