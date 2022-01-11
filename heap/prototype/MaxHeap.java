package heap.prototype;


public class MaxHeap extends Heap{
    public MaxHeap(int maxSize, int[] data) {
        super(maxSize, data);
    }

    @Override
    public int compare(int heapIndex) {
        return data[heap[heapIndex]];
    }
}
