package leetcode;

import java.util.*;

public class PacificAtlanticWaterFlow417 {

    class Solution {

        int ROW;
        int COL;
        Map<Integer, Set<Integer>> pac = new HashMap<>();
        Map<Integer, Set<Integer>> alt = new HashMap<>();
        int[][] heights;

        public List<List<Integer>> pacificAtlantic(int[][] heights) {
            this.heights = heights;
            ROW = heights.length;
            COL = heights[0].length;

            for (int r = 0; r < ROW; r++) {
                helper(r, 0, 0, pac);
                helper(r, COL - 1, 0, alt);
            }

            for (int c = 0; c < COL; c++) {
                helper(0, c, 0, pac);
                helper(ROW - 1, c, 0, alt);
            }

            List<List<Integer>> result = new ArrayList<>();

            for (int row : pac.keySet()) {
                for (int col : pac.get(row)) {
                    if (alt.containsKey(row) && alt.get(row).contains(col)) {
                        List<Integer> current = new ArrayList<>();
                        current.add(row);
                        current.add(col);
                        result.add(current);
                    }
                }
            }

            return result;
        }

        private void helper(
            int r,
            int c,
            int previousHeight,
            Map<Integer, Set<Integer>> visit
        ) {
            if (r < 0 || c < 0 || r == ROW || c == COL) {
                return;
            }

            if (heights[r][c] < previousHeight) {
                return;
            }

            visit.putIfAbsent(r, new HashSet<>());
            if (visit.get(r).contains(c)) {
                return;
            }

            visit.get(r).add(c);
            helper(r, c - 1, heights[r][c], visit);
            helper(r, c + 1, heights[r][c], visit);
            helper(r - 1, c, heights[r][c], visit);
            helper(r + 1, c, heights[r][c], visit);
        }
    }
}
