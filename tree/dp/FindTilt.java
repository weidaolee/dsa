package tree.dp;

import tree.TreeNode;

public class FindTilt {
    /**
       遞迴累加所有的 node val, 且每個 node 更新全局的 tilt 加總
     */

    int totalTilt = 0;
    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }

        bottomupCumNodeValue(root);
        return totalTilt;
    }

    public int bottomupCumNodeValue (TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftValueSum = bottomupCumNodeValue(root.left);
        int rightValueSum = bottomupCumNodeValue(root.right);
        totalTilt += Math.abs(leftValueSum - rightValueSum);

        return root.val + leftValueSum + rightValueSum;
    }

}
