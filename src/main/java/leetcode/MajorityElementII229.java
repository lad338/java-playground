package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class MajorityElementII229 {

    class Solution {

        public List<Integer> majorityElement(int[] nums) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            return map
                .entrySet()
                .stream()
                .filter(it -> it.getValue() > nums.length / 3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        }
    }

    class O1SpaceSolution {

        public List<Integer> majorityElement(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
                if (map.size() <= 2) {
                    continue;
                }

                Map<Integer, Integer> newMap = new HashMap<>();
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    if (entry.getValue() > 1) {
                        newMap.put(entry.getKey(), entry.getValue() - 1);
                    }
                }
                map = newMap;
            }

            List<Integer> result = new ArrayList<>();

            Map<Integer, Integer> resultMap = new HashMap<>();
            for (int num : nums) {
                if (map.containsKey(num)) {
                    resultMap.put(num, resultMap.getOrDefault(num, 0) + 1);
                }
            }

            for (Map.Entry<Integer, Integer> entry : resultMap.entrySet()) {
                if (entry.getValue() > nums.length / 3) {
                    result.add(entry.getKey());
                }
            }

            return result;
        }
    }
}
