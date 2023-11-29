package leetcode;

public class NumberIsland200 {

    class Solution {

        int result = 0;
        boolean[][] visited;
        char[][] grid;

        public int numIslands(char[][] grid) {
            this.grid = grid;
            visited = new boolean[grid.length][grid[0].length];

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1' && !visited[i][j]) {
                        result++;
                        helper(i, j);
                    }
                }
            }

            return result;
        }

        private void helper(int r, int c) {
            if (
                r < 0 ||
                c < 0 ||
                r >= grid.length ||
                c >= grid[0].length ||
                visited[r][c]
            ) {
                return;
            }
            visited[r][c] = true;
            if (grid[r][c] == '1') {
                helper(r, c + 1);
                helper(r, c - 1);
                helper(r + 1, c);
                helper(r - 1, c);
            }
        }
    }
}
