package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MakingALargeIsland827 {

  class Solution {

    int width; //x
    int height; //y

    int[][] g;
    int[][] islandIndices; //starts at 1, 0 indicates not searched

    final int[] singleVectorX = new int[] { 0, 0, 1, -1 };
    final int[] singleVectorY = new int[] { 1, -1, 0, 0 };

    public int largestIsland(int[][] grid) {
      g = grid;
      width = grid.length;
      height = grid[0].length;
      islandIndices = new int[width][height];
      Map<Integer, Integer> islandSizes = new HashMap<>();

      //DFSing all island
      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          if (grid[i][j] == 1 && islandIndices[i][j] == 0) {
            final int size = dfs(i, j, islandSizes.size() + 1);
            islandSizes.put(islandSizes.size() + 1, size);
          }
        }
      }

      int max = 0;

      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          if (grid[i][j] == 0) {
            Set<Integer> includedIsland = new HashSet<>();
            int subTotal = 1;
            for (int h = 0; h < 4; h++) {
              int checkX = i + singleVectorX[h];
              int checkY = j + singleVectorY[h];
              if (checkX >= 0 && checkX < width && checkY >= 0 && checkY < height) {
                int islandIndex = islandIndices[checkX][checkY];
                if (islandIndex != 0 && !includedIsland.contains(islandIndex)) {
                  includedIsland.add(islandIndex);
                  subTotal += islandSizes.get(islandIndex);
                }
              }
            }

            max = Math.max(subTotal, max);
          }
        }
      }
      return max == 0 ? width * height : max;
    }

    public int dfs(int x, int y, int index) { //return the size
      islandIndices[x][y] = index;

      int result = 1;
      for (int i = 0; i < 4; i++) {
        int checkX = x + singleVectorX[i];
        int checkY = y + singleVectorY[i];
        if (
          checkX >= 0 &&
          checkX < width &&
          checkY >= 0 &&
          checkY < height &&
          g[checkX][checkY] == 1 &&
          islandIndices[checkX][checkY] == 0
        ) { //not yet searched land
          result += dfs(checkX, checkY, index); // recursive call
        }
      }
      return result;
    }
  }
}
