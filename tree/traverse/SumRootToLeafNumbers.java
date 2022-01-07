package tree.traverse;

import tree.TreeNode;


public class SumRootToLeafNumbers {
    /**
     * Link:
     * https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
     *
     */
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root, 0);
        return sum;
    }

    int sum = 0;
    private void dfs (TreeNode root, int digit) {
        if (root == null) {
            return;
        }

        int num = digit * 10 + root.val;

        if (root.left == null && root.right == null) {
            sum += num;
        }

        dfs(root.left, num);
        dfs(root.right, num);
    }
}
