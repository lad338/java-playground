package leetcode.slidingWindow;

import java.util.*;

public class SubstringConcatenationAllWords30 {

    class Solution {

        public List<Integer> findSubstring(String s, String[] words) {
            int length = words[0].length();
            Map<String, Integer> map = new HashMap<>();
            for (String word : words) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
            Map<String, Integer> resetMap = new HashMap<>(map);

            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                map.putAll(resetMap);
                int L = i;
                int R = i;
                while (R + length <= s.length()) {
                    R += length;
                    String substring = s.substring(R - length, R);
                    while (!map.containsKey(substring) && L < R) {
                        map.put(
                            s.substring(L, L + length),
                            map.getOrDefault(s.substring(L, L + length), 0) + 1
                        );
                        L = L + length;
                    }
                    int count = map.get(substring);
                    if (count == 1) {
                        map.remove(substring);
                    } else {
                        map.put(substring, count - 1);
                    }

                    if (map.isEmpty()) {
                        result.add(L);
                        map.put(
                            s.substring(L, L + length),
                            map.getOrDefault(s.substring(L, L + length), 0) + 1
                        );
                        L = L + length;
                    }
                }
            }

            return result;
        }
    }
}
