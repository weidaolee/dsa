package slidingwindow;


public class KLenSubstringNoRepeat {
    public int numKLenSubstrNoRepeats(String s, int k) {
        if (s.length() < k) {
            return 0;
        }

        int ans = 0;
        int [] set = new int[26];

        int L = 0, R = 0;

        while (R < s.length()) {
            if (R < s.length() && set[s.charAt(R) - 'a'] == 0) {
                set[s.charAt(R++) - 'a'] ++;
            } else {
                set[s.charAt(L++) - 'a'] --;

            }

            if (R - L == k) {
                set[s.charAt(L ++) - 'a'] --;
                ans ++;
            }
        }
        return ans;
    }
}
