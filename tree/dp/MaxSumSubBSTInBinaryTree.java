package tree.dp;

import tree.TreeNode;

public class MaxSumSubBSTInBinaryTree {
    /**
     * Link:
     * https://leetcode-cn.com/problems/maximum-sum-bst-in-binary-tree/
     *
     */
    public int largestBSTSubtree (TreeNode root) {

        Info info = getInfo(root);
        return info.largestSubBSTSize;
    }

    private Info getInfo (TreeNode root) {
        if (root == null) {
            return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        Info left = getInfo(root.left);
        Info right = getInfo(root.right);

        int num = left.num + 1 + right.num;
        int min = Math.min(root.val, Math.min(left.min, right.min));
        int max = Math.max(root.val, Math.max(left.max, right.max));

        boolean isBST = false;

        if (left.isBST && right.isBST && left.max < root.val && root.val < right.min) {
            isBST = true;
        }

        int largestBSTSubtree = isBST ? num : Math.max(left.largestSubBSTSize, right.largestSubBSTSize);

        return new Info(isBST, num, min, max, largestBSTSubtree);
    }

    private static class Info {
        boolean isBST;
        int num;
        int min;
        int max;
        int largestSubBSTSize;

		public Info(boolean isBST, int num, int min, int max, int largestSubBSTSize) {
            this.isBST = isBST;
			this.num = num;
			this.min = min;
			this.max = max;
            this.largestSubBSTSize = largestSubBSTSize;
		}
    }

}
