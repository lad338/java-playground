package leetcode;

public class ConstructStringFromBinaryTree606 {

    class Solution {

        public String tree2str(TreeNode root) {
            String right = root.right != null
                ? "(" + tree2str(root.right) + ")"
                : "";

            String left = root.left != null
                ? "(" + tree2str(root.left) + ")"
                : right.equals("") ? "" : "()";

            return root.val + left + right;
        }
    }
}
