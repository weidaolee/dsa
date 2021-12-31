package string.match;

public class LongestSubstringWithRepeatingKTimesCharacters {

    public int longestSubstring(String s, int k) {
        if (k == 1) {
            return s.length();
        }
        return longestSubstring(s, k, 0);
    }

    public int longestSubstring(String s, int k, int curLen) {
        if (s.length() < k) {
            return 0;
        }

        int []times = new int[26];
        for (int i = 0; i < s.length(); i++) {
            times[s.charAt(i) - 'a'] ++;
        }

        for (int abc = 0; abc < 26; abc ++) {
            if (0 < times[abc] && times[abc] < k) {
                curLen = 0;
                for (String subString : s.split(String.valueOf((char) (abc + 'a')))) {
                    curLen = Math.max(curLen, longestSubstring(subString, k, curLen));
                }
                return curLen;
            }
        }
        return s.length();
    }
}
