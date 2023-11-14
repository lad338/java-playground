package leetcode;

import java.util.*;

public class PathWithMinEffort1631 {

    class Solution {

        class Choice {

            public int row;
            public int col;
            public int eff;

            public Choice(int row, int col, int eff) {
                this.row = row;
                this.col = col;
                this.eff = eff;
            }
        }

        public int minimumEffortPath(int[][] heights) {
            PriorityQueue<Choice> heap = new PriorityQueue<>(
                Comparator.comparingInt(it -> it.eff)
            );
            Set<Pair<Integer, Integer>> visited = new HashSet<>();
            visited.add(new Pair<>(0, 0));

            if (heights.length > 1) {
                int eff = Math.abs(heights[0][0] - heights[1][0]);
                heap.add(new Choice(1, 0, eff));
            }

            if (heights[0].length > 1) {
                int eff = Math.abs(heights[0][0] - heights[0][1]);
                heap.add(new Choice(0, 1, eff));
            }

            int result = 0;
            while (!heap.isEmpty()) {
                Choice first = heap.poll();
                result = Math.max(result, first.eff);
                visited.add(new Pair<>(first.row, first.col));

                if (
                    first.row == heights.length - 1 &&
                    first.col == heights[0].length - 1
                ) {
                    break;
                }

                if (first.row - 1 >= 0) {
                    if (
                        !visited.contains(new Pair<>(first.row - 1, first.col))
                    ) {
                        heap.add(
                            new Choice(
                                first.row - 1,
                                first.col,
                                Math.abs(
                                    heights[first.row][first.col] -
                                    heights[first.row - 1][first.col]
                                )
                            )
                        );
                    }
                }

                if (first.row + 1 < heights.length) {
                    if (
                        !visited.contains(new Pair<>(first.row + 1, first.col))
                    ) {
                        heap.add(
                            new Choice(
                                first.row + 1,
                                first.col,
                                Math.abs(
                                    heights[first.row][first.col] -
                                    heights[first.row + 1][first.col]
                                )
                            )
                        );
                    }
                }

                if (first.col - 1 >= 0) {
                    if (
                        !visited.contains(new Pair<>(first.row, first.col - 1))
                    ) {
                        heap.add(
                            new Choice(
                                first.row,
                                first.col - 1,
                                Math.abs(
                                    heights[first.row][first.col] -
                                    heights[first.row][first.col - 1]
                                )
                            )
                        );
                    }
                }

                if (first.col + 1 < heights[0].length) {
                    if (
                        !visited.contains(new Pair<>(first.row, first.col + 1))
                    ) {
                        heap.add(
                            new Choice(
                                first.row,
                                first.col + 1,
                                Math.abs(
                                    heights[first.row][first.col] -
                                    heights[first.row][first.col + 1]
                                )
                            )
                        );
                    }
                }
            }

            return result;
        }
    }
}
