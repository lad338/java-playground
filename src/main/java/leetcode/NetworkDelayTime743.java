package leetcode;

import java.util.*;

public class NetworkDelayTime743 {

    class Solution {

        Map<Integer, Map<Integer, Integer>> fromToCostMap = new HashMap<>();

        public int networkDelayTime(int[][] times, int n, int k) {
            for (int[] time : times) {
                fromToCostMap.putIfAbsent(time[0], new HashMap<>());
                fromToCostMap.get(time[0]).put(time[1], time[2]);
            }

            int result = 0;
            Map<Integer, Integer> delayMap = new HashMap<>();
            delayMap.put(k, 0);

            PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(a -> a[2])
            );

            for (int destination : fromToCostMap
                .getOrDefault(k, new HashMap<>())
                .keySet()) {
                minHeap.offer(
                    new int[] {
                        k,
                        destination,
                        fromToCostMap.get(k).get(destination),
                    }
                );
            }

            while (!minHeap.isEmpty() && delayMap.size() < n) {
                int[] first = minHeap.poll();
                if (delayMap.containsKey(first[1])) {
                    continue;
                }

                result = first[2];
                delayMap.put(first[1], first[2]);

                for (int destination : fromToCostMap
                    .getOrDefault(first[1], new HashMap<>())
                    .keySet()) {
                    if (delayMap.containsKey(destination)) {
                        continue;
                    }
                    minHeap.offer(
                        new int[] {
                            first[1],
                            destination,
                            result +
                            fromToCostMap.get(first[1]).get(destination),
                        }
                    );
                }
            }

            return delayMap.size() == n ? result : -1;
        }
    }
}
