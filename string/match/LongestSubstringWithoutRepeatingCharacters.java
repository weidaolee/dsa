package string.match;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        Set <Character> set = new HashSet<>();
        int L = 0;
        int R = 0;

        int maxLen = 0;
        while (R < s.length()) {
            char c = s.charAt(R);
            if (!set.contains(c)) {
                R ++;
                set.add(c);
                maxLen = Math.max(maxLen, R - L);
            } else {
                set.remove(s.charAt(L));
                L ++;
            }
        }
        return maxLen;
    }
}
