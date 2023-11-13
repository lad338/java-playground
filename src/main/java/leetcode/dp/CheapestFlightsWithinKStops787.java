package leetcode.dp;

import java.util.*;
import leetcode.Pair;

public class CheapestFlightsWithinKStops787 {

    class Solution {

        public int findCheapestPrice(
            int n,
            int[][] flights,
            int src,
            int dst,
            int k
        ) {
            Map<Integer, Map<Integer, Integer>> adjMap = new HashMap<>();

            for (int[] flight : flights) {
                int source = flight[0];
                int destination = flight[1];
                int cost = flight[2];

                Map<Integer, Integer> sourceMap = adjMap.getOrDefault(
                    source,
                    new HashMap<>()
                );
                sourceMap.put(destination, cost);
                adjMap.put(source, sourceMap);
            }

            Deque<Pair<Integer, Integer>> queue = new ArrayDeque<>(
                adjMap
                    .getOrDefault(src, new HashMap<>())
                    .keySet()
                    .stream()
                    .map(it -> new Pair<>(src, it))
                    .toList()
            );

            int[] costCache = new int[n];
            Set<Pair<Integer, Integer>> visited = new HashSet<>();

            for (int i = 0; i <= k && !queue.isEmpty(); i++) {
                int[] currentCostCache = Arrays.copyOf(
                    costCache,
                    costCache.length
                );

                List<Pair<Integer, Integer>> pairs = new ArrayList<>(queue);
                queue.clear();

                Set<Pair<Integer, Integer>> revisits = new HashSet<>();

                for (Pair<Integer, Integer> pair : pairs) {
                    if (!visited.contains(pair)) {
                        visited.add(pair);
                        int source = pair.getKey();
                        int destination = pair.getValue();
                        List<Pair<Integer, Integer>> newPairs = adjMap
                            .getOrDefault(destination, new HashMap<>())
                            .keySet()
                            .stream()
                            .map(it -> new Pair<>(destination, it))
                            .toList();

                        int totalCost =
                            costCache[source] +
                            adjMap.get(source).get(destination);
                        if (currentCostCache[destination] == 0) {
                            currentCostCache[destination] = totalCost;
                        } else {
                            if (totalCost < currentCostCache[destination]) {
                                currentCostCache[destination] = totalCost;
                                revisits.addAll(newPairs);
                            }
                        }
                        queue.addAll(newPairs);
                    }
                }
                visited.removeAll(revisits);
                costCache = currentCostCache;
            }

            return costCache[dst] == 0 ? -1 : costCache[dst];
        }
    }

    class NeetCodeSolution {

        public int findCheapestPrice(
            int n,
            int[][] flights,
            int src,
            int dst,
            int k
        ) {
            int[] costCache = new int[n];
            Arrays.fill(costCache, Integer.MAX_VALUE);
            costCache[src] = 0;

            for (int i = 0; i <= k; i++) {
                int[] currentCostCache = Arrays.copyOf(
                    costCache,
                    costCache.length
                );

                for (int[] flight : flights) {
                    int source = flight[0];
                    int destination = flight[1];
                    int cost = flight[2];

                    if (costCache[source] != Integer.MAX_VALUE) {
                        currentCostCache[destination] =
                            Math.min(
                                currentCostCache[destination],
                                costCache[source] + cost
                            );
                    }
                }
                costCache = currentCostCache;
            }

            return costCache[dst] == Integer.MAX_VALUE ? -1 : costCache[dst];
        }
    }
}
