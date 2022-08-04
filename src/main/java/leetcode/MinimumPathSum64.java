package leetcode;

public class MinimumPathSum64 {

  class Solution {

    public int minPathSum(int[][] grid) {
      int[][] sum = new int[grid.length][grid[0].length];
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
          if (i == 0 && j == 0) {
            sum[i][j] = grid[i][j];
          } else {
            int right = i - 1 < 0 ? Integer.MAX_VALUE : sum[i - 1][j];
            int bottom = j - 1 < 0 ? Integer.MAX_VALUE : sum[i][j - 1];

            sum[i][j] = grid[i][j] + Math.min(right, bottom);
          }
        }
      }
      return sum[grid.length - 1][grid[0].length - 1];
    }
  }
}
