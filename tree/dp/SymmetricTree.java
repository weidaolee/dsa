package tree.dp;

import java.util.LinkedList;
import java.util.Queue;

import tree.TreeNode;

public class SymmetricTree {
    /**
     * Link:
     * https://leetcode-cn.com/problems/symmetric-tree/
     *
     * Main idea:
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     *
     * 每次檢查兩個 node: left and right,
     * 如果 left.val = right.val
     * 則檢查 left 的 left 是否等於 right 的 right
     * 且 left 的 right 是否等於 right 的 left
     *
     * 
     */

    private boolean isSymmetric (TreeNode root) {
        if (root == null) {
            return false;
        }
        return recursive(root.left, root.right);
    }

    private boolean recursive(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }
        return left.val == right.val && recursive(left.left, right.right) && recursive(left.right, right.left);
    }

    private boolean iterative(TreeNode root) {
        Queue <TreeNode> queue = new LinkedList<>();
        TreeNode left = root.left;
        TreeNode right = root.right;

        queue.offer(left);
        queue.offer(right);

        while (!queue.isEmpty()) {
            left = queue.poll();
            right = queue.poll();

            if (left == null && right == null) {
                continue;
            }

            if ((left == null || right == null) || left.val != right.val) {
                return false;
            }

            queue.offer(left.left);
            queue.offer(right.right);

            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }
}
