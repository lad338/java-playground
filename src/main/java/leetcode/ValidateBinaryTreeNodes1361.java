package leetcode;

import java.util.*;

public class ValidateBinaryTreeNodes1361 {

    class Solution {

        Set<Integer> visited = new HashSet<>();

        public boolean validateBinaryTreeNodes(
            int n,
            int[] leftChild,
            int[] rightChild
        ) {
            Set<Integer> nodes = new HashSet<>();
            for (int child : leftChild) {
                if (child != -1) {
                    nodes.add(child);
                }
            }
            for (int child : rightChild) {
                if (child != -1) {
                    nodes.add(child);
                }
            }

            if (nodes.size() == n) {
                return false;
            }

            int root = -1;
            for (int i = 0; i < n; i++) {
                if (!nodes.contains(i)) {
                    root = i;
                }
            }

            return dfs(root, leftChild, rightChild) && visited.size() == n;
        }

        private boolean dfs(int i, int[] leftChild, int[] rightChild) {
            if (i == -1) {
                return true;
            }
            if (visited.contains(i)) {
                return false;
            }
            visited.add(i);
            return (
                dfs(leftChild[i], leftChild, rightChild) &&
                dfs(rightChild[i], leftChild, rightChild)
            );
        }
    }
}
