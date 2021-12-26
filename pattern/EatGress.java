package pattern;

public class EatGress {
    public String whoIsWinner (int num) {
        if (num <= 4) {
            return num == 0 || num == 2 ? "2" : "1";
        }

        int base = 1;
        while (base <= num) {
            if (whoIsWinner(num - base).equals("2")) {
                return "1";
            }
            if (base > num / 4) {
                break;
            }

            base *= 4;
        }
        return "2";

    }

    public String pattern (int num) {
        /**
         * 21211, 21211, 21211
         * 01234, 01234, 01234
         */
        num %= 5;
        return num == 0 || num == 2 ? "2" : "1";
    }

    public static void main(String[] args) {
        EatGress o = new EatGress();
        for (int i = 0; i <= 50; i++) {
            System.out.printf("num  %d, winner  %s%n", i, o.whoIsWinner(i));
        }
    }
}
