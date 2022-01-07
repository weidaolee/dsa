package recursive.backtrack;

import java.util.LinkedList;
import java.util.List;

public class WordBreakII {
    /**
     * Link:
     * https://leetcode-cn.com/problems/word-break-ii/
     *
     */

    String s;
    List<String> ans;
    List<String> wordDict;
    int len;
    public List<String> wordBreak(String s, List<String> wordDict) {
        init(s, wordDict);
        backtrack(0, new StringBuilder());
        return ans;
    }

    private void backtrack(int begin, StringBuilder phrase) {
        if (phrase == null) {
            return;
        }

        if (begin == len) {
            phrase.deleteCharAt(phrase.length() - 1);
            ans.add(phrase.toString());
            return;
        }

        for (String word : wordDict) {
            if (s.startsWith(word, begin)) {
                int tail = phrase.length();
                phrase.append(word + " ");
                backtrack(begin + word.length(), phrase);
                phrase.delete(tail, phrase.length());
            }
        }
        phrase = null;
    }

    private void init (String s, List<String> wordDict) {
        this.s = s;
        this.wordDict = wordDict;
        ans = new LinkedList<>();
        len = s.length();
    }
}
