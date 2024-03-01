package leetcode.tree;

import java.util.*;
import leetcode.TreeNode;

public class EvenOddTree1609 {

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

        public boolean isEvenOddTree(TreeNode root) {
            if (root == null) {
                return true;
            }

            Deque<TreeNode> queue = new ArrayDeque<>();

            queue.offer(root);

            int row = 0;

            while (!queue.isEmpty()) {
                Deque<TreeNode> nextQueue = new ArrayDeque<>();
                int previous = row % 2 == 0 ? 0 : ((int) 1e6 + 1);

                while (!queue.isEmpty()) {
                    TreeNode node = queue.poll();
                    int value = node.val;
                    if (row % 2 == 0) {
                        if (value % 2 == 0) {
                            return false;
                        }
                        if (value <= previous) {
                            return false;
                        }
                    } else {
                        if (value % 2 != 0) {
                            return false;
                        }
                        if (value >= previous) {
                            return false;
                        }
                    }

                    previous = value;

                    if (node.left != null) {
                        nextQueue.offer(node.left);
                    }
                    if (node.right != null) {
                        nextQueue.offer(node.right);
                    }
                }
                queue = nextQueue;
                row++;
            }

            return true;
        }
    }
}
