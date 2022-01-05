package stack.monotonic;


public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        if (num.length() == k) {
            return "0";
        }


        int [] stack = new int[num.length()];

        int top = -1;
        int times = 0;
        for (int i = 0; i < num.length(); i++) {
            // remain a decrease monotone stack
            for (;top != -1 && times < k && num.charAt(stack[top]) > num.charAt(i); times ++) {
                top --;
            }
            stack[++ top] = i;
        }

        StringBuffer ans = new StringBuffer();
        for (int i = 0; i < num.length() - k; i++) {
            ans.append(num.charAt(stack[i]));
        }

        while (ans.charAt(0) == '0' && ans.length() > 1) {
            ans.deleteCharAt(0);
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        RemoveKDigits o = new RemoveKDigits();
        System.out.println(o.removeKdigits("12190000000000", 3));
    }
}
