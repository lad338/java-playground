package interview;

public class WordSearchInGrid {

    // you can also use imports, for example:
    // import java.util.*;

    // M O R S
    // M C W E
    // S H O L
    // A N C K

    public static void main(String[] args) {
        // you can write to stdout for debugging purposes, e.g.
        char[][] grid = new char[][] {
            { 'M', 'O', 'R', 'S' },
            { 'M', 'C', 'W', 'E' },
            { 'S', 'H', 'O', 'L' },
            { 'A', 'N', 'C', 'K' },
        };

        try {
            System.out.println(searchWordInGrid("MCOKLESRS", grid));
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public static boolean searchWordInGrid(String word, char[][] grid)
        throws Exception {
        //TODO validate grid
        int cols = grid[0].length;
        for (char[] row : grid) {
            if (row.length != cols) {
                throw new Exception("sth");
            }
        }

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (searchWordLeftInGrid(word, grid, r, c, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean searchWordLeftInGrid(
        String word,
        char[][] grid,
        int r,
        int c,
        boolean[][] visited
    ) {
        if (
            r >= grid.length ||
            c >= grid[0].length ||
            r < 0 ||
            c < 0 ||
            visited[r][c] ||
            grid[r][c] != word.charAt(0)
        ) {
            return false;
        }

        if (word.length() == 1 && grid[r][c] == word.charAt(0)) {
            return true;
        }

        String wordLeft = word.substring(1);
        visited[r][c] = true;

        for (int nextR = r - 1; nextR <= r + 1; nextR++) {
            for (int nextC = c - 1; nextC <= c + 1; nextC++) {
                if (
                    searchWordLeftInGrid(wordLeft, grid, nextR, nextC, visited)
                ) {
                    return true;
                }
            }
        }
        visited[r][c] = false;
        return false;
    }
}
