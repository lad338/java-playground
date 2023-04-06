package leetcode;

import java.util.Arrays;

public class SetMatrixZeroes73 {

    class MNSolution {

        public void setZeroes(int[][] matrix) {
            boolean[] rowStatus = new boolean[matrix.length];
            boolean[] colStatus = new boolean[matrix[0].length];

            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[0].length; col++) {
                    rowStatus[row] = rowStatus[row] || matrix[row][col] == 0;
                    colStatus[col] = colStatus[col] || matrix[row][col] == 0;
                }
            }

            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[0].length; col++) {
                    if (rowStatus[row] || colStatus[col]) {
                        matrix[row][col] = 0;
                    }
                }
            }
        }
    }

    class O1Solution {

        class Solution {

            public void setZeroes(int[][] matrix) {
                boolean[] rowStatus = new boolean[matrix.length];
                boolean[] colStatus = new boolean[matrix[0].length];
                boolean firstRow = false;
                boolean firstCol = false;

                for (int row = 0; row < matrix.length; row++) {
                    for (int col = 0; col < matrix[0].length; col++) {
                        if (matrix[row][col] == 0) {
                            if (row == 0) {
                                firstRow = true;
                            }
                            if (col == 0) {
                                firstCol = true;
                            }
                            matrix[row][0] = 0;
                            matrix[0][col] = 0;
                        }
                    }
                }

                for (int row = 1; row < matrix.length; row++) {
                    for (int col = 1; col < matrix[0].length; col++) {
                        if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                            matrix[row][col] = 0;
                        }
                    }
                }

                if (firstRow) {
                    Arrays.fill(matrix[0], 0);
                }

                if (firstCol) {
                    for (int i = 0; i < matrix.length; i++) {
                        matrix[i][0] = 0;
                    }
                }
            }
        }
    }
}
