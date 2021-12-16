package dp;

public class CoinSum {

    public static int brute (int pickup, int value,int aim, int [] arr) {
        if (pickup == arr.length) {
            return value == aim ? 1 : 0;
        }

        return brute(pickup + 1, value, aim, arr) +
            brute(pickup + 1, value + arr[pickup], aim, arr);
    }

    public static void main(String[] args) {
        int [] arr = {2, 7, 3, 7, 5, 1};
        int aim = 10;

        System.out.println(brute(0, 0, aim, arr));
    }
}
