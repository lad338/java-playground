package leetcode.graph;

public class RedundantConnection684 {

    class Solution {

        public int[] findRedundantConnection(int[][] edges) {
            int n = edges.length;
            int[] parent = new int[n + 1];
            int[] rank = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }

            for (int[] edge : edges) {
                int parentFrom = edge[0];
                int parentTo = edge[1];

                while (parent[parentFrom] != parentFrom) {
                    parentFrom = parent[parentFrom];
                }

                while (parent[parentTo] != parentTo) {
                    parentTo = parent[parentTo];
                }

                if (parentFrom == parentTo) {
                    return edge;
                }

                if (rank[parentFrom] > rank[parentTo]) {
                    rank[parentFrom] += rank[parentTo];
                    parent[edge[1]] = parentFrom;
                    parent[parentTo] = parentFrom;
                } else {
                    rank[parentTo] += rank[parentFrom];
                    parent[edge[0]] = parentTo;
                    parent[parentFrom] = parentTo;
                }
            }

            return new int[] { -1 };
        }
    }
}
