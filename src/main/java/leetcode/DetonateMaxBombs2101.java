package leetcode;

import java.util.*;

public class DetonateMaxBombs2101 {

    class Solution {

        public int maximumDetonation(int[][] bombs) {
            Map<Integer, Set<Integer>> adjMap = new HashMap<>();
            for (int i = 0; i < bombs.length; i++) {
                Set<Integer> connected = new HashSet<>();
                for (int j = 0; j < bombs.length; j++) {
                    if (i == j) {
                        connected.add(i);
                        continue;
                    }
                    if (
                        bombs[i][2] >=
                        Math.sqrt(
                            Math.pow(bombs[j][0] - bombs[i][0], 2) +
                            Math.pow(bombs[j][1] - bombs[i][1], 2)
                        )
                    ) {
                        connected.add(j);
                    }
                }
                adjMap.put(i, connected);
            }

            int result = 0;
            for (int i = 0; i < bombs.length; i++) {
                Set<Integer> visited = new HashSet<>();
                visited.add(i);
                Queue<Integer> toVisit = new ArrayDeque<>(adjMap.get(i));
                while (!toVisit.isEmpty()) {
                    Integer first = toVisit.poll();
                    if (visited.contains(first)) {
                        continue;
                    }
                    visited.add(first);
                    toVisit.addAll(adjMap.get(first));
                    if (visited.size() == bombs.length) {
                        return bombs.length;
                    }
                }
                result = Math.max(result, visited.size());
            }
            return result;
        }
    }
}
