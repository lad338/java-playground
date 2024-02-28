package leetcode.graph;

import java.util.*;
import java.util.stream.Collectors;

public class FindAllPeopleWithSecret2092 {

    class Solution {

        Map<Integer, Map<Integer, Set<Integer>>> timeMap = new HashMap<>();
        Set<Integer> secretSet = new HashSet<>();

        public List<Integer> findAllPeople(
            int n,
            int[][] meetings,
            int firstPerson
        ) {
            secretSet.add(0);
            secretSet.add(firstPerson);

            for (int[] meeting : meetings) {
                int src = meeting[0];
                int dst = meeting[1];
                int time = meeting[2];

                timeMap.putIfAbsent(time, new HashMap<>());
                timeMap.get(time).putIfAbsent(src, new HashSet<>());
                timeMap.get(time).putIfAbsent(dst, new HashSet<>());
                timeMap.get(time).get(src).add(dst);
                timeMap.get(time).get(dst).add(src);
            }

            List<Integer> uniqueTimes = timeMap
                .keySet()
                .stream()
                .sorted()
                .toList();
            for (int time : uniqueTimes) {
                Set<Integer> visited = new HashSet<>();
                for (int src : timeMap.get(time).keySet()) {
                    if (secretSet.contains(src)) {
                        helper(src, time, visited);
                    }
                }
            }

            return new ArrayList<>(secretSet);
        }

        private void helper(int src, int time, Set<Integer> visited) {
            if (visited.contains(src)) {
                return;
            }
            visited.add(src);
            secretSet.add(src);
            for (int dst : timeMap.get(time).get(src)) {
                helper(dst, time, visited);
            }
        }
    }
}
