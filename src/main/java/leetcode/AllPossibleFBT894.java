package leetcode;

import java.util.*;

public class AllPossibleFBT894 {

    class Solution {

        public List<TreeNode> allPossibleFBT(int n) {
            if (n % 2 == 0) {
                return Collections.emptyList();
            }

            Map<Integer, List<TreeNode>> results = new HashMap<>();
            results.put(1, List.of(newSingleNode()));

            for (int i = 3; i <= n; i += 2) {
                List<TreeNode> possibles = results.get(i - 2);
                List<TreeNode> newPossibles = new ArrayList<>();
                Set<List<Integer>> newSerializedPossibles = new HashSet<>();
                for (TreeNode node : possibles) {
                    List<TreeNode> toCheckList = getAllPossibleChanges(node);
                    for (TreeNode toCheck : toCheckList) {
                        List<Integer> serialized = new ArrayList<>();
                        serializeTree(toCheck, serialized);
                        if (!newSerializedPossibles.contains(serialized)) {
                            newPossibles.add(toCheck);
                            newSerializedPossibles.add(serialized);
                        }
                    }
                }

                results.put(i, newPossibles);
            }

            return results.get(n);
        }

        private TreeNode newSingleNode() {
            return new TreeNode(0);
        }

        private void replaceNodeWith3Nodes(TreeNode node) {
            node.left = newSingleNode();
            node.right = newSingleNode();
        }

        private void replaceNodeWithSingle(TreeNode node) {
            node.left = null;
            node.right = null;
        }

        private TreeNode copyTree(TreeNode node) {
            if (node == null) {
                return null;
            }

            TreeNode result = newSingleNode();
            result.left = copyTree(node.left);
            result.right = copyTree(node.right);
            return result;
        }

        private void getAllPathToLeaves(
            TreeNode node,
            List<Deque<Boolean>> found,
            Deque<Boolean> path
        ) {
            if (node.left == null || node.right == null) {
                Deque<Boolean> copyPath = new ArrayDeque<>(path);
                found.add(copyPath);
            } else {
                Deque<Boolean> copyPathLeft = new ArrayDeque<>(path);
                Deque<Boolean> copyPathRight = new ArrayDeque<>(path);
                copyPathLeft.add(false);
                copyPathRight.add(true);
                getAllPathToLeaves(node.left, found, copyPathLeft);
                getAllPathToLeaves(node.right, found, copyPathRight);
            }
        }

        private List<TreeNode> getAllPossibleChanges(TreeNode base) {
            List<TreeNode> results = new ArrayList<>();
            List<Deque<Boolean>> pathToLeaves = new ArrayList<>();

            getAllPathToLeaves(base, pathToLeaves, new ArrayDeque<>());

            for (Deque<Boolean> path : pathToLeaves) {
                TreeNode node = buildTreeFromPath(base, path);
                results.add(node);
            }

            return results;
        }

        private TreeNode buildTreeFromPath(TreeNode base, Deque<Boolean> path) {
            TreeNode node = base;

            while (path.size() > 0) {
                if (path.poll()) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }

            replaceNodeWith3Nodes(node);
            TreeNode result = copyTree(base);
            replaceNodeWithSingle(node);
            return result;
        }

        private void serializeTree(TreeNode base, List<Integer> list) {
            list.add(base.val);
            if (base.left == null) {
                list.add(1);
            }
            if (base.right == null) {
                list.add(1);
            }
            if (base.left != null) {
                serializeTree(base.left, list);
            }

            if (base.right != null) {
                serializeTree(base.right, list);
            }
        }
    }
}
