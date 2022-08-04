package leetcode;

import java.util.ArrayList;
import java.util.List;

public class UniquePaths62 {

  class Solution {

    public int uniquePaths(int m, int n) {
      List<List<Integer>> paths = new ArrayList<>();

      for (int i = 0; i < m; i++) {
        paths.add(new ArrayList<>());
      }

      for (int i = 0; i < m; i++) {
        paths.get(i).add(1);
      }

      for (int j = 1; j < n; j++) {
        paths.get(0).add(1);
      }

      for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
          int value = paths.get(i - 1).get(j) + paths.get(i).get(j - 1);
          paths.get(i).add(value);
        }
      }

      return paths.get(m - 1).get(n - 1);
    }
  }

  class PrimitiveSolution {

    public int uniquePaths(int m, int n) {
      if (m == 1 || n == 1) {
        return 1;
      }
      int[][] paths = new int[m][n];
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          paths[i][j] = i == 0 || j == 0 ? 1 : paths[i - 1][j] + paths[i][j - 1];
        }
      }
      return paths[m - 1][n - 1];
    }
  }
}
