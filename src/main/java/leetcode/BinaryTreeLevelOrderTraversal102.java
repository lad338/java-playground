package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryTreeLevelOrderTraversal102 {

    class Solution {

        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) {
                return Collections.emptyList();
            }
            List<List<Integer>> results = new ArrayList<>();
            fill(results, root, 0);
            return results;
        }

        public void fill(
            List<List<Integer>> existing,
            TreeNode node,
            int height
        ) {
            if (existing.size() > height) {
                List<Integer> current = existing.get(height);
                current.add(node.val);
            } else {
                List<Integer> newList = new ArrayList<>();
                newList.add(node.val);
                existing.add(newList);
            }

            if (node.left != null) {
                fill(existing, node.left, height + 1);
            }
            if (node.right != null) {
                fill(existing, node.right, height + 1);
            }
        }
    }
}
