package tree.dp;

import tree.TreeNode;

public class BinaryTreeTilt {
    /**
     * Link:
     * https://leetcode-cn.com/problems/binary-tree-tilt/
     *
     */

    int totalTilt = 0;
    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }

        bottomupCumNodeValue(root);
        return totalTilt;
    }

    private int bottomupCumNodeValue (TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftValueSum = bottomupCumNodeValue(root.left);
        int rightValueSum = bottomupCumNodeValue(root.right);
        totalTilt += Math.abs(leftValueSum - rightValueSum);

        return root.val + leftValueSum + rightValueSum;
    }

}
