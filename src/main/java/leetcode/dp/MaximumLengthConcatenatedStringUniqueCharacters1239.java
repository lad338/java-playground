package leetcode.dp;

import java.util.*;

public class MaximumLengthConcatenatedStringUniqueCharacters1239 {

    class Solution {

        Map<Integer, Map<Set<Character>, Integer>> cache = new HashMap<>();

        Map<String, Set<Character>> stringMap = new HashMap<>();
        List<String> arr = new ArrayList<>();
        int size;

        public int maxLength(List<String> arr) {
            for (String string : arr) {
                Set<Character> set = new HashSet<>();
                char[] charArray = string.toCharArray();
                boolean invalid = false;
                for (char c : charArray) {
                    if (set.contains(c)) {
                        invalid = true;
                        break;
                    }
                    set.add(c);
                }
                if (invalid) {
                    continue;
                }
                stringMap.put(string, set);
                this.arr.add(string);
            }

            if (this.arr.size() == 0) {
                return 0;
            }

            size = this.arr.size();

            return helper(0, new HashSet<>());
        }

        private int helper(int index, Set<Character> set) {
            if (index >= size) {
                return set.size();
            }

            cache.putIfAbsent(index, new HashMap<>());
            if (cache.get(index).containsKey(set)) {
                return cache.get(index).get(set);
            }

            int result = 0;

            Set<Character> newSet = new HashSet<>(set);
            Set<Character> set2 = stringMap.get(arr.get(index));

            newSet.addAll(set2);
            if (newSet.size() == set2.size() + set.size()) {
                result = helper(index + 1, newSet);
            }

            result = Math.max(result, helper(index + 1, set));

            cache.get(index).put(set, result);

            return result;
        }
    }
}
