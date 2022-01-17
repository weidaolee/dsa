package string;


public class MultplyStrings {
    /**
     * Link:
     * https://leetcode-cn.com/problems/multiply-strings/
     *
     */

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        return dp(num1, num2);
    }

    private String straightMultply (String num1, String num2) {
        String res = "0";
        String mul;
        for (int i = num2.length() - 1; i >= 0; i --) {
            mul = digitWiseMultiply(num1, num2, i);
            res = addTwoStringNumbers(res, mul);
        }
        return res;
    }

    private String dp (String num1, String num2) {
        int[] dp = new int[num1.length() + num2.length()];
        int mul;
        int a, b;
        for (int i = num2.length() - 1; i >= 0; i --) {
            a = num2.charAt(i) - '0';
            for (int j = num1.length() - 1; j >= 0; j --) {
                b = num1.charAt(j) - '0';
                mul = a * b + dp[i + j + 1];
                dp[i + j + 1] = mul % 10;
                dp[i + j] += mul / 10;
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = dp[0] == 0 ? 1 : 0; i < dp.length; i++) {
            ans.append(dp[i]);
        }

        return ans.toString();
    }

    private String digitWiseMultiply (String num1, String num2, int index) {
        StringBuilder res = new StringBuilder();
        for (int i = index; i < num2.length() - 1; i++) {
            res.append("0");
        }

        int mul;
        int a, b = num2.charAt(index) - '0';
        for (int i = num1.length() - 1, carry = 0;
           i >= 0 || carry != 0;
           i --) {
            a = i < 0 ? 0 : num1.charAt(i) - '0';
            mul = a * b + carry;
            res.append(mul % 10);
            carry = mul / 10;
        }
        return res.reverse().toString();
    }

    private String addTwoStringNumbers (String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int sum;
        int a, b;
        for (int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
           i >= 0 || j >= 0 || carry != 0;
           i --, j --) {
            a = i < 0 ? 0 : num1.charAt(i) - '0';
            b = j < 0 ? 0 : num2.charAt(j) - '0';

            sum = a + b + carry;
            res.append(sum % 10);
            carry = sum / 10;
        }
        return res.reverse().toString();
    }
}
