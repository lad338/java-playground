package leetcode;

import java.util.HashMap;
import java.util.Map;

public class UniqueBinarySearchTrees96 {

  static class Solution {

    public int numTrees(int n) {
      Map<Integer, Integer> possibilities = new HashMap<>() {
        {
          put(1, 1);
        }
      };

      for (int i = 1; i <= n; i++) {
        int subTotal = 0;
        for (int j = 0; j < i; j++) {
          int rhs = i - j - 1;
          subTotal += (j > 0 ? possibilities.get(j) : 1) * (rhs > 0 ? possibilities.get(rhs) : 1);
        }
        possibilities.put(i, subTotal);
      }

      return possibilities.get(n);
    }
  }

  static class PrimitiveSolution {

    public int numTrees(int n) {
      int[] possibilities = new int[20];
      possibilities[1] = 1;
      possibilities[2] = 2;

      for (int i = 3; i <= n; i++) {
        int subTotal = 0;
        for (int j = 0; j < i; j++) {
          int rhs = i - j - 1;
          subTotal += (j > 0 ? possibilities[j] : 1) * (rhs > 0 ? possibilities[rhs] : 1);
        }
        possibilities[i] = subTotal;
      }

      return possibilities[n];
    }
  }

  public static void main(String[] args) {
    Solution sol = new Solution();
    System.out.println(sol.numTrees(5));
  }
}
