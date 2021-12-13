package tree.traversal;

import tree.*;

public class Morris {
    /**
     * Main idea:
     * pre-order traversal, 但每次先以左樹上的最右的 node 的 right 紀錄自己
     * 等到 左樹 traverse 完畢後，直接跳回自己，然後 traverse 自己的右樹
     *
     * Implementation:
     * cur = root
     * 1. if cur has no left child:
     *     cur = cur.right
     * 2. find the rightest child of the left child x
     *    if x.right = null, x.right = cur
     *    if x.right = cur itself, x.right = null
     */
    public static void morris(TreeNode root) {
        TreeNode cur = root;
        TreeNode rightest;
        while (cur != null) {
            rightest = cur.left;
            if (cur.left == null) {
                process(cur);
                cur = cur.right;
                continue;
            }
            while (rightest.right != null && rightest.right != cur) {
                rightest = rightest.right;
            }
            if (rightest.right == null) {
                process(cur);
                rightest.right = cur;
                cur = cur.left;
            } else {
                rightest.right = null;
                cur = cur.right;
            }
        }
    }

    public static void preOrder(TreeNode root) {
        /**
         * 在 morris-order 基礎上:
         *   1. 只能經過 1 次的 node, 直接 process
         *   2. 可以經過 2 次的 node, 在第 1 次 process
         */
        TreeNode cur = root;
        TreeNode rightest;
        while (cur != null) {
            rightest = cur.left;
            if (cur.left == null) {
                process(cur);
                cur = cur.right;
                continue;
            }
            while (rightest.right != null && rightest.right != cur) {
                rightest = rightest.right;
            }
            if (rightest.right == null) {
                process(cur);
                rightest.right = cur;
                cur = cur.left;
            } else {
                rightest.right = null;
                cur = cur.right;
            }
        }
    }

    public static void inOrder (TreeNode root) {
        /**
         * 在 morris-order 基礎上:
         *   1. 只能經過 1 次的 node, 直接 process
         *   2. 可以經過 2 次的 node, 在第 2 次 process
         */

        TreeNode cur = root;
        TreeNode rightest;
        while (cur != null) {
            rightest = cur.left;
            if (rightest == null) {
                process(cur);
                cur = cur.right;
                continue;
            }
            while (rightest.right != null && rightest.right != cur) {
                rightest = rightest.right;
            }
            if (rightest.right == null) {
                rightest.right = cur;
                cur = cur.left;
            } else {
                process(cur);
                rightest.right = null;
                cur = cur.right;
            }
        }

    }
    public static void postOrder (TreeNode root) {
        /**
         * Mian idea:
         * 在 morris-order 基礎上:
         * 1. 只在能 traverse 2 次的 node process
         * 2. 只在第 2 次 process
         * 3. process 是逆序 process 該 node 的左樹上的右界 right edge
         * 4. 離開 while 時 逆序 process 整棵樹的右界 right edge
         * note: 逆序 process: 使用 reverse linked list 方法，處理完後再恢復原狀
         * Implementation:
         */
        TreeNode cur = root;
        TreeNode rightest;
        while (cur != null) {
            rightest = cur.left;
            if (rightest == null) {
                cur = cur.right;
                continue;
            }
            while (rightest.right != null && rightest.right != cur) {
                rightest = rightest.right;
            }

            if (rightest.right == null) {
                rightest.right = cur;
                cur = cur.left;
            } else {
                processRightEdge(cur.left);
                rightest.right = null;
                cur = cur.right;
            }
        }
        processRightEdge(root);
    }

    public static void processRightEdge (TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode tail = reverseRightEdge(root);
        TreeNode cur = tail;
        while (cur.right != null) {
            process(cur);
            cur = cur.right;
        }
        reverseRightEdge(tail);
    }
    public static TreeNode reverseRightEdge (TreeNode root) {
        TreeNode pre = null;
        TreeNode cur = root;
        TreeNode nxt = root;

        while (cur != null) {
            nxt = root.right;
            cur.right = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    public static void process (TreeNode root) {
        System.out.println(root.val);
    }

	public static void main(String[] args) {
		TreeNode head = new TreeNode(4);
		head.left = new TreeNode(2);
		head.right = new TreeNode(6);
		head.left.left = new TreeNode(1);
		head.left.right = new TreeNode(3);
		head.right.left = new TreeNode(5);
		head.right.right = new TreeNode(7);
		morris(head);
    }
}
