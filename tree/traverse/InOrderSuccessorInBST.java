package tree.traverse;

import java.util.Stack;

import tree.TreeNode;

public class InOrderSuccessorInBST {
    /**
     * Link:
     * https://leetcode-cn.com/problems/inorder-successor-in-bst/
     *
     * Main idea:
     *   1. 如果 p 有 右子樹, 右子樹的 最左的 node, 是 successor
     *   2. 如果 p 沒有 右子樹, 問自己的 parent 自己是不是 left child, 如果是, parent 是 successor
     *
     */


    public TreeNode inorderSuccessor (TreeNode root, TreeNode p) {
        if (root == null || root.left == null && root.right == null) {
            return null;
        }

        TreeNode successor;
        if (p.right != null) {
            successor = p.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != p) {
            stack.push(cur);
            cur = p.val < cur.val ? cur.left : cur.right;
        }

        TreeNode parent;
        while (!stack.isEmpty()) {
            parent = stack.pop();
            if (cur.val < parent.val) {
                return parent;
            } else {
                cur = parent;
            }
        }
        return null;
    }
}
