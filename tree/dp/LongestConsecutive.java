package tree.dp;

import tree.TreeNode;

public class LongestConsecutive {

    int maxLength = 1;
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        bottomupCumLength(root);
        return maxLength;
    }

    public int bottomupCumLength (TreeNode node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        int leftLength = bottomupCumLength(node.left);
        maxLength = Math.max(maxLength, leftLength);
        if (node.left != null) {
            leftLength = node.val + 1 == node.left.val ? leftLength + 1 : 1;
        }

        int rightLength = bottomupCumLength(node.right);
        maxLength = Math.max(maxLength, rightLength);
        if (node.right != null) {
            rightLength = node.val + 1 == node.right.val ? rightLength + 1 : 1;
        }

        int curLength = Math.max(leftLength, rightLength);
        maxLength = Math.max(curLength, maxLength);

        return curLength;
    }

    public void topdown (TreeNode root, TreeNode parent, int curLength) {
        /**
         * 這題是從 root 開始到 leaves 結束，以 dfs 視角看待
         * 從 root 開始，如果自己是 root, 則 current length 是 1, 更新 max length，
         * 把 root, current length 1 往下傳
         * =========================================
         * 來到某一個 node, 有 current length L, 以及上面傳下來的 parent p
         * 如果當前 node.val - parent.val = 1, 則 current length 可以 + 1
         * 如果不相等，從這個 node 開始往下 current length 都要重新計算，因此重置為 1
         * 然後把 node, current length 往下傳
         */

        if (root == null) {
            return;
        }
        curLength = parent != null && root.val == parent.val + 1 ? curLength + 1 : 1;
        maxLength = Math.max(curLength, maxLength);
        topdown(root.left, root, curLength);
        topdown(root.right, root, curLength);
    }
}
