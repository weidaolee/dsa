package stack;

import java.util.Arrays;

public class ArrayStack <E> {
    public int top;
    public int capacity;
    public Object[] data;


	public ArrayStack () {
		top = -1;
		capacity = 16;
		data = new Object[capacity];
	}

    public void push (E e) {
        if (top + 1 == capacity) {
            capacity *= 2;
            data = Arrays.copyOf(data, capacity);
        }
        data[++ top] = e;
    }

    @SuppressWarnings("unchecked")
    public E peek () {
        if (top == -1) {
            // throw new EmptyStackException ();
        }
        return (E) data[top];
    }

    @SuppressWarnings("unchecked")
    public E pop () {
        if (top == -1) {
            // throw new EmptyStackException ();
        }
        return (E) data[top --];
    }

    public boolean isEmpty () {
        return top == -1 ? true : false;
    }
}
