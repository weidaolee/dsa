package tree.dp;
import tree.*;

public class LowestCommonAncestor {
    /**
     * Link:
     * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
     *
     * Main idea:
     * 只有兩種 cases:
     *    1. lca 是 p or q
     *    2. lca 不是 p or q
     *
     * 討論, 某一個 node , 會有 3 種情況
     *    1. 接收到 p, 自身是 q, return itself
     *    2. 接收到 p, 也接收到 q, return itself
     *    3. 其他情況, 一律 return null
     *
     * proof:
     * 當 lca 是 p or q:
     *   在 lca 上, 設 lca return p, lca 以上的 node, 因此而只能 return p, 於是 p 為 lca
     *
     *   當 lca 不是 p or q, lca return return lca, 因此得到 lca
     *
     *   其他情況一律是 null
     *
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return recursion(root, p, q);
    }

    private TreeNode recursion (TreeNode node, TreeNode p, TreeNode q) {
        if (node == null || node == p || node == q) {
            return node;
        }

        TreeNode left = recursion(node.left, p, q);
        TreeNode right = recursion(node.right, p, q);

        if (left != null && right != null) {
            return node;
        }

        return left != null ? left : right;
    } 
}
