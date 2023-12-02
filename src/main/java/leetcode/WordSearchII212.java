package leetcode;

import java.util.*;

public class WordSearchII212 {

    class Solution {

        class Node {

            public boolean isEnd;
            public Map<Character, Node> children = new HashMap<>();

            public void addWord(String word) {
                Node node = this;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    node.children.putIfAbsent(c, new Node());
                    node = node.children.get(c);
                }
                node.isEnd = true;
            }
        }

        Node root = new Node();
        char[][] board;
        int ROW;
        int COL;
        int maxLength = 0;
        Set<String> result = new HashSet<>();

        public List<String> findWords(char[][] board, String[] words) {
            this.board = board;
            ROW = board.length;
            COL = board[0].length;

            for (String word : words) {
                maxLength = Math.max(maxLength, word.length());
                root.addWord(word);
            }

            for (int r = 0; r < ROW; r++) {
                for (int c = 0; c < COL; c++) {
                    search(r, c, root, new HashSet<>(), "");
                }
            }

            return new ArrayList<>(result);
        }

        private void search(
            int r,
            int c,
            Node node,
            Set<Integer> visited,
            String currentString
        ) {
            if (r < 0 || c < 0 || r >= ROW || c >= COL) {
                return;
            }
            if (!node.children.containsKey(board[r][c])) {
                return;
            }
            int visitValue = r * COL + c;
            if (visited.contains(visitValue)) {
                return;
            }
            node = node.children.get(board[r][c]);
            currentString += board[r][c];
            if (node.isEnd) {
                result.add(currentString);
            }
            if (currentString.length() >= maxLength) {
                return;
            }
            visited.add(visitValue);
            search(r - 1, c, node, visited, currentString);
            search(r + 1, c, node, visited, currentString);
            search(r, c - 1, node, visited, currentString);
            search(r, c + 1, node, visited, currentString);
            visited.remove(visitValue);
        }
    }

    // 0 1 2 3
    // 4 5 6 7

    class Solution2 {

        class Node {

            public String word = null;
            public Node[] children = new Node[26];

            public void addWord(String word) {
                Node node = this;
                for (char c : word.toCharArray()) {
                    if (node.children[c - 'a'] == null) {
                        node.children[c - 'a'] = new Node();
                    }
                    node = node.children[c - 'a'];
                }
                node.word = word;
            }
        }

        Node root = new Node();
        char[][] board;
        int ROW;
        int COL;
        int maxLength = 0;
        Set<String> result = new HashSet<>();

        public List<String> findWords(char[][] board, String[] words) {
            this.board = board;
            ROW = board.length;
            COL = board[0].length;

            for (String word : words) {
                maxLength = Math.max(maxLength, word.length());
                root.addWord(word);
            }

            for (int r = 0; r < ROW; r++) {
                for (int c = 0; c < COL; c++) {
                    search(r, c, root, 0);
                }
            }

            return new ArrayList<>(result);
        }

        private void search(int r, int c, Node node, int count) {
            if (r < 0 || c < 0 || r >= ROW || c >= COL) {
                return;
            }
            if (board[r][c] == '#') {
                return;
            }
            char ch = board[r][c];
            if (node.children[ch - 'a'] == null) {
                return;
            }

            node = node.children[ch - 'a'];
            count++;
            if (node.word != null) {
                result.add(node.word);
            }
            if (count >= maxLength) {
                return;
            }
            board[r][c] = '#';
            search(r - 1, c, node, count);
            search(r + 1, c, node, count);
            search(r, c - 1, node, count);
            search(r, c + 1, node, count);
            board[r][c] = ch;
        }
    }
}
