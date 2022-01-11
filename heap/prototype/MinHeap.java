package heap.prototype;


public class MinHeap extends Heap{
    public MinHeap(int maxSize, int[] data) {
        super(maxSize, data);
    }

    @Override
    public int compare(int heapIndex) {
        return -data[heap[heapIndex]];
    }
}
