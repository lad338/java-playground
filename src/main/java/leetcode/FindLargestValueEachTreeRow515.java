package leetcode;

import java.util.*;

public class FindLargestValueEachTreeRow515 {

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

        public List<Integer> largestValues(TreeNode root) {
            if (root == null) {
                return Collections.emptyList();
            }
            List<Integer> result = new ArrayList<>();

            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            while (queue.size() > 0) {
                int length = queue.size();
                int max = queue.peek().val;
                for (int i = 0; i < length; i++) {
                    TreeNode removed = queue.remove();
                    if (removed.left != null) {
                        queue.add(removed.left);
                    }
                    if (removed.right != null) {
                        queue.add(removed.right);
                    }
                    max = Math.max(max, removed.val);
                }
                result.add(max);
            }

            return result;
        }
    }
}
