package leetcode;

public class WordSearch79 {

    class Solution {

        int[] dx = new int[] { 0, 0, 1, -1 };
        int[] dy = new int[] { 1, -1, 0, 0 };

        public boolean exist(char[][] board, String word) {
            if (word.length() > board.length * board[0].length) {
                return false;
            }

            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[0].length; col++) {
                    if (board[row][col] == word.charAt(0)) {
                        if (
                            dfs(
                                board,
                                word.substring(1),
                                row,
                                col,
                                word.charAt(0)
                            )
                        ) {
                            return true;
                        }
                    }
                }
            }

            return false;
        }

        private boolean dfs(
            char[][] board,
            String word,
            int row,
            int col,
            char c
        ) {
            if (word.length() == 0) {
                return true;
            }

            board[row][col] = '!';

            for (int i = 0; i < 4; i++) {
                int nextRow = row + dx[i];
                int nextCol = col + dy[i];

                if (
                    nextRow >= 0 &&
                    nextRow < board.length &&
                    nextCol >= 0 &&
                    nextCol < board[0].length
                ) {
                    if (
                        board[nextRow][nextCol] == word.charAt(0) &&
                        dfs(
                            board,
                            word.substring(1),
                            nextRow,
                            nextCol,
                            word.charAt(0)
                        )
                    ) {
                        return true;
                    }
                }
            }
            board[row][col] = c;

            return false;
        }
    }
}
