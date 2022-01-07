package tree.dp;

import tree.TreeNode;

public class PathSumI {
    /**
     * Link:
     * https://leetcode-cn.com/problems/path-sum/
     *
     */
    public boolean hasPathSum (TreeNode root, int targetSum) {
        return recursion(root, 0,  targetSum);
    }


    private boolean recursion (TreeNode root,int sum, int targetSum) {

        if (root == null) {
            return false;
        }

        sum += root.val;
        if (root.left == null && root.right == null) {
            return sum == targetSum;
        }
        return recursion(root.left,sum, targetSum) || recursion(root.right,sum, targetSum);
    }
}
