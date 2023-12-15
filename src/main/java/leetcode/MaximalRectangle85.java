package leetcode;

import java.util.*;

public class MaximalRectangle85 {

    class Solution {

        public int maximalRectangle(char[][] matrix) {
            int M = matrix.length;
            int N = matrix[0].length;

            int[][] histograms = new int[M][N];

            for (int i = 0; i < N; i++) {
                histograms[0][i] = matrix[0][i] == '1' ? 1 : 0;
            }

            for (int i = 1; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    histograms[i][j] =
                        matrix[i][j] == '0' ? 0 : 1 + histograms[i - 1][j];
                }
            }

            int max = 0;
            for (int[] histogram : histograms) {
                max = Math.max(max, largestRectangleArea(histogram));
            }
            return max;
        }

        // From leetcode 84
        public int largestRectangleArea(int[] heights) {
            // Monotonic Increasing (by height) Stack
            Deque<Integer> stack = new ArrayDeque<>();

            int max = 0;

            for (int i = 0; i < heights.length; i++) {
                int height = heights[i];
                int startIndex = i;
                while (!stack.isEmpty() && heights[stack.peek()] > height) {
                    int poppedIndex = stack.pop();
                    int poppedHeight = heights[poppedIndex];
                    max = Math.max(max, poppedHeight * (i - poppedIndex));
                    startIndex = poppedIndex;
                    heights[startIndex] = height;
                }
                stack.push(startIndex);
                max = Math.max(max, (i - startIndex + 1) * height);
            }

            while (!stack.isEmpty()) {
                int poppedIndex = stack.pop();
                int poppedHeight = heights[poppedIndex];
                max =
                    Math.max(
                        max,
                        poppedHeight * (heights.length - poppedIndex)
                    );
            }

            return max;
        }
    }
}
