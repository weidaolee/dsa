package sorting.nlogn.heap;

public class TestMaxumHeap {
    public static void main(String[] args) {
        int size = 36;
        int [] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * size);
        }

        // insert(arr, size * 2);
        // heapify();
        // constructorWithArray(arr);
        heapSort(arr);
    }

    public static void constructorWithArray(int [] arr) {
        MaxumHeap heap = new MaxumHeap(arr);
        heap.display("heapify");
    }

    public static void heapSort(int [] arr) {

        MaxumHeap heap = new MaxumHeap(arr);
        heap.display("heapify");
        for (int i = 0; i < arr.length; i++) {
            try {
				heap.heapify();
                heap.display("tail " + heap.tail);
                // heap.tail --;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        System.out.println("===========================");
        for (int i : heap.arr) {
            System.out.println(i);
        }
    }

    public static void insert(int [] arr, int maxSize) {
        MaxumHeap heap = new MaxumHeap(maxSize);

        for (int i = 0; i < arr.length; i ++) {
            heap.insert(arr[i]);
            // for (int j = 0; j <= heap.tail; j++) {
            //     heap.display("tail " + heap.tail);
            // }
        }
        heap.display("insert");
    }

    public static void heapify() {
        int [] arr = {2, 1};

        MaxumHeap heap = new MaxumHeap(1024);

        for (int i = 0; i < arr.length; i ++) {
            heap.insert(arr[i]);
        }

        try {
			int x = heap.heapify();
            heap.display("Heapify");
            System.out.printf("x %d %n", x);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
