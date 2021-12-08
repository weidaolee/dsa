package tree.basic;

import tree.TreeNode;

public class HasPathSum {
    public boolean hasPathSum (TreeNode root, int targetSum) {
        return recursiveHasPathSum(root, 0,  targetSum);
    }


    public boolean recursiveHasPathSum (TreeNode root,int sum, int targetSum) {
        /**
         路徑總和必須從 root 一直累加到 leaf, 使用 pre-order
         要沿路 recursive 把 node 的 info 傳到 leaf 上

         */
        if (root == null) {
            return false;
        }

        sum += root.val;
        if (root.left == null && root.right == null) {
            return sum == targetSum;
        }
        return recursiveHasPathSum(root.left,sum, targetSum) || recursiveHasPathSum(root.right,sum, targetSum);
    }
}
