package heap;

public class MaxumHeap {
    int [] arr;
    int tail = -1;
    int numOfSwap = 0;

	public MaxumHeap(int maxSize) {
        this.arr = new int [maxSize];
	}

    public MaxumHeap(int[] arr) {
		this.arr = arr;
        tail = arr.length - 1;
        int i = tail;
        while (i > -1) {
            try {
				heapify(i--);
                tail = arr.length - 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        System.out.println(tail);
	}

    public int leftChildIndex(int i) {
        return 2 * i + 1;
    }

    public int rightChildIndex(int i) {
        return leftChildIndex(i) + 1;
    }

    public int parentIndex(int i) {
        return (i - 1) / 2;
    }

    public int largerChildIndex(int i) {
        int left = leftChildIndex(i);
        if (left > tail) {
            return -1;
        } else if (left == tail){
            return left;
        }

        if (arr[left] > arr[left + 1]) {
            return left;
        } else {
            return left + 1;
        }
    }

    public void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        numOfSwap ++;
    }

    public void insert(int value) {
        if (tail  < arr.length - 1) {
            arr[++tail] = value;
            numOfSwap ++;

            int i = tail;
            while (i > 0 && arr[i] > arr[parentIndex(i)]) {
                swap(i, parentIndex(i));
                i = parentIndex(i);
            }
        }
    }

    public int heapify() throws Exception {
        int res = heapify(0);
        return res;
    }

    public int heapify(int index) throws Exception {
        int res = arr[index];
        if (index < tail) {
            int i = index;
            swap(i, tail --);
            int largerChildID = largerChildIndex(i);
            while (largerChildID != -1 && arr[largerChildID] > arr[i]) {
                swap(i, largerChildID);
                i = largerChildID;
                largerChildID = largerChildIndex(i);
            }
            return res;
        } else if (index == tail) {
            tail --;
            return res;
        } else {
            throw new Exception("Heap size < 1: index " + index + " tail " + tail);
        }
    }

    public void display(String s) {
        System.out.printf("=========== %s ===========%n", s);
        for (int i = 0; i <= tail; i++) {
            System.out.printf("i %d value %d %n", i, arr[i]);
        }
        System.out.println("Number of swaps: " + numOfSwap);
    }

    public void display() {
        String s = "";
        display(s);
    }
}
/*
     [0]
   [1]   [2]
 [3] [4]
 */
