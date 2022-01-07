package recursive.memory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreakI {
    /**
     * Link:
     * https://leetcode-cn.com/problems/word-break/
     *
     */

    String s;
    List<String> wordDict;
    int[] memory;
    int len;
    public boolean wordBreak(String s, List<String> wordDict) {
        init(s, wordDict);

        return memory(0);
    }

    /**
     * Recursion:
     *
     * Base cases:
     *   begin 來到 len 位置, 代表可以抵達終點
     *
     * Normal cases:
     *   begin 來到一般的位置, 遍歷 dictionary 中的 word:
     *   檢查 string 的 begin 位置, 是否以 word 開頭, 且 begin + word.length 是否可以抵達終點
     */

    private boolean wordBreak(int begin) {
        if (begin == len) {
            return true;
        }

        for (String word : wordDict) {
            if (s.startsWith(word, begin) && wordBreak(begin + word.length())) {
                return true;
            }
        }
        return false;
    }

    private boolean memory(int begin) {
        if (memory[begin] == -1) {
            return false;
        }

        if (memory[begin] == 1 || begin == len) {
            return true;
        }

        for (String word : wordDict) {
            if (s.startsWith(word, begin) && memory(begin + word.length())) {
                memory[begin] = 1;
                return true;
            }
        }
        memory[begin] = -1;
        return false;
    }

    /**
     * 放棄使用 dp, 本題 dp 的解法是以訛傳訛的錯誤
     *
     */

    private boolean dp() {
        Set <String> set = new HashSet<>(wordDict);
        boolean [] dp = new boolean[len + 1];
        dp[0] = true;

        for (int end = 1; end < len + 1; end ++) {
            for (int begin = 0; begin < end; begin ++) {
                dp[end] |= set.contains(s.substring(begin, end)) && dp[begin];
            }
        }
        return dp[len];
    }

    private void init (String s, List<String> wordDict) {
        this.s = s;
        this.wordDict = wordDict;
        len = s.length();
        memory = new int[len + 1];
    }
}
