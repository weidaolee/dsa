package recursive.dp;


public class DecodeWays {
    String s;
    int len;

    public int numDecodings(String s) {
        init(s);
        return refineDp();
    }

    public int refineDp () {
        int[] dp = new int[3];
        dp[mode3(len)] = 1;
        dp[mode3(len - 1)] = s.charAt(len - 1) == '0' ? 0 : 1;

        for (int i = len - 2; i >= 0; i--) {
            int sum = dp[mode3(i + 1)];
            switch (s.charAt(i)) {
            case '0':
                sum = 0;
                break;
            case '1':
                sum += dp[mode3(i + 2)];
                break;
            case '2':
                sum += '0' <= s.charAt(i + 1) && s.charAt(i + 1) <= '6' ? dp[mode3(i + 2)] : 0;
                break;
            }
            dp[mode3(i)] = sum;
        }

        return dp[0];
    }


    public int dp () {
        int[] dp = new int[len + 1];
        dp[len] = 1;
        dp[len - 1] = s.charAt(len - 1) == '0' ? 0 : 1;

        for (int i = len - 2; i >= 0; i--) {
            int sum = dp[i + 1];
            switch (s.charAt(i)) {
            case '0':
                sum = 0;
                break;
            case '1':
                sum += dp[i + 2];
                break;
            case '2':
                sum += '0' <= s.charAt(i + 1) && s.charAt(i + 1) <= '6' ? dp[i + 2] : 0;
                break;
            }
            dp[i] = sum;
        }
        return dp[0];
    }

    private int mode3 (int i) {
        return i % 3;
    }

    public int decodeWays (int i) {
        if (i == len) {
            return 1;
        }

        if (s.charAt(i) == '0') {
            return 0;
        }

        int sum = decodeWays(i + 1);
        if (i + 2 <= len) {
            switch (s.charAt(i)) {
            case '1':
                sum += decodeWays(i + 2);
                break;
            case '2':
                if ('0' <= s.charAt(i + 1) && s.ch <= '6') {
                    sum += decodeWays(i + 2);
                }
                break;
            }
        }
        return sum;
    }

    public void init (String s) {
        this.s = s;
        len = s.length();
    }
}
