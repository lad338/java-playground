package leetcode;

import java.util.*;

public class CourseSchedule207 {

    class Solution {

        Map<Integer, Set<Integer>> adjMap = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Boolean> cache = new HashMap<>();

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            for (int[] prerequisite : prerequisites) {
                adjMap.putIfAbsent(prerequisite[0], new HashSet<>());
                adjMap.get(prerequisite[0]).add(prerequisite[1]);
            }

            for (int i = 0; i < numCourses; i++) {
                if (!helper(i)) {
                    return false;
                }
            }
            return true;
        }

        private boolean helper(int index) {
            if (!adjMap.containsKey(index)) {
                return true;
            }

            if (cache.containsKey(index)) {
                return cache.get(index);
            }

            if (visited.contains(index)) {
                cache.put(index, false);
                return false;
            }

            visited.add(index);
            for (int prereq : adjMap.get(index)) {
                if (!helper(prereq)) {
                    visited.remove(index);
                    cache.put(index, false);
                    return false;
                }
            }
            visited.remove(index);
            cache.put(index, true);
            return true;
        }
    }

    class NeetcodeSolution {

        Map<Integer, Set<Integer>> adjMap = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            for (int[] prerequisite : prerequisites) {
                adjMap.putIfAbsent(prerequisite[0], new HashSet<>());
                adjMap.get(prerequisite[0]).add(prerequisite[1]);
            }

            for (int i = 0; i < numCourses; i++) {
                if (!helper(i)) {
                    return false;
                }
            }
            return true;
        }

        private boolean helper(int index) {
            if (!adjMap.containsKey(index)) {
                return true;
            }

            if (visited.contains(index)) {
                return false;
            }

            visited.add(index);
            for (int prereq : adjMap.get(index)) {
                if (!helper(prereq)) {
                    return false;
                }
            }
            visited.remove(index);
            adjMap.remove(index);
            return true;
        }
    }
}
