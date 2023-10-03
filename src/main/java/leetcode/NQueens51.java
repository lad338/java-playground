package leetcode;

import java.util.*;

public class NQueens51 {

    class Solution {

        List<List<String>> results = new ArrayList<>();
        final Set<Integer> column = new HashSet<>();
        final Set<Integer> posD = new HashSet<>();
        final Set<Integer> negD = new HashSet<>();
        List<String> board = new ArrayList<>();

        public List<List<String>> solveNQueens(int n) {
            recursive(0, n);
            return results;
        }

        private void recursive(int r, int n) {
            if (r == n) {
                List<String> copy = new ArrayList<>(board);
                results.add(copy);
                return;
            }

            for (int c = 0; c < n; c++) {
                if (
                    column.contains(c) ||
                    posD.contains(r + c) ||
                    negD.contains(r - c)
                ) {
                    continue;
                }

                column.add(c);
                posD.add(r + c);
                negD.add(r - c);
                String boardLine = ".".repeat(c) + "Q" + ".".repeat(n - c - 1);
                board.add(boardLine);

                recursive(r + 1, n);

                column.remove(c);
                posD.remove(r + c);
                negD.remove(r - c);
                board.remove(board.size() - 1);
            }
        }
    }
}
