package tree.dp;
import tree.TreeNode;

public class FindDistance {
    /**
     * Main idea:
     * 1. 先找到 lowest common ancenstor
     * 2. 以 lca 為 root, 計算 p, q 距離
     *
     * Implementation:
     *
     * lca:
     * base cases:
     *     if (root == null || root == p || root == q)
     *         return root
     *
     * normal case:
     *     left = lca (root.left)
     *     right = lca (root.right)
     *     if (left != null && right != null)
     *        說明左右子樹個找到 1 個 p and q
     *     else:
     *        誰不為空返回誰
     *        return left != null ? left : right;
     *
     * getInfo:
     *     root == null:
     *        edges = 0
     *        found = 0
     *
     *     left = getInfo(root.left)
     *     right = getInfo(root.right)
     *
     *     if (left 跟 right 都找到 0 個):
     *         如果 root 找到一個, return info (0, 1), else return info (0, 0)
     *
     * case: root 啥都不是
     *     if (left 跟 right 有一個找齊全部):
     *         return left.found == 2 ? left : right;
     *
     *     if (left 跟 right 各找到 一個):
     *         return info (left.edges + right.edges + 2, 2)
     *
     *     if (left 跟 right 其中只有一個有找到)
     *         return info (left.edges + right.edges + 1, 1)
     * case: root 是 p, q 其中之一
     *     if (root.val == p || root.val == q)
     *         return info (left.edges + right.edges + 1, 2)
     */

    public int findDistance(TreeNode root, int p, int q) {
        if (root == null || p == q) {
            return 0;
        }
        root =  getLowestAncenstor(root, p, q);
        // int [] info = getInfo(root, p, q);
        return getInfo(root, p, q).edges;
    }

    public TreeNode getLowestAncenstor(TreeNode root, int p, int q) {
        if (root == null || root.val == p || root.val == q) {
            return root;
        }

        TreeNode left = getLowestAncenstor(root.left, p, q);
        TreeNode right = getLowestAncenstor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    public Info getInfo (TreeNode root, int p, int q) {
        if (root == null) {
            return new Info(0, 0);
        }

        Info left = getInfo(root.left, p, q);
        Info right = getInfo(root.right, p, q);

        if (left.found == 0 && right.found == 0) {
        // case: leaf
            // if leaf.val == p or q, find one of q or q
            // but num of edges = 0
            return root.val == p || root.val == q ? new Info (0, 1) : new Info (0, 0);
        }


        if (left.found == 2 || right.found == 2) {
        // case: had already find p and q
            return left.found == 2 ? left : right;
        }

        Info info = new Info(0, left.found + right.found);
        if (root.val != p && root.val != q) {
        // case: root is not p or q
            // case 1: left or right find one of p and q -> edges + 1
            // case 2: left find p and right find q -> edges + 2
            info.edges = left.edges + right.edges + info.found;
        } else {
        // case: root is p or q
            info.edges = left.edges + right.edges + info.found;
            info.found ++;
        }
        return info;
    }

    private static class Info {
        int edges;
        int found;
		public Info(int edges, int found) {
			this.edges = edges;
			this.found = found;
		}
    }
}
