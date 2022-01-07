package tree.dp;
import tree.TreeNode;

public class BinaryTreeMaxPathSum {
    /**
     * Link:
     * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
     *
     * 總和交給全局變量
     * 遞迴只計算最大路徑合:
     *
     * 必經過 root 的最大路徑合為:
     * 1. root.val + 左樹的最大路徑合取正 + 右樹的最大路徑合取正
     * 2. update maxSum
     * 3. return 中止在此 root 的最佳路徑
     */

    public int maxPathSum(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        }
        getPathMaxSum(root);
        return maxSum;
    }

    int maxSum = Integer.MIN_VALUE;
    private int getPathMaxSum (TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftMaxSum = Math.max(getPathMaxSum(root.left), 0);
        int rightMaxSum = Math.max(getPathMaxSum(root.right), 0);

        maxSum = Math.max(maxSum, root.val + leftMaxSum + rightMaxSum);

        return root.val + Math.max(leftMaxSum, rightMaxSum);
    }
}
