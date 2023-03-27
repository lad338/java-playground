package interview;

import java.util.ArrayList;
import java.util.List;

public class ClockwiseMatrix {

    // Defining a class for direction
    public static final class Direction {

        private int x;
        private int y;

        public Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    // Defining the 4 direction of the matrix;
    static final Direction RIGHT = new Direction(1, 0);
    static final Direction DOWN = new Direction(0, 1);
    static final Direction LEFT = new Direction(-1, 0);
    static final Direction UP = new Direction(0, -1);

    public static String clockwiseMatrix(int[][] input) {
        // "short circuit" cases
        if (input == null) {
            return "";
        }

        final int height = input.length;
        if (height == 0) {
            return "";
        }

        final int width = input[0].length;
        if (width == 0) {
            return "";
        }

        // using the clockwise direction
        final List<Direction> directions = List.of(RIGHT, DOWN, LEFT, UP);
        final List<String> results = new ArrayList<>();

        // starting at (0,0)
        int currentX = 0;
        int currentY = 0;
        // storing visited cells
        final boolean[][] visited = new boolean[height][width];
        // starting direction is the first in the list
        int direction = 0;

        // loop through all items
        // here assumes the matrix is a correct input such that all rows have the same number of items
        for (int i = 0; i < input.length * input[0].length; i++) {
            // storing the current item in the result array
            results.add(String.valueOf(input[currentY][currentX]));
            visited[currentY][currentX] = true;

            // find the potential next item
            final int nextX = currentX + directions.get(direction).getX();
            final int nextY = currentY + directions.get(direction).getY();

            // if the potential next item is within the matrix bounds and haven't been visited
            if (
                0 <= nextX &&
                nextX < width &&
                0 <= nextY &&
                nextY < height &&
                !visited[nextY][nextX]
            ) {
                // using the potential next item as the next item in loop
                currentX = nextX;
                currentY = nextY;
            } else {
                // otherwise, change in direction
                // the next direction is the next in the list unless it is already the last, which goes back to the first
                direction = (direction + 1) % directions.size();
                currentX += directions.get(direction).getX();
                currentY += directions.get(direction).getY();
            }
        }

        // return the result with a delimiter to separate values
        return String.join(", ", results);
    }

    public static void main(String[] args) {
        int[][] input = { { 2, 3, 4, 8 }, { 5, 7, 9, 12 }, { 1, 0, 6, 10 } };

        System.out.println(clockwiseMatrix(input));
        assert clockwiseMatrix(input)
            .equals("2, 3, 4, 8, 12, 10, 6, 0, 1, 5, 7, 9");

        input = new int[][] { { 2, 3, 4, 8 } };
        assert clockwiseMatrix(input).equals("2, 3, 4, 8");

        input = new int[][] { { 2 }, { 3 }, { 4 } };
        assert clockwiseMatrix(input).equals("2, 3, 4");

        input =
            new int[][] {
                { 2, 3, 4, 8 },
                { 5, 7, 9, 12 },
                { 1, 0, 6, 10 },
                { 13, 14, 15, 16 },
            };
        assert clockwiseMatrix(input)
            .equals("2, 3, 4, 8, 12, 10, 16, 15, 14, 13, 1, 5, 7, 9, 6, 0");

        input =
            new int[][] {
                { 2, 3, 4 },
                { 5, 7, 9 },
                { 1, 0, 6 },
                { 13, 14, 15 },
            };
        assert clockwiseMatrix(input)
            .equals("2, 3, 4, 9, 6, 15, 14, 13, 1, 5, 7, 0");

        input = new int[][] {};
        assert clockwiseMatrix(input).equals("");

        input = new int[][] { {}, {} };
        assert clockwiseMatrix(input).equals("");

        assert clockwiseMatrix(null).equals("");
    }
}
