package leetcode.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring76 {

    class Solution {

        public String minWindow(String s, String t) {
            int resultL = -1;
            int resultR = 0;
            int resultLength = Integer.MAX_VALUE;

            Map<Character, Integer> required = new HashMap<>();
            Map<Character, Integer> countMap = new HashMap<>();

            for (int i = 0; i < t.length(); i++) {
                required.put(
                    t.charAt(i),
                    required.getOrDefault(t.charAt(i), 0) + 1
                );
            }
            int requiredSize = required.size();

            int L = 0;
            int count = 0;
            for (int R = 0; R < s.length(); R++) {
                int previous = countMap.getOrDefault(s.charAt(R), 0);
                countMap.put(s.charAt(R), previous + 1);
                if (previous + 1 == required.getOrDefault(s.charAt(R), 0)) {
                    count++;
                }

                while (count == requiredSize) {
                    if (R - L + 1 < resultLength) {
                        resultLength = R - L + 1;
                        resultR = R;
                        resultL = L;
                    }

                    int previousL = countMap.get(s.charAt(L));
                    if (previousL == required.getOrDefault(s.charAt(L), 0)) {
                        count--;
                    }
                    countMap.put(s.charAt(L), previousL - 1);
                    L++;
                }
            }

            if (resultL == -1) {
                return "";
            }

            return s.substring(resultL, resultR + 1);
        }
    }
}
