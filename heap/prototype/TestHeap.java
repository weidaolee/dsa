package heap.prototype;

import java.util.Map.Entry;

public class TestHeap {


    public static void testAdd() {
        int [] data = new int [] {10, 3 , 2, 8 ,6 ,-4 ,1};
        // int [] ans = new int [data.length];

        Heap heap = new MinHeap(data.length, data);
        for (int i = 0; i < data.length; i++) {
            heap.add(i);
            System.out.println(heap.peekFirst());
            System.out.println(heap.map.get(i));
            System.out.println("");
        }
    }

    public static void testRemove() {
        int [] data = new int [] {10, 3 , 2, 8 ,6 ,-4 ,1};
        Heap heap = new MaxHeap(data.length, data);
        for (int i = 0; i < data.length; i++) {
            heap.add(i);
        }

        heap.remove(0);
        heap.remove(2);
        heap.remove(4);
        heap.remove(6);

        for (Entry<Integer, Integer> entry : heap.map.entrySet()) {
            int dataIndex = entry.getKey();
            int heapIndex = entry.getValue();
            System.out.printf("data index  %d,  heap index  %d, value  %d%n", dataIndex, heapIndex, data[dataIndex]);
        }


    }




    public static void main(String[] args) {
        testRemove();

    }
}
