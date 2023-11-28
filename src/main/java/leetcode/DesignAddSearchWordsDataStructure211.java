package leetcode;

import java.util.*;

public class DesignAddSearchWordsDataStructure211 {

    class WordDictionary {

        PrefixTree root = new PrefixTree();

        Set<String> searchCache = new HashSet<>();
        Set<String> addCache = new HashSet<>();

        public static class PrefixTree {

            public boolean isEnd;
            public Map<Character, PrefixTree> childrenMap = new HashMap<>();
        }

        public WordDictionary() {}

        public void addWord(String word) {
            if (addCache.contains(word)) {
                return;
            }
            addCache.add(word);
            addWord(word, 0, root);
        }

        private void addWord(String word, int index, PrefixTree node) {
            if (index == word.length()) {
                node.isEnd = true;
                return;
            }

            char c = word.charAt(index);
            node.childrenMap.putIfAbsent(c, new PrefixTree());
            addWord(word, index + 1, node.childrenMap.get(c));
        }

        public boolean search(String word) {
            if (searchCache.contains(word)) {
                return true;
            }

            boolean result = search(word, 0, root);

            if (result) {
                searchCache.add(word);
            }
            return result;
        }

        private boolean search(String word, int index, PrefixTree node) {
            if (index == word.length()) {
                return node.isEnd;
            }

            char c = word.charAt(index);

            if (c != '.' && !node.childrenMap.containsKey(c)) {
                return false;
            }

            if (c != '.') {
                return search(word, index + 1, node.childrenMap.get(c));
            }

            for (Character key : node.childrenMap.keySet()) {
                if (search(word, index + 1, node.childrenMap.get(key))) {
                    return true;
                }
            }

            return false;
        }
    }
    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */
}
