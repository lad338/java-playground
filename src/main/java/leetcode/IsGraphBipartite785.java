package leetcode;

public class IsGraphBipartite785 {

    class Solution {

        int[] partitionSet;
        int[][] graph;

        public boolean isBipartite(int[][] graph) {
            this.graph = graph;
            partitionSet = new int[graph.length];

            for (int i = 0; i < graph.length; i++) {
                if (partitionSet[i] == 0 && !helper(i, 1)) {
                    return false;
                }
            }
            return true;
        }

        private boolean helper(int index, int factor) {
            if (partitionSet[index] != 0 && factor != partitionSet[index]) {
                return false;
            }

            if (partitionSet[index] != 0) {
                return true;
            }

            partitionSet[index] = factor;
            factor *= -1;

            for (int neighour : graph[index]) {
                if (!helper(neighour, factor)) {
                    return false;
                }
            }

            return true;
        }
    }
}
