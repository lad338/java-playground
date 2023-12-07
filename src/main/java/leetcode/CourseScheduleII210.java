package leetcode;

import java.util.*;

public class CourseScheduleII210 {

    class Solution {

        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> path = new HashSet<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        boolean isCyclic = false;

        public int[] findOrder(int numCourses, int[][] prerequisites) {
            for (int[] preq : prerequisites) {
                map.putIfAbsent(preq[0], new HashSet<>());
                map.get(preq[0]).add(preq[1]);
            }

            for (int i = 0; i < numCourses; i++) {
                if (!isCyclic) {
                    helper(i);
                } else {
                    return new int[] {};
                }
            }

            if (isCyclic) {
                return new int[] {};
            }

            if (visited.size() < numCourses) {
                return new int[] {};
            }

            int[] resultArray = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                resultArray[i] = result.get(i);
            }
            return resultArray;
        }

        private void helper(int course) {
            if (visited.contains(course)) {
                return;
            }
            if (path.contains(course)) {
                isCyclic = true;
                return;
            }

            if (map.containsKey(course)) {
                path.add(course);
                for (int preq : map.get(course)) {
                    helper(preq);
                }
                path.remove(course);
            }
            visited.add(course);
            result.add(course);
        }
    }
}
