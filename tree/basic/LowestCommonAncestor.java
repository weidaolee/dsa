package tree.basic;
import tree.*;

public class LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return recursion(root, p, q);
    }

    public TreeNode recursion (TreeNode node, TreeNode p, TreeNode q) {
        /*
          只有兩種 cases:
          1. lca 是 p or q
          2. lca 不是 p and q
         */


        if (node == null || node == p || node == q) {
            /*
              node != p, node != p -> 跳過
              如果遇到 p, q, null -> 直接 return p, q, or null
              所以，如果某子樹若不包含 p or q, 一定 return null
            */
            return node;
        }

        /*
          向 left 跟 right 要答案:
          如果遞迴中命中 base-case, left and right = {p, q, null}

         */
        TreeNode left = recursion(node.left, p, q);
        TreeNode right = recursion(node.right, p, q);

        if (left != null && right != null) {
        /*
          如果兩者接不為 null，說明 左右分別找到了 p or q, 屬於 case 2
          把自己 return, 且向上傳遞過程中, 自己是最低的 lca

          注意，這裡只會中 case 2
         */
            return node;
        }

        /*
          如果整個過程，只有找到 p or q 其中之一，代表該 子樹上包含了 p and q,
          且我們又 return 了最先找到的 p or q, 屬於 case 1.
         */
        return left != null ? left : right;
    } 
}
