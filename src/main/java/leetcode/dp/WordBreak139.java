package leetcode.dp;

import java.util.*;

public class WordBreak139 {

    class Solution {

        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> dict = new HashSet<>();

            int maxWordLength = 0;

            for (String w : wordDict) {
                dict.add(w);
                maxWordLength = Math.max(maxWordLength, w.length());
            }

            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;
            int lastTrue = 0;

            for (int i = 0; i < s.length(); i++) {
                int p = lastTrue;
                while (p >= 0) {
                    if (dp[p]) {
                        String sub = s.substring(p, i + 1);
                        if (dict.contains(sub)) {
                            dp[i + 1] = true;
                            lastTrue = i + 1;
                            break;
                        } else {
                            if (sub.length() > maxWordLength) {
                                break;
                            }
                        }
                    }
                    p--;
                }
            }

            return dp[s.length()];
        }
    }

    class Solution2 {

        public boolean wordBreak(String s, List<String> wordDict) {
            boolean dp[] = new boolean[s.length() + 1];
            dp[s.length()] = true;

            for (int i = s.length() - 1; i >= 0; i--) {
                for (String word : wordDict) {
                    if (word.length() + i > s.length()) {
                        continue;
                    }
                    if (word.equals(s.substring(i, i + word.length()))) {
                        dp[i] = dp[i + word.length()];
                    }
                    if (dp[i]) {
                        break;
                    }
                }
            }

            return dp[0];
        }
    }
}
