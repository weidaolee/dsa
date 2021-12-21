package recursive.left2right;
import java.util.LinkedList;
import java.util.List;

public class AllSubsequece {
    List <String> res = new LinkedList<>();
    public List <String> allSubsequece (String s) {
        if (s == null || s.length() == 0) {
            return res;
        }
        addSubsequence(s.toCharArray(), 0);

        return res;
    }

    public void addSubsequence (char [] s, int i) {
        if (i == s.length) {
            StringBuilder sb = new StringBuilder();
            for (char c : s) {
                if (c != '\0') {
                    sb.append(c);
                }
            }
            res.add(sb.toString());
            return;
        }
        addSubsequence(s, i + 1);
        char tmp = s[i];
        s[i] = '\0';
        addSubsequence(s, i + 1);
        s[i] = tmp;
    }

    public static void main(String[] args) {
         List <String> res = new AllSubsequece().allSubsequece("123");
        for (String s : res) {
            System.out.println(s);
        }
    }
}
