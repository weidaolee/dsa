package string.match;


public class CanPermutePalindrome {
    public boolean canPermutePalindrome(String s) {

        boolean [] set = new boolean [26];
        int size = 0;

        for (int i = 0; i < s.length(); i++) {
            if (size > s.length() - i + 1) {
                return false;
            }

            char c = s.charAt(i);
            if (!set[c - 'a']) {
                set[c - 'a'] = true;
                size ++;
            } else {
                set[c - 'a'] = false;
                size --;
            }
        }
        return size > 1 ? false : true;
    }
}
