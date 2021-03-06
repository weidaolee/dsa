package tree.traverse;

import java.util.LinkedList;
import java.util.Queue;


import tree.TreeNode;

public class CompletenessOfBinaryTree {
    /**
     * Link:
     * https://leetcode-cn.com/problems/check-completeness-of-a-binary-tree/
     *
     */
    public boolean isCompleteTree(TreeNode root) {
        Queue <TreeNode> queue = new LinkedList<>();

        queue.add(root);
        boolean noRight = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null && node.right != null) {
                return false;
            }

            if (noRight && (node.left != null || node.right != null)) {
                return false;
            }

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            } else {
                noRight = true;
            }
        }
        return true;
    }
}
