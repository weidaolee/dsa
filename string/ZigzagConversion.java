package string;

import java.util.ArrayList;
import java.util.List;

public class ZigzagConversion {
    /**
     * Link:
     * https://leetcode-cn.com/problems/zigzag-conversion/
     *
     */

    List <StringBuilder> cabinet;
    public String convert(String s, int numRows) {
        init(s, numRows);
        int index = 0;
        while (index < s.length()) {
            int level;
            for (level = 0; level < numRows && index < s.length(); level ++) {
                cabinet.get(level).append(s.charAt(index ++));
            }

            for (level = numRows - 2; level > 0 && index < s.length(); level --) {
                cabinet.get(level).append(s.charAt(index ++));
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            ans.append(cabinet.get(i));
        }

        return ans.toString();
    }

    private void init (String s, int numRows) {
        cabinet = new ArrayList<>(numRows);
        for (int level = 0; level < numRows; level++) {
            cabinet.add(new StringBuilder());
        }
    }
}
