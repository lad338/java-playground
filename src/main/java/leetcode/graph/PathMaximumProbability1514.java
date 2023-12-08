package leetcode.graph;

import java.util.*;
import leetcode.Pair;

public class PathMaximumProbability1514 {

    class Solution {

        public double maxProbability(
            int n,
            int[][] edges,
            double[] succProb,
            int start_node,
            int end_node
        ) {
            Map<Integer, Map<Integer, Double>> map = new HashMap<>();

            for (int i = 0; i < edges.length; i++) {
                map.putIfAbsent(edges[i][0], new HashMap<>());
                map.get(edges[i][0]).put(edges[i][1], succProb[i]);
                map.putIfAbsent(edges[i][1], new HashMap<>());
                map.get(edges[i][1]).put(edges[i][0], succProb[i]);
            }

            PriorityQueue<Pair<Integer, Double>> maxHeap = new PriorityQueue<>(
                    (a, b) ->
                b.getValue().compareTo(a.getValue())
            );

            if (!map.containsKey(start_node)) {
                return 0;
            }
            for (int destination : map.get(start_node).keySet()) {
                maxHeap.offer(
                    new Pair<>(
                        destination,
                        map.get(start_node).get(destination)
                    )
                );
            }

            Set<Integer> visited = new HashSet<>();
            while (!maxHeap.isEmpty()) {
                Pair<Integer, Double> max = maxHeap.poll();
                int start = max.getKey();
                if (start == end_node) {
                    return max.getValue();
                }
                visited.add(start);
                for (int destination : map.get(start).keySet()) {
                    if (!visited.contains(destination)) {
                        maxHeap.offer(
                            new Pair<>(
                                destination,
                                max.getValue() * map.get(start).get(destination)
                            )
                        );
                    }
                }
            }

            return 0;
        }
    }
}
