package leetcode;

import java.util.HashMap;
import java.util.Map;

public class IntegerToRoman {
    static class Solution {
        public int romanToInt(String s) {
            final Map<Character, Integer> valueMap = new HashMap<>() {
                {
                    put('I', 1);
                    put('V', 5);
                    put('X', 10);
                    put('L', 50);
                    put('C', 100);
                    put('D', 500);
                    put('M', 1000);
                }
            };

            Character minimum = 'I';
            final Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char current = s.charAt(s.length() - i - 1);
                int currentCount = map.get(current) != null ? map.get(current) : 0;
                if (valueMap.get(current) < valueMap.get(minimum)) {
                    map.put(current, currentCount - 1);
                } else {
                    map.put(current, currentCount + 1);
                }
                if (valueMap.get(current) > valueMap.get(minimum)) {
                    minimum = current;
                }
            }

            return map.entrySet().stream().map(it -> it.getValue() * valueMap.get(it.getKey())).reduce(0, Integer::sum);

        }
    }
}
