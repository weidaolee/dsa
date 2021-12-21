package recursive.dp;

public class CoinsMinSum {
    public static int brute (int pickup, int rest, int [] arr) {
        /**
         * 從左往右，來到第 i 個 coin:
         * min(不選這個硬幣的解， 選這個硬幣的解)
         * = min (選第 i + 1 個硬幣的解，1 + 選第 i + 1 但扣掉當前硬幣的解)
         */
        if (rest < 0) {
            return -1;
        }

        if (rest == 0) {
            return 0;
        }

        if (pickup == arr.length) {
            return -1;
        }

        int r1 = brute(pickup + 1, rest, arr);
        int r2 =  brute(pickup + 1, rest - arr[pickup], arr);

        if (r1 == -1 && r2 == -1) {
            return -1;
        } else if (r1 != -1 && r2 != -1) {
            return Math.min(r1 , 1 + r2);
        } else if (r1 != -1){
            return r1;
        } else {
            return 1 + r2;
        }
    }

    public static int memorized (int aim, int [] arr) {
        int [][] dp = new int [arr.length + 1][aim + 1];

        for (int i = 0; i < dp.length; i ++) {
            for (int j = 0; j < dp[0].length; j ++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }
        return memory(0, aim, arr, dp);
    }

    public static int memory (int pickup, int rest, int [] arr, int[][] dp) {
        if (rest < 0) {
            return -1;
        }

        if (dp[pickup][rest] != Integer.MIN_VALUE) {
            return dp[pickup][rest];
        }

        if (rest == 0) {
            dp[pickup][rest] = 0;
            return dp[pickup][rest];
        }

        if (pickup == arr.length) {
            dp[pickup][rest] = -1;
            return dp[pickup][rest];
        }

        int r1 = memory(pickup + 1, rest, arr, dp);
        int r2 = memory(pickup + 1, rest - arr[pickup], arr, dp);

        if (r1 == -1 && r2 == -1) {
            dp[pickup][rest] = -1;
        } else if (r1 != -1 && r2 != -1) {
            dp[pickup][rest] =  Math.min(r1, 1 + r2);
        } else if (r1 != -1) {
            dp[pickup][rest] = r1;
        } else if (r2 != -1) {
            dp[pickup][rest] = 1 + r2;
        }

        return dp[pickup][rest];
    }

    public static int table (int aim, int [] arr) {
        int [][] dp = new int [arr.length + 1][aim + 1];

        for (int pickup = 0; pickup <= arr.length; pickup++) {
            dp[pickup][0] = 0;
        }

        for (int rest = 1; rest <= aim; rest++) {
            dp[arr.length][rest] = -1;
        }

        for (int pickup = arr.length - 1; pickup >= 0; pickup --) {
            for (int rest = 1; rest <= aim; rest ++) {

                int r1 = dp[pickup + 1][rest];
                int r2 = rest - arr[pickup] < 0 ? -1 : dp[pickup + 1][rest - arr[pickup]];

                if (r1 == -1 && r2 == -1 ) {
                    dp[pickup][rest] = -1;
                } else if (r1 != -1 && r2 != -1 ) {
                    dp[pickup][rest] = Math.min(r1, 1 + r2);
                } else if (r1 != -1) {
                    dp[pickup][rest] = r1;
                } else if (r2 != -1) {
                    dp[pickup][rest] = 1 + r2;
                }
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int [] arr = {2, 7, 3, 7, 5, 1};
        int aim = 10;

        System.out.println(brute(0, aim, arr));
        System.out.println(memorized(aim, arr));
        System.out.println(table(aim, arr));
    }
}
