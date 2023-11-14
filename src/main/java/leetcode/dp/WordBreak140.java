package leetcode.dp;

import java.util.*;

public class WordBreak140 {

    class Solution {

        String s;
        Map<Integer, List<String>> cache = new HashMap<>();
        List<String> wordDict;

        public List<String> wordBreak(String s, List<String> wordDict) {
            this.s = s;
            this.wordDict = wordDict;

            cache.put(
                s.length(),
                new ArrayList<>() {
                    {
                        add("");
                    }
                }
            );

            if (helper(0)) {
                return cache.get(0);
            }
            return new ArrayList<>();
        }

        private boolean helper(int index) {
            if (index == s.length()) {
                return true;
            }

            if (cache.containsKey(index)) {
                return true;
            }

            List<String> result = new ArrayList<>();
            for (String current : wordDict) {
                if (current.length() + index > s.length()) {
                    continue;
                }
                boolean found = true;
                for (int j = 0; j < current.length(); j++) {
                    if (current.charAt(j) != s.charAt(j + index)) {
                        found = false;
                        break;
                    }
                }
                if (!found) {
                    continue;
                }
                if (helper(index + current.length())) {
                    String prefix = s.substring(
                        index,
                        index + current.length()
                    );
                    List<String> cacheResult = cache.get(
                        index + current.length()
                    );
                    for (String cacheString : cacheResult) {
                        result.add(
                            cacheString.length() > 0
                                ? prefix + " " + cacheString
                                : prefix
                        );
                    }
                }
            }
            if (result.size() > 0) {
                cache.put(index, result);
                return true;
            }
            return false;
        }
    }
}
