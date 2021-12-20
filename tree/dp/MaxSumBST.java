package tree.dp;
import tree.TreeNode;

public class MaxSumBST {
    public int maxSumBST (TreeNode root) {
        getInfo(root);
        return maxSum;
    }

    int maxSum = 0;
    public Info getInfo (TreeNode root) {
        if (root == null) {
            return new Info (true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        if (root.left == null && root.right == null) {
            maxSum = Math.max(maxSum, root.val);
            return new Info (true, root.val, root.val, root.val);
        }

        Info left = getInfo(root.left);
        Info right = getInfo(root.right);
        Info info = new Info(false, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);

        if (!(left.isBST && right.isBST)) {
            return info;
        }

        if (left.max < root.val && root.val < right.min) {
            info.isBST = true;
        }

        info.sum = left.sum + root.val + right.sum;
        info.max = Math.max(left.max, Math.max(root.val, right.max));
        info.min = Math.min(left.min, Math.min(root.val, right.min));

        if (info.isBST) {
            maxSum = Math.max(maxSum, info.sum);
        }

        return info;
    }

    private static class Info {
        boolean isBST;
        int sum;
        int min;
        int max;
		public Info(boolean isBST, int sum, int min, int max) {
			this.isBST = isBST;
			this.sum = sum;
			this.min = min;
			this.max = max;
		}

    }
}
