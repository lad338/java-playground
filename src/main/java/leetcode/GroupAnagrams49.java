package leetcode;

import java.util.*;

public class GroupAnagrams49 {

    class Solution {

        public List<List<String>> groupAnagrams(String[] strs) {
            int emptyStringCount = 0;
            Map<Map<Character, Integer>, List<String>> group = new HashMap<>();

            for (String s : strs) {
                if (s.length() == 0) {
                    emptyStringCount++;
                } else {
                    Map<Character, Integer> countMap = new HashMap<>();
                    for (int i = 0; i < s.length(); i++) {
                        countMap.put(
                            s.charAt(i),
                            countMap.getOrDefault(s.charAt(i), 0) + 1
                        );
                    }
                    List<String> list = group.getOrDefault(
                        countMap,
                        new ArrayList<>()
                    );
                    list.add(s);
                    group.put(countMap, list);
                }
            }
            List<List<String>> results = new ArrayList<>();
            if (emptyStringCount > 0) {
                List<String> emptyStrings = new ArrayList<>();
                for (int i = 0; i < emptyStringCount; i++) {
                    emptyStrings.add("");
                }
                results.add(emptyStrings);
            }

            for (Map.Entry<Map<Character, Integer>, List<String>> entry : group.entrySet()) {
                results.add(entry.getValue());
            }
            return results;
        }
    }
}
