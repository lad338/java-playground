package leetcode.dp;

import java.util.Arrays;

public class MaximalSquare221 {

    class Solution {

        public int maximalSquare(char[][] matrix) {
            int rows = matrix.length;
            int cols = matrix[0].length;
            int[][] cache = new int[rows][cols];
            for (int[] row : cache) {
                Arrays.fill(row, -1);
            }

            int result = 0;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    result =
                        Math.max(
                            result,
                            helper(i, j, rows, cols, cache, matrix)
                        );
                }
            }
            return result * result;
        }

        private int helper(
            int r,
            int c,
            int rows,
            int cols,
            int[][] cache,
            char[][] matrix
        ) {
            if (r >= rows || c >= cols) {
                return 0;
            }
            if (cache[r][c] != -1) {
                return cache[r][c];
            }
            int result = 0;
            if (matrix[r][c] == '1') {
                int left = helper(r, c + 1, rows, cols, cache, matrix);
                int down = helper(r + 1, c, rows, cols, cache, matrix);
                int diagonal = helper(r + 1, c + 1, rows, cols, cache, matrix);
                result = 1 + Math.min(left, Math.min(down, diagonal));
            }
            cache[r][c] = result;
            return result;
        }
    }

    class BottomUpSolution {

        public int maximalSquare(char[][] matrix) {
            int rows = matrix.length;
            int cols = matrix[0].length;
            int[][] cache = new int[rows + 1][cols + 1];
            for (int[] row : cache) {
                Arrays.fill(row, 0);
            }

            int result = 0;

            for (int i = rows - 1; i >= 0; i--) {
                for (int j = cols - 1; j >= 0; j--) {
                    if (matrix[i][j] == '1') {
                        int max =
                            1 +
                            Math.min(
                                cache[i + 1][j],
                                Math.min(cache[i][j + 1], cache[i + 1][j + 1])
                            );
                        cache[i][j] = max;
                        result = Math.max(result, max);
                    }
                }
            }
            return result * result;
        }
    }
}
