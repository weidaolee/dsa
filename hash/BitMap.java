package hash;

public class BitMap {
    int size;
    int [] arr;

	public BitMap() {
		this.size = 1;
        arr = new int[size];
	}

	public BitMap(int size) {
		this.size = size;
        arr = new int[size];
	}

    public int getBitStatus(int index) {
        if (index >= size) {
            return -1;
        }

        int numIndex = index / 32;
        int bitIndex = index % 32;

        return (arr[numIndex] >> bitIndex) & 1;
    }

    public void setBitStatus(int index, int setTo) {
        if (index >= size) {
            return;
        }

        if (setTo != 0 && setTo != 1) {
            return;
        }

        int numIndex = index / 32;
        int bitIndex = index % 32;

        if (setTo == 1) {
            arr[numIndex] = arr[numIndex] | (1 << bitIndex);
        }

        if (setTo == 0){
            arr[numIndex] = arr[numIndex] & (~ (1 << bitIndex));
        }
    }
}
