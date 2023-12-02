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

    class ONSolution {

        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> numToCount = new HashMap<>();
            Map<Integer, Set<Integer>> countToNum = new HashMap<>();
            int maxCount = 0;

            for (int num : nums) {
                numToCount.put(num, numToCount.getOrDefault(num, 0) + 1);
            }

            for (int num : numToCount.keySet()) {
                int count = numToCount.get(num);
                countToNum.putIfAbsent(count, new HashSet<>());
                countToNum.get(count).add(num);
                maxCount = Math.max(maxCount, count);
            }

            List<Integer> list = new ArrayList<>();
            for (int i = maxCount; i >= 1 && list.size() < k; i--) {
                if (countToNum.containsKey(i)) {
                    list.addAll(countToNum.get(i));
                }
            }
            return list.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}
