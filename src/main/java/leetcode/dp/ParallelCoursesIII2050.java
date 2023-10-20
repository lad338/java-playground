package leetcode.dp;

import java.util.*;

public class ParallelCoursesIII2050 {

    class Solution {

        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Integer> cache = new HashMap<>();
        int result = Integer.MIN_VALUE;

        public int minimumTime(int n, int[][] relations, int[] time) {
            for (int[] course : relations) {
                List<Integer> neigbours = map.getOrDefault(
                    course[0],
                    new ArrayList<>()
                );
                neigbours.add(course[1]);
                map.put(course[0], neigbours);
            }

            for (int i = 1; i <= n; i++) {
                dfs(i, time);
            }

            return result;
        }

        private int dfs(int i, int[] time) {
            if (cache.containsKey(i)) {
                return cache.get(i);
            }

            int max = time[i - 1];

            if (map.containsKey(i)) {
                for (Integer neigbour : map.get(i)) {
                    max = Math.max(max, time[i - 1] + dfs(neigbour, time));
                }
            }

            cache.put(i, max);
            result = Math.max(max, result);
            return max;
        }
    }
}
