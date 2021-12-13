package tree.dp;
import tree.*;

public class MaxDepth {
    public int maxDepth(TreeNode root) {
        return recursion(root);
    }

    public int recursion (TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftDepth = recursion(node.left);
        int rightDepth = recursion(node.right);

        return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
    }
}
