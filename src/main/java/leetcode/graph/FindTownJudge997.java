package leetcode.graph;

import java.util.*;

public class FindTownJudge997 {

    class Solution {

        public int findJudge(int n, int[][] trust) {
            if (n == 1 && trust.length == 0) {
                return 1;
            }

            Map<Integer, Set<Integer>> trustMap = new HashMap<>();
            Map<Integer, Set<Integer>> beingTrustMap = new HashMap<>();
            for (int[] current : trust) {
                int src = current[0];
                int dst = current[1];

                trustMap.putIfAbsent(src, new HashSet<>());
                trustMap.get(src).add(dst);
                beingTrustMap.putIfAbsent(dst, new HashSet<>());
                beingTrustMap.get(dst).add(src);
            }

            for (int person : beingTrustMap.keySet()) {
                if (trustMap.containsKey(person)) {
                    continue;
                }

                if (beingTrustMap.get(person).size() < n - 1) {
                    continue;
                }
                return person;
            }
            return -1;
        }
    }
}
