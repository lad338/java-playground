package leetcode;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku36 {

    class InitialSolution {

        public boolean isValidSudoku(char[][] board) {
            for (int i = 0; i < 9; i++) {
                Set<Character> rowSet = new HashSet<>();
                Set<Character> colSet = new HashSet<>();
                for (int j = 0; j < 9; j++) {
                    if (colSet.contains(board[i][j])) {
                        return false;
                    } else {
                        if (board[i][j] != '.') {
                            colSet.add(board[i][j]);
                        }
                    }
                    if (rowSet.contains(board[j][i])) {
                        return false;
                    } else {
                        if (board[j][i] != '.') {
                            rowSet.add(board[j][i]);
                        }
                    }
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Set<Character> squareSet = new HashSet<>();
                    for (int a = 0; a < 3; a++) {
                        for (int b = 0; b < 3; b++) {
                            char current = board[i * 3 + a][j * 3 + b];
                            if (squareSet.contains(current)) {
                                return false;
                            } else if (current != '.') {
                                squareSet.add(current);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }

    class OnePassSolution {

        public boolean isValidSudoku(char[][] board) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Set<Character> rowSet = new HashSet<>();
                    Set<Character> colSet = new HashSet<>();
                    Set<Character> squareSet = new HashSet<>();
                    for (int a = 0; a < 3; a++) {
                        for (int b = 0; b < 3; b++) {
                            char row = board[a * 3 + b][i * 3 + j];
                            char col = board[i * 3 + j][a * 3 + b];
                            char square = board[i * 3 + a][j * 3 + b];
                            if (squareSet.contains(square)) {
                                return false;
                            } else if (square != '.') {
                                squareSet.add(square);
                            }
                            if (rowSet.contains(row)) {
                                return false;
                            } else if (row != '.') {
                                rowSet.add(row);
                            }
                            if (colSet.contains(col)) {
                                return false;
                            } else if (col != '.') {
                                colSet.add(col);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}
