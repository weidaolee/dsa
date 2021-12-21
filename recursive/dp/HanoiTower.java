package recursive.dp;

public class HanoiTower {
    public static void force(int i, String start, String end, String other) {
        if (i == 1) {
            System.out.printf("Move 1 from %s to %s %n", start, end);
        } else {
            force(i - 1 , start, other, end);
            System.out.printf("Move %d from %s to %s %n",i, start, end);
            force(i - 1, other, end, start);
        }
    }

    public static void hanoi (int n) {
        force(n, "A", "C", "B");
    }

    public static void main(String[] args) {
        hanoi(3);
    }
}
