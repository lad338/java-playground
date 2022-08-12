package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LCAofBST235 {

    class NaiveSolution {

        public TreeNode lowestCommonAncestor(
            TreeNode root,
            TreeNode p,
            TreeNode q
        ) {
            List<TreeNode> pAncestors = ancestors(root, p);
            List<TreeNode> qAncestors = ancestors(root, q);

            for (int i = 0; i < pAncestors.size(); i++) {
                for (int j = 0; j < qAncestors.size(); j++) {
                    if (
                        pAncestors.get(pAncestors.size() - 1 - i).val ==
                        qAncestors.get(qAncestors.size() - 1 - j).val
                    ) {
                        return pAncestors.get(pAncestors.size() - 1 - i);
                    }
                }
            }

            return root;
        }

        public List<TreeNode> ancestors(TreeNode node, TreeNode target) {
            List<TreeNode> currentList = new ArrayList<>() {
                {
                    add(node);
                }
            };

            if (target.val < node.val) {
                currentList.addAll(ancestors(node.left, target));
            } else if (target.val > node.val) {
                currentList.addAll(ancestors(node.right, target));
            }
            return currentList;
        }
    }

    class RecursiveSolution {

        public TreeNode lowestCommonAncestor(
            TreeNode root,
            TreeNode p,
            TreeNode q
        ) {
            if (p.val > root.val && q.val > root.val) {
                return lowestCommonAncestor(root.right, p, q);
            }

            if (p.val < root.val && q.val < root.val) {
                return lowestCommonAncestor(root.left, p, q);
            }

            return root;
        }
    }
}
