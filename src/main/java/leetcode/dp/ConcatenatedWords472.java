package leetcode.dp;

import java.util.*;

public class ConcatenatedWords472 {

    class Solution {

        Map<String, Boolean> cache = new HashMap<>();
        Set<String> set = new HashSet<>();

        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            for (String word : words) {
                set.add(word);
            }
            List<String> result = new ArrayList<>();

            for (String word : words) {
                if (helper(word)) {
                    result.add(word);
                }
            }
            return result;
        }

        private boolean helper(String s) {
            if (cache.containsKey(s)) {
                return cache.get(s);
            }

            for (int i = 1; i < s.length(); i++) {
                String prefix = s.substring(0, i);
                String suffix = s.substring(i, s.length());
                if (set.contains(prefix)) {
                    if (set.contains(suffix) || helper(suffix)) {
                        cache.put(s, true);
                        return true;
                    }
                }
            }

            cache.put(s, false);
            return false;
        }
    }
}
