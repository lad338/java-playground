package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class InvertBinaryTree226 {

    class RecursiveSolution {

        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            } else {
                TreeNode temp = root.left;
                root.left = invertTree(root.right);
                root.right = invertTree(temp);
                return root;
            }
        }
    }

    class stackSolution {

        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }

            Deque<TreeNode> stack = new ArrayDeque<>();

            stack.push(root);

            while (stack.size() != 0) {
                TreeNode current = stack.pop();

                TreeNode temp = current.left;
                current.left = current.right;
                current.right = temp;

                if (current.left != null) {
                    stack.push(current.left);
                }

                if (current.right != null) {
                    stack.push(current.right);
                }
            }

            return root;
        }
    }
}
