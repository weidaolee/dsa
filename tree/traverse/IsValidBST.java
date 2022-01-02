package tree.traverse;

import java.util.Stack;

import tree.TreeNode;

public class IsValidBST {
    /**
     * 合法的 BST 要滿足
     * 1. 左子樹所有 node < root
     * 2. 右子樹所有 node > root
     */

    long preValue = Long.MIN_VALUE;
    public boolean recursiveInorderValidateBST (TreeNode root) {
        /**
         BST in-order 必須是嚴格生序
         在 in-order 處裡時紀錄當前 node, 在 recursion 才可以進行比較
         */
        if (root == null) {
            return true;
        }

        boolean leftIsBST = recursiveInorderValidateBST(root.left);

        // process
        if (!leftIsBST) {
            return false;
        }
        if (root.val <= preValue) {
            return false;
        } else {
            preValue = root.val;
        }
        // end of process
        return recursiveInorderValidateBST (root.right);
    }

    public boolean iterativeInorderValidateBST(TreeNode root) {

        Stack <TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                // process
                root = stack.pop();
                if (preValue >= root.val) {
                    return false;
                } else {
                    preValue = root.val;
                }
                // end of process
                root = root.right;
            }
        }
        return true;
    }
}
