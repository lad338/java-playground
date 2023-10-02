package leetcode;

import java.util.*;

public class TopKFrequentElements347 {

    class Solution {

        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> countMap = new HashMap<>();

            for (int n : nums) {
                countMap.put(n, countMap.getOrDefault(n, 0) + 1);
            }

            final List<Map.Entry<Integer, Integer>> sorted = countMap
                .entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .toList();

            return sorted
                .subList(0, k)
                .stream()
                .mapToInt(Map.Entry::getKey)
                .toArray();
        }
    }
}
