package leetcode.dp;

public class LongestIncreasingPathInMatrix329 {

    class Solution {

        public int longestIncreasingPath(int[][] matrix) {
            final int[][] dp = new int[matrix.length][matrix[0].length];

            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[0].length; col++) {
                    dfs(matrix, dp, row, col, Integer.MIN_VALUE);
                }
            }

            int result = 0;
            for (int[] ints : dp) {
                for (int i : ints) {
                    result = Math.max(result, i);
                }
            }

            return result;
        }

        private int dfs(
            int[][] matrix,
            int[][] dp,
            int row,
            int col,
            int previousValue
        ) {
            if (
                row < 0 ||
                col < 0 ||
                row >= matrix.length ||
                col >= matrix[0].length ||
                matrix[row][col] <= previousValue
            ) {
                return 0;
            }

            if (dp[row][col] != 0) {
                return dp[row][col];
            }

            int max = 1;
            max =
                Math.max(
                    max,
                    1 + dfs(matrix, dp, row - 1, col, matrix[row][col])
                );
            max =
                Math.max(
                    max,
                    1 + dfs(matrix, dp, row, col - 1, matrix[row][col])
                );
            max =
                Math.max(
                    max,
                    1 + dfs(matrix, dp, row + 1, col, matrix[row][col])
                );
            max =
                Math.max(
                    max,
                    1 + dfs(matrix, dp, row, col + 1, matrix[row][col])
                );

            dp[row][col] = max;
            return max;
        }
    }
}
