package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryTreeInorderTraversal94 {

    class Solution {

        public List<Integer> inorderTraversal(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            List<Integer> result = root.left == null
                ? new ArrayList<>()
                : inorderTraversal(root.left);
            List<Integer> rightList = root.right == null
                ? new ArrayList<>()
                : inorderTraversal(root.right);

            result.add(root.val);
            result.addAll(rightList);
            return result;
        }
    }

    class Solution2 {

        public List<Integer> inorderTraversal(TreeNode root) {
            if (root == null) {
                return Collections.emptyList();
            }
            List<Integer> left = root.left == null
                ? null
                : inorderTraversal(root.left);
            List<Integer> right = root.right == null
                ? null
                : inorderTraversal(root.right);

            List<Integer> result;

            if (left != null) {
                result = left;
                result.add(root.val);
            } else {
                result =
                    new ArrayList<>() {
                        {
                            add(root.val);
                        }
                    };
            }
            if (right != null) {
                result.addAll(right);
            }
            return result;
        }
    }
}
