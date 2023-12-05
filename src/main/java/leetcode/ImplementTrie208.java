package leetcode;

import java.util.*;

public class ImplementTrie208 {

    class Trie {

        public class Node {

            public boolean isEnd;
            public Map<Character, Node> children = new HashMap<>();
        }

        Set<String> wordCache = new HashSet<>();
        Set<String> prefixCache = new HashSet<>();

        Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            Node node = root;
            for (char c : word.toCharArray()) {
                node.children.putIfAbsent(c, new Node());
                node = node.children.get(c);
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            if (wordCache.contains(word)) {
                return true;
            }
            Node node = root;
            for (char c : word.toCharArray()) {
                node = node.children.get(c);
                if (node == null) {
                    return false;
                }
            }
            if (node.isEnd) {
                wordCache.add(word);
            }
            return node.isEnd;
        }

        public boolean startsWith(String prefix) {
            if (prefixCache.contains(prefix)) {
                return true;
            }
            Node node = root;
            for (char c : prefix.toCharArray()) {
                node = node.children.get(c);
                if (node == null) {
                    return false;
                }
            }
            prefixCache.add(prefix);
            return true;
        }
    }
    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
}
