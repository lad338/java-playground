package leetcode;

public class ValidateBinarySearchTree98 {

    class Solution {

        public boolean isValidBST(TreeNode root) {
            return isValid(root, null, null);
        }

        public boolean isValid(TreeNode node, Integer max, Integer min) {
            return (
                (max == null || max > node.val) &&
                (min == null || min < node.val) &&
                (node.left == null || isValid(node.left, node.val, min)) &&
                (
                    node.right == null ||
                    isValid(
                        node.right,
                        max,
                        min == null ? node.val : Math.max(min, node.val)
                    )
                )
            );
        }
    }
}
