package leetcode;

import java.util.ArrayList;
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
}
