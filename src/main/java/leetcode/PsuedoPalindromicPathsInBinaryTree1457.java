package leetcode;

import java.util.HashMap;
import java.util.Map;

public class PsuedoPalindromicPathsInBinaryTree1457 {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    class Solution {

        public int pseudoPalindromicPaths(TreeNode root) {
            if (root == null) {
                return 0;
            }

            return pseudoPalindromicPaths(root, new HashMap<>());
        }

        public int pseudoPalindromicPaths(
            TreeNode node,
            Map<Integer, Integer> map
        ) {
            map.put(node.val, map.getOrDefault(node.val, 0) + 1);

            if (node.left == null && node.right == null) {
                return isPathPseudoPalindromic(map) ? 1 : 0;
            }

            int left = 0;
            int right = 0;

            if (node.left != null) {
                Map<Integer, Integer> leftMap = new HashMap<>(map);
                left = pseudoPalindromicPaths(node.left, leftMap);
            }

            if (node.right != null) {
                Map<Integer, Integer> rightMap = new HashMap<>(map);
                right = pseudoPalindromicPaths(node.right, rightMap);
            }

            return left + right;
        }

        public boolean isPathPseudoPalindromic(Map<Integer, Integer> map) {
            boolean hasOneOdd = false;
            for (int i = 1; i <= 9; i++) {
                int currentCount = map.getOrDefault(i, 0);
                if (currentCount % 2 != 0) {
                    if (hasOneOdd) {
                        return false;
                    }
                    hasOneOdd = true;
                }
            }
            return true;
        }
    }

    class BitSolution {

        public int pseudoPalindromicPaths(TreeNode root) {
            return pseudoPalindromicPaths(root, 0);
        }

        private int pseudoPalindromicPaths(TreeNode root, int count) {
            if (root == null) return 0;

            count ^= 1 << (root.val - 1);

            if (root.left == root.right) {
                return (count & (count - 1)) == 0 ? 1 : 0;
            }

            int left = root.left == null
                ? 0
                : pseudoPalindromicPaths(root.left, count);

            int right = root.right == null
                ? 0
                : pseudoPalindromicPaths(root.right, count);

            return left + right;
        }
    }
}
