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

    class Solution2 {

        int[][] dp;

        public int longestIncreasingPath(int[][] matrix) {
            dp = new int[matrix.length][matrix[0].length];

            int result = 1;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    result = Math.max(result, dfs(matrix, i, j));
                }
            }
            return result;
        }

        private int dfs(int[][] matrix, int row, int col) {
            if (
                row < 0 ||
                col < 0 ||
                row >= matrix.length ||
                col >= matrix[0].length
            ) {
                return 0;
            }

            if (dp[row][col] != 0) {
                return dp[row][col];
            }

            int max = 1;

            if (row - 1 >= 0) {
                if (matrix[row - 1][col] > matrix[row][col]) {
                    max = Math.max(max, 1 + dfs(matrix, row - 1, col));
                }
            }

            if (row + 1 < matrix.length) {
                if (matrix[row + 1][col] > matrix[row][col]) {
                    max = Math.max(max, 1 + dfs(matrix, row + 1, col));
                }
            }

            if (col - 1 >= 0 && col - 1 < matrix[0].length) {
                if (matrix[row][col - 1] > matrix[row][col]) {
                    max = Math.max(max, 1 + dfs(matrix, row, col - 1));
                }
            }

            if (col + 1 < matrix[0].length) {
                if (matrix[row][col + 1] > matrix[row][col]) {
                    max = Math.max(max, 1 + dfs(matrix, row, col + 1));
                }
            }

            dp[row][col] = max;
            return max;
        }
    }
}
