package tree.traverse;

import tree.TreeNode;

public class InOrderSuccessorInBinaryTreeII {
    /**
     * Link:
     * https://leetcode-cn.com/problems/inorder-successor-in-bst-ii/
     *
     * Main idea:
     *   1. 如果 p 有 右子樹, 右子樹的 最左的 node, 是 successor
     *   2. 如果 p 沒有 右子樹, 問自己的 parent 自己是不是 left child, 如果是, parent 是 successor
     *
     */
    public TreeNode inorderSuccessor (TreeNode root, TreeNode p) {
        if (root == null || (root.left == null && root.right == null)) {
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

        // root = p and root without right tree
        if (root == p && root.right == null) {
            return null;
        }

        // search p
        successor = root;
        TreeNode cur = successor;
        int leftStept = 0;
        while (cur.val != p.val && cur != null) {
            if (p.val < cur.val) {
                // successor is parent
                successor = cur;
                cur = cur.left;
                leftStept ++;
            } else if (p.val > cur.val) {
                // successor is an acentor but not parent
                cur = cur.right;
            }
        }
        // if leftStept = 0, then p is the rightest node of whole tree, successor is null
        return leftStept > 0 ? successor : null;
    }

    private Node withParentInorderSuccessor(Node node) {
        if (node == null) {
            return null;
        }

        Node successor;
        if (node.right != null) {
            successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor;
        } else {
            while (node.parent != null && node.parent.left != node) {
                node = node.parent;
            }
            return node.parent; // node.parent = {null, successor}
        }
    }
    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
}

