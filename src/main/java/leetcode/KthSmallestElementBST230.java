package leetcode;

import java.util.*;

public class KthSmallestElementBST230 {

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

        Deque<TreeNode> stack = new ArrayDeque<>();

        public int kthSmallest(TreeNode root, int k) {
            TreeNode node = root;
            while (true) {
                if (node == null) {
                    node = stack.pop();
                    k--;
                    if (k == 0) {
                        return node.val;
                    }
                    node = node.right;
                    continue;
                }

                stack.push(node);
                node = node.left;
            }
        }
    }

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
    class InorderRecursiveSolution {

        int k;
        int result;

        public int kthSmallest(TreeNode root, int k) {
            this.k = k;
            helper(root);
            return result;
        }

        public void helper(TreeNode node) {
            if (node == null) {
                return;
            }
            helper(node.left);
            k--;
            if (k == 0) {
                result = node.val;
            }
            helper(node.right);
        }
    }
}
