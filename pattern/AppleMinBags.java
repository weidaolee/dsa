package pattern;

public class AppleMinBags {
    public int[] brute (int num, int bagA, int bagB) {
        int [] res = new int [] {-1, -1};

        if (num < bagA || num < bagB) {
            return res;
        }

        int sizeA = Math.max(bagA, bagB);
        int sizeB = sizeA == bagA ? bagB : bagA;

        int rest = num % sizeA;
        int numA = num / sizeA;
        int numB = 0;

        int lcm = lcm(sizeA, sizeB);

        while (numA >= 0 && rest < lcm) {
            numB = useBagB (sizeB, rest);
            if (numB == -1) {
                numA -= 1;
                rest += sizeA;
                continue;
            } else {
                res[0] = numA;
                res[1] = numB;
                return res;
            }
        }
        return res;
    }

    public int useBagB (int sizeB, int rest) {
        int count = 0;
        while (rest > 0) {
            rest -= sizeB;
            count ++;
        }
        return rest == 0 ? count : -1;
    }

    public int lcm(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }

        a = Math.abs(a);
        b = Math.abs(b);

        int higher = Math.max(a, b);
        int lower = higher == b ? a : b;

        int lcm = higher;
        while (lcm % lower != 0) {
            lcm += higher;
        }

        return lcm;
    }

    public static void main(String[] args) {
        AppleMinBags o = new AppleMinBags();

        for (int i = 0; i < 100; i++) {
            int num = i;
            int [] arr = o.brute(i, 8, 6);
            System.out.printf("Numbers of Apple: %d  Bag A: %d  Bag B: %d %n",num, arr[0], arr[1]);
        }
    }
}
