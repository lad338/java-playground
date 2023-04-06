package leetcode;

public class RotateImage48 {

    class Solution {

        public void rotate(int[][] matrix) {
            if (matrix.length == 1) {
                return;
            }

            int a = 0;
            while (a < matrix.length - 1 - a) {
                int bound = matrix.length - 1 - a;

                for (int i = 0; i < bound - a; i++) {
                    int leftRight = i + a;
                    int rightLeft = bound - i;

                    swap(matrix, a, leftRight, rightLeft, a);
                    swap(matrix, a, leftRight, leftRight, bound);
                    swap(matrix, leftRight, bound, bound, rightLeft);
                }
                a++;
            }
        }

        public void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
            int temp = matrix[y1][x1];
            matrix[y1][x1] = matrix[y2][x2];
            matrix[y2][x2] = temp;
        }
    }
}
