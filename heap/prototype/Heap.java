package heap.prototype;

import java.util.HashMap;
import java.util.Map;

public abstract class Heap {
    public int data[];
    public int heap[];
    public int tail;
    public Map <Integer, Integer> map;

    public Heap(int maxSize, int[] data) {
        this.data = data;
        map = new HashMap<>();
        heap = new int[maxSize];
        tail = -1;
    }

    public void add(int dataIndex) {
        heap[++ tail] = dataIndex;
        map.put(dataIndex, tail);
        heapInsert(tail);
    }

    public void remove(int dataIndex) {
        if (!map.containsKey(dataIndex)) {
            return;
        }
        int heapIndex = map.get(dataIndex);
        swap(heapIndex, tail --);
        heapify(heapIndex);
        map.remove(dataIndex);
    }

    public int pollFirst () {
        int dataIndex = peekFirst();
        remove(heap[0]);
        return dataIndex;
    }

    public int peekFirst() {
        return heap[0];
    }

    public int peekLast() {
        return heap[tail];
    }

    public int size() {
        return tail + 1;
    }

    public boolean isEmpty() {
        return tail == -1;
    }

    public boolean isFull() {
        return tail == heap.length - 1;
    }

    public void heapify (int i) {
        int left = 2 * i + 1;
        int next;
        while (left <= tail) {
            next = left + 1 <= tail && compare(left + 1) > compare(left) ? left + 1 : left;
            next = compare(next) > compare(i) ? next : i;
            if (next == i) {
                break;
            }

            swap(i, next);
            i = next;
            left = 2 * i + 1;
        }
    }

    public void heapInsert (int i) {
        while (compare(i) > compare((i - 1) / 2)) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    public void swap(int i, int j) {
        map.put(heap[i], j);
        map.put(heap[j], i);

        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
    public abstract int compare(int heapIndex);

}
