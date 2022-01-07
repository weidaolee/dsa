package tree.dp;
import tree.*;


public class SameTree {
    /**
     *Link:
     * https://leetcode-cn.com/problems/same-tree/
     *
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return recursion(p, q);
    }

    private boolean recursion (TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null && q != null) {
            return false;
        }

        if (p != null && q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }
        return recursion(p.left, q.left) && recursion(p.right, q.right);
    }
}
